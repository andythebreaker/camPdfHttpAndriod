package me.andythebreaker.basic_http_sv;

import WebServer.MyWebServer;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Rational;
import android.util.Size;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.app.NotificationCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "float_cam_srv";
    private static final int REQUEST_CODE_PERMISSIONS = 1001;
    private static final int RECORDING_NOTIFICATION_ID = 2001;
    private static final long SEGMENT_DURATION_MS = 60_000L;
    private static final String NOTIFICATION_CHANNEL_ID = "float_cam_srv_recording";
    private static final String ACTION_STOP_RECORDING = "me.andythebreaker.float_cam_srv.STOP_RECORDING";
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            "android.permission.CAMERA"
    };
    private static final Size CAPTURE_SIZE = new Size(1920, 1080);

    private final Executor cameraExecutor = Executors.newSingleThreadExecutor();

    private MyWebServer myWebServer;
    private PreviewView previewView;
    private View topScrim;
    private View controlsContainer;
    private TextView statusText;
    private TextView infoText;
    private SeekBar previewScaleSeekBar;
    private ImageCapture imageCapture;
    private VideoCapture videoCapture;
    private float currentPreviewScale = 1.0f;
    private String focusSummary = "pending";
    private boolean segmentedRecordingEnabled;
    private boolean isRecordingSegment;
    private boolean restartRecordingAfterSave;
    private File currentVideoFile;
    private Handler mainHandler;
    private NotificationManager notificationManager;
    private final Runnable segmentRotateRunnable = this::rotateSegment;
    private final BroadcastReceiver stopRecordingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_STOP_RECORDING.equals(intent.getAction())) {
                stopSegmentedRecording(true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.camera_preview_view);
        topScrim = findViewById(R.id.top_scrim);
        controlsContainer = findViewById(R.id.controls_container);
        statusText = findViewById(R.id.status_text);
        infoText = findViewById(R.id.info_text);
        previewScaleSeekBar = findViewById(R.id.preview_scale_seekbar);
        ImageButton captureButton = findViewById(R.id.capture_button);
        ImageButton floatButton = findViewById(R.id.float_button);
        mainHandler = new Handler(Looper.getMainLooper());
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        statusText.setText(getString(R.string.status_initializing));
        infoText.setText(buildInfoText());
        createNotificationChannel();
        registerReceiver(stopRecordingReceiver, new IntentFilter(ACTION_STOP_RECORDING));

        previewScaleSeekBar.setProgress(45);
        applyPreviewScale(progressToScale(previewScaleSeekBar.getProgress()));
        previewScaleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                applyPreviewScale(progressToScale(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        captureButton.setOnClickListener(v -> captureImage());
        floatButton.setOnClickListener(v -> enterFloatingMode());

        startWebServer();

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (allPermissionsGranted()) {
            enterFloatingMode();
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        topScrim.setVisibility(isInPictureInPictureMode ? View.GONE : View.VISIBLE);
        controlsContainer.setVisibility(isInPictureInPictureMode ? View.GONE : View.VISIBLE);
        if (isInPictureInPictureMode) {
            startSegmentedRecording();
        } else {
            stopSegmentedRecording(false);
        }
    }

    @Override
    protected void onDestroy() {
        stopSegmentedRecording(false);
        unregisterReceiver(stopRecordingReceiver);
        super.onDestroy();
        if (myWebServer != null) {
            myWebServer.closeAllConnections();
            myWebServer = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, R.string.permissions_denied, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void startWebServer() {
        try {
            myWebServer = new MyWebServer(this);
            Toast.makeText(this, R.string.web_server_started, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, "Failed to start web server", e);
            Toast.makeText(this, getString(R.string.web_server_failed, e.getMessage()), Toast.LENGTH_LONG).show();
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCamera(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "Unable to start camera", e);
                statusText.setText(R.string.status_camera_error);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindCamera(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();

        imageCapture = new ImageCapture.Builder()
                .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation())
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .setTargetResolution(CAPTURE_SIZE)
                .build();

        videoCapture = new VideoCapture.Builder()
                .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation())
                .setTargetResolution(CAPTURE_SIZE)
                .setBitRate(4 * 1024 * 1024)
                .setVideoFrameRate(30)
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        cameraProvider.unbindAll();
        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, videoCapture);
        updateLensInfo(camera);
        statusText.setText(getString(R.string.status_ready, CAPTURE_SIZE.getWidth(), CAPTURE_SIZE.getHeight()));
    }

    @SuppressLint({"RestrictedApi", "UnsafeOptInUsageError"})
    private void updateLensInfo(Camera camera) {
        CameraCharacteristics cameraCharacteristics =
                Camera2CameraInfo.extractCameraCharacteristics(camera.getCameraInfo());
        Float minFocusDistance = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        focusSummary = minFocusDistance == null ? getString(R.string.focus_unknown) : String.format(Locale.US, "%.2f", minFocusDistance);
        updateInfoText();
    }

    private void captureImage() {
        if (imageCapture == null) {
            Toast.makeText(this, R.string.camera_not_ready, Toast.LENGTH_SHORT).show();
            return;
        }

        File outputFile = new File(getBatchDirectoryName(),
                new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date()) + ".jpg");

        ImageCapture.OutputFileOptions outputFileOptions =
                new ImageCapture.OutputFileOptions.Builder(outputFile).build();

        imageCapture.takePicture(outputFileOptions, cameraExecutor, new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(MainActivity.this, getString(R.string.image_saved, outputFile.getAbsolutePath()), Toast.LENGTH_LONG).show();
                    statusText.setText(getString(R.string.status_saved, outputFile.getName(), CAPTURE_SIZE.getWidth(), CAPTURE_SIZE.getHeight()));
                });
            }

            @Override
            public void onError(@NonNull ImageCaptureException error) {
                Log.e(TAG, "Image capture failed", error);
                new Handler(Looper.getMainLooper()).post(
                        () -> Toast.makeText(MainActivity.this, getString(R.string.capture_failed, error.getMessage()), Toast.LENGTH_LONG).show()
                );
            }
        });
    }

    private void enterFloatingMode() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Toast.makeText(this, R.string.pip_not_supported, Toast.LENGTH_SHORT).show();
            return;
        }

        PictureInPictureParams params = new PictureInPictureParams.Builder()
                .setAspectRatio(new Rational(16, 9))
                .build();
        enterPictureInPictureMode(params);
    }

    private void startSegmentedRecording() {
        if (videoCapture == null || segmentedRecordingEnabled) {
            return;
        }

        segmentedRecordingEnabled = true;
        statusText.setText(R.string.status_recording);
        startNewVideoSegment();
    }

    private void startNewVideoSegment() {
        if (!segmentedRecordingEnabled || videoCapture == null || isRecordingSegment) {
            return;
        }

        String batchDirectory = getBatchDirectoryName();
        Log.i(TAG, "Video segment directory: " + batchDirectory);
        currentVideoFile = new File(batchDirectory,
                new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date()) + ".mp4");
        Log.i(TAG, "Starting video segment: " + currentVideoFile.getAbsolutePath());
        VideoCapture.OutputFileOptions outputFileOptions =
                new VideoCapture.OutputFileOptions.Builder(currentVideoFile).build();

        isRecordingSegment = true;
        restartRecordingAfterSave = false;
        showRecordingNotification();
        scheduleNextSegmentRotation();

        videoCapture.startRecording(outputFileOptions, cameraExecutor, new VideoCapture.OnVideoSavedCallback() {
            @Override
            public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                mainHandler.post(() -> {
                    Log.i(TAG, "Saved video segment: " + currentVideoFile.getAbsolutePath());
                    isRecordingSegment = false;
                    statusText.setText(getString(R.string.status_segment_saved, currentVideoFile.getName()));
                    if (segmentedRecordingEnabled && restartRecordingAfterSave) {
                        restartRecordingAfterSave = false;
                        startNewVideoSegment();
                    } else if (!segmentedRecordingEnabled) {
                        cancelRecordingNotification();
                    }
                });
            }

            @Override
            public void onError(int videoCaptureError, @NonNull String message, Throwable cause) {
                Log.e(TAG, "Video capture error: " + message, cause);
                mainHandler.post(() -> {
                    isRecordingSegment = false;
                    restartRecordingAfterSave = false;
                    segmentedRecordingEnabled = false;
                    mainHandler.removeCallbacks(segmentRotateRunnable);
                    cancelRecordingNotification();
                    Toast.makeText(MainActivity.this, getString(R.string.recording_failed, message), Toast.LENGTH_LONG).show();
                    statusText.setText(R.string.status_camera_error);
                });
            }
        });
    }

    private void rotateSegment() {
        if (!segmentedRecordingEnabled || !isRecordingSegment || videoCapture == null) {
            return;
        }
        restartRecordingAfterSave = true;
        videoCapture.stopRecording();
    }

    private void stopSegmentedRecording(boolean fromNotification) {
        if (!segmentedRecordingEnabled && !isRecordingSegment) {
            cancelRecordingNotification();
            return;
        }

        segmentedRecordingEnabled = false;
        restartRecordingAfterSave = false;
        mainHandler.removeCallbacks(segmentRotateRunnable);
        if (isRecordingSegment && videoCapture != null) {
            videoCapture.stopRecording();
        } else {
            cancelRecordingNotification();
        }

        if (fromNotification) {
            Toast.makeText(this, R.string.recording_stopped, Toast.LENGTH_SHORT).show();
        }
        statusText.setText(getString(R.string.status_ready, CAPTURE_SIZE.getWidth(), CAPTURE_SIZE.getHeight()));
    }

    private void scheduleNextSegmentRotation() {
        mainHandler.removeCallbacks(segmentRotateRunnable);
        mainHandler.postDelayed(segmentRotateRunnable, SEGMENT_DURATION_MS);
    }

    private void applyPreviewScale(float scale) {
        currentPreviewScale = scale;
        previewView.setScaleX(scale);
        previewView.setScaleY(scale);
        if (infoText != null) {
            updateInfoText();
        }
    }

    private float progressToScale(int progress) {
        return 0.60f + (progress / 100f) * 0.80f;
    }

    private String getBatchDirectoryName() {
        File baseDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File directory = baseDirectory == null ? new File(getFilesDir(), "images") : new File(baseDirectory, "images");
        if (!directory.exists() && !directory.mkdirs()) {
            Log.w(TAG, "Could not create image output directory: " + directory.getAbsolutePath());
        }
        Log.i(TAG, "Media output directory: " + directory.getAbsolutePath());
        return directory.getAbsolutePath();
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private String buildInfoText() {
        return getString(R.string.info_template, getLocalIpAddress(), focusSummary, currentPreviewScale);
    }

    private void updateInfoText() {
        infoText.setText(buildInfoText());
    }

    private void showRecordingNotification() {
        Intent stopIntent = new Intent(ACTION_STOP_RECORDING).setPackage(getPackageName());
        int pendingIntentFlags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pendingIntentFlags |= PendingIntent.FLAG_IMMUTABLE;
        }
        PendingIntent stopPendingIntent =
                PendingIntent.getBroadcast(this, 0, stopIntent, pendingIntentFlags);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_camera_black_36dp)
                .setContentTitle(getString(R.string.notification_recording_title))
                .setContentText(getString(R.string.notification_recording_text))
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .addAction(0, getString(R.string.stop_recording), stopPendingIntent);

        notificationManager.notify(RECORDING_NOTIFICATION_ID, builder.build());
    }

    private void cancelRecordingNotification() {
        notificationManager.cancel(RECORDING_NOTIFICATION_ID);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_LOW
        );
        channel.setDescription(getString(R.string.notification_channel_description));
        notificationManager.createNotificationChannel(channel);
    }

    @SuppressLint("LongLogTag")
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return "unavailable";
    }
}
