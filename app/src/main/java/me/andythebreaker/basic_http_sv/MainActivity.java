package me.andythebreaker.basic_http_sv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import WebServer.MyWebServer;
import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
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
import java.io.File;


import androidx.annotation.NonNull;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.extensions.ExtensionsManager;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

import static androidx.camera.extensions.ExtensionMode.HDR;

public class MainActivity extends AppCompatActivity {

    private String hostip; //??????IP
    private MyWebServer mywebserver;
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    PreviewView mPreviewView;
    ImageView captureImage;


    private Executor executor = Executors.newSingleThreadExecutor();
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    PreviewView mPreviewView;
    ImageView captureImage;
    TextView tt;
    TextView t3;
    SeekBar sk;
    TextView aaaaaaaaaaaaaaaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getActionBar().hide();

        //hostip = getLocalIpAddress();
        String tmp = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    tmp += inetAddress.getHostAddress().toString() + "->" +
                            (inetAddress.isAnyLocalAddress() ? "isAnyLocalAddress, " : "-") +
                            (inetAddress.isLinkLocalAddress() ? "isLinkLocalAddress, " : "-") +
                            (inetAddress.isLoopbackAddress() ? "isLoopbackAddress, " : "-") +
                            (inetAddress.isMCGlobal() ? "isMCGlobal, " : "-") +
                            (inetAddress.isMCLinkLocal() ? "isMCLinkLocal, " : "-") +
                            (inetAddress.isMCNodeLocal() ? "isMCNodeLocal, " : "-") +
                            (inetAddress.isMCOrgLocal() ? "isMCOrgLocal, " : "-") +
                            (inetAddress.isMCSiteLocal() ? "isMCSiteLocal, " : "-") +
                            (inetAddress.isMulticastAddress() ? "isMulticastAddress, " : "-") +
                            (inetAddress.isSiteLocalAddress() ? "isSiteLocalAddress, " : "-") + "#";
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }


        tt = (TextView) findViewById(R.id.t1);

        tt.setText(tmp);
        t3 = (TextView) findViewById(R.id.t3);
        t3.setText("..........");
        sk = (SeekBar) findViewById(R.id.seekBar2);
        aaaaaaaaaaaaaaaa = (TextView) findViewById(R.id.aaaaaaaaaaaaaaaa);

        try {
            mywebserver = new MyWebServer(this);
            Toast toast = Toast.makeText(this, "onResume->WebServer started", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "onResume->WebServer start failed..." + e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }


        mPreviewView = findViewById(R.id.camera_previewView);
        captureImage = findViewById(R.id.captureImg);

        if (allPermissionsGranted()) {
            startCamera(); //start camera if permission has been granted by user
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aaaaaaaaaaaaaaaa.setText("??????????????????" + progress + "  /  ????????????" + sk.getMax());

            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "??????SeekBar", Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "??????SeekBar", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        /*try {
            mywebserver = new MyWebServer(this);
            Toast toast = Toast.makeText(this, "onResume->WebServer started", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "onResume->WebServer start failed..."+ e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }

    @Override
    public void onPause() {
        super.onPause();

        /*if (mywebserver != null) {
            mywebserver.closeAllConnections();
            mywebserver = null;
            Toast toast = Toast.makeText(this, "onPause->app pause, so web server close...", Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }

    //????????????IP
    @SuppressLint("LongLogTag")
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }

    public void buttonOnClick(View v) {
        //Button button = (Button) v;
        Toast toast = Toast.makeText(this, "?????????????????????", Toast.LENGTH_SHORT);
        toast.show();
        if (mywebserver != null) {
            mywebserver.closeAllConnections();
            mywebserver = null;
            Toast toast2 = Toast.makeText(this, "onPause->app pause, so web server close...", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void checkIpClick(View v) {
        String tmp = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    tmp += inetAddress.getHostAddress().toString() + ";";
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        Toast toast = Toast.makeText(this, tmp, Toast.LENGTH_SHORT);
        toast.show();
    }



    ///////////////////////////////////////////////////////////////////////////////////

    private void startCamera() {

        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);


        cameraProviderFuture.addListener(() -> {
            try {

                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);

            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));
    }


    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .build();

        ImageCapture.Builder builder = new ImageCapture.Builder();


        final ImageCapture imageCapture = builder
                .setTargetRotation(this.getWindowManager().getDefaultDisplay().getRotation())
                .build();

        preview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview, imageAnalysis, imageCapture);

        @SuppressLint({"RestrictedApi", "UnsafeOptInUsageError"}) CameraCharacteristics camChars = Camera2CameraInfo
                .extractCameraCharacteristics(camera.getCameraInfo());
        float discoveredMinFocusDistance = camChars
                .get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        Log.i("dev", "\t\t\t----------------------------\n\n\n----------------------------------------\t\t\tfound it! " + discoveredMinFocusDistance);
        t3.setText(String.valueOf(discoveredMinFocusDistance));
        captureImage.setOnClickListener(v -> {

            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            File file = new File(getBatchDirectoryName(), mDateFormat.format(new Date()) + ".jpg");

            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
            imageCapture.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback() {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                    new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(MainActivity.this, "Image Saved successfully", Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onError(@NonNull ImageCaptureException error) {
                    error.printStackTrace();
                }
            });
        });
    }

    public String getBatchDirectoryName() {

        String app_folder_path = "";
        app_folder_path = Environment.getExternalStorageDirectory().toString() + "/images";
        File dir = new File(app_folder_path);
        if (!dir.exists() && !dir.mkdirs()) {

        }

        return app_folder_path;
    }

    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }

    }

}

