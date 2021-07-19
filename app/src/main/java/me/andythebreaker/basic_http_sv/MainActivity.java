package me.andythebreaker.basic_http_sv;

import androidx.appcompat.app.AppCompatActivity;

import WebServer.MyWebServer;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import androidx.annotation.NonNull;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.camera2.interop.Camera2Interop;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.extensions.ExtensionsManager;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.CameraController;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import static androidx.camera.extensions.ExtensionMode.HDR;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class MainActivity extends AppCompatActivity {

    private String hostip; //本機IP
    private MyWebServer mywebserver;

    private Executor executor = Executors.newSingleThreadExecutor();
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    PreviewView mPreviewView;
    ImageView captureImage;
    //TextView tt;
    TextView t3;
    SeekBar sk;
    TextView aaaaaaaaaaaaaaaa;
    RecyclerView mRecyclerView;
    MyListAdapter myListAdapter;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    Preview.Builder previewBuilder_wf = new Preview.Builder();
    Switch switch_if_af;
    int define_if_able_auto_focus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().hide();
        //hostip = getLocalIpAddress();
        //String tmp = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("Id", inetAddress.getHostAddress());
                    //hashMap.put("Sub1",String.valueOf(new Random().nextInt(80) + 20));
                    //hashMap.put("Sub2",String.valueOf(new Random().nextInt(80) + 20));

                    String tmp_ip_stat = (inetAddress.isAnyLocalAddress() ? "isAnyLocalAddress, " : "-, ") +
                            (inetAddress.isLinkLocalAddress() ? "isLinkLocalAddress, " : "-, ") +
                            (inetAddress.isLoopbackAddress() ? "isLoopbackAddress, " : "-, ") +
                            (inetAddress.isMCGlobal() ? "isMCGlobal, " : "-, ") +
                            (inetAddress.isMCLinkLocal() ? "isMCLinkLocal, " : "-, ") +
                            (inetAddress.isMCNodeLocal() ? "isMCNodeLocal, " : "-, ") +
                            (inetAddress.isMCOrgLocal() ? "isMCOrgLocal, " : "-, ") +
                            (inetAddress.isMCSiteLocal() ? "isMCSiteLocal, " : "-, ") +
                            (inetAddress.isMulticastAddress() ? "isMulticastAddress, " : "-, ") +
                            (inetAddress.isSiteLocalAddress() ? "isSiteLocalAddress, " : "-, ");

                    hashMap.put("Avg", tmp_ip_stat);

                    arrayList.add(hashMap);

                    //tmp += inetAddress.getHostAddress().toString() + "->" +tmp_ip_stat + "#";
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }

        //tt = (TextView) findViewById(R.id.t1);
        //tt.setText(tmp);

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
                aaaaaaaaaaaaaaaa.setText("目前拖移植：" + progress + "  /  最大值：" + sk.getMax());
                if (define_if_able_auto_focus == 0) {
                    setFocusDistance(previewBuilder_wf, progress / 10);
                }
            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "觸碰SeekBar", Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "放開SeekBar", Toast.LENGTH_SHORT).show();

            }
        });
        //製造資料
        /*for (int i = 0;i<30;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("Id","座號："+String.format("%02d",i+1));
            hashMap.put("Sub1",String.valueOf(new Random().nextInt(80) + 20));
            hashMap.put("Sub2",String.valueOf(new Random().nextInt(80) + 20));
            hashMap.put("Avg",String.valueOf(
                    (Integer.parseInt(hashMap.get("Sub1"))
                            +Integer.parseInt(hashMap.get("Sub2")))/2));

            arrayList.add(hashMap);
        }*/
        //設置RecycleView
        mRecyclerView = findViewById(R.id.lllll);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        mRecyclerView.setAdapter(myListAdapter);

        switch_if_af = (Switch) findViewById(R.id.switch1);
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

    @Override
    public void onDestroy() {

        if (mywebserver != null) {
            mywebserver.closeAllConnections();
            mywebserver = null;
            Toast toast2 = Toast.makeText(this, "onPause->app pause, so web server close...", Toast.LENGTH_SHORT);
            toast2.show();
        }
        super.onDestroy();

    }

    //獲取本地IP
    @Nullable
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
        Toast toast = Toast.makeText(this, "按鈕已經被點擊", Toast.LENGTH_SHORT);
        toast.show();
        if (mywebserver != null) {
            mywebserver.closeAllConnections();
            mywebserver = null;
            Toast toast2 = Toast.makeText(this, "onPause->app pause, so web server close...", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void bton_sv_hand_startOnClick(View v) {
        Toast toast2 = Toast.makeText(this, "hand start sv", Toast.LENGTH_SHORT);
        toast2.show();
        try {
            mywebserver = new MyWebServer(this);
            Toast toast = Toast.makeText(this, "onResume->WebServer started", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "onResume->WebServer start failed..." + e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
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

        Preview preview;
        /*Preview preview = new Preview.Builder()
                .build();*/

        /*if(switch_if_af.isChecked()){

        }else{

        }*/

        if (define_if_able_auto_focus == 0) {
            float focusDistance = 0F; // example: infinite focus
            //Preview.Builder previewBuilder_wf = new Preview.Builder();
            setFocusDistance(previewBuilder_wf, focusDistance);
            preview = previewBuilder_wf.build();
        } else {
            preview = new Preview.Builder()
                    .build();
        }

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .build();

        ImageCapture.Builder builder = new ImageCapture.Builder();

        //TODO:72dpi->96dpi
        final ImageCapture imageCapture = builder
                .setTargetRotation(this.getWindowManager().getDefaultDisplay().getRotation())
                //.setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build();

        preview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview, imageAnalysis, imageCapture);

        @SuppressLint({"RestrictedApi", "UnsafeOptInUsageError"}) CameraCharacteristics camChars = Camera2CameraInfo
                .extractCameraCharacteristics(camera.getCameraInfo());
        float discoveredMinFocusDistance = camChars
                .get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        Log.i("dev", "\t\t\t----------------------------\n\n\n----------------------------------------\t\t\tfound it! " + discoveredMinFocusDistance);
        t3.setText(String.valueOf(discoveredMinFocusDistance));

        //af.ontouch

        //original
        captureImage.setOnClickListener(v -> {

            /*val cameraControl = CameraX.getCameraControl(CameraX.LensFacing.BACK) // you can set it to front
            val factory = TextureViewMeteringPointFactory(textureView)
            val point = factory.createPoint(event.x, event.y)
            val action = FocusMeteringAction.Builder.from(point).build()
            cameraControl.startFocusAndMetering(action)*/

            /*CameraControl my_cam_ctrl=camera.getCameraControl();
            new MeteringPointFactory();
            FocusMeteringAction.Builder myFocusMeteringAction_Builder=FocusMeteringAction.Builder();
            my_cam_ctrl.startFocusAndMetering(FocusMeteringAction.FLAG_AF);*/
            focus(mPreviewView, null, camera);

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
        /*previewView.setOnTouchListener { _, event ->
                scaleGestureDetector.onTouchEvent(event)
            return@setOnTouchListener true
        }*/
//呼叫一個新的class

        mPreviewView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        return focus(v, event, camera);
                    //break;
                    default:
                        // Unhandled event.
                        return false;
                }
                return true;
            }
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


    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {


        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvId, tvSub1, tvSub2, tvAvg;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvId = itemView.findViewById(R.id.textView_Id);
                //tvSub1 = itemView.findViewById(R.id.textView_sub1);
                //tvSub2 = itemView.findViewById(R.id.textView_sub2);
                tvAvg = itemView.findViewById(R.id.textView_avg);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            /*int avgS = Integer.parseInt(arrayList.get(position).get("Avg"));
            if (avgS>=80){
                holder.tvId.setBackgroundColor(getColor(R.color.green_TOKIWA));
            }else if (avgS<80 &&avgS>=60){
                holder.tvId.setBackgroundColor(getColor(R.color.blue_RURI));
            }else if(avgS<60 &&avgS>=40){
                holder.tvId.setBackgroundColor(getColor(R.color.yellow_YAMABUKI));
            }else {
                holder.tvId.setBackgroundColor(getColor(R.color.red_GINSYU));
            }*/
            String avgS = arrayList.get(position).get("Avg");
            if (avgS.contains("isSiteLocalAddress, ")) {
                holder.tvId.setBackgroundColor(getColor(R.color.red_ip));
            } else if (avgS.contains("-, -, -, -, -, -, -, -, -, -, ")) {
                holder.tvId.setBackgroundColor(getColor(R.color.blank_ip));
            } else {
                holder.tvId.setBackgroundColor(getColor(R.color.other_ip));
            }

            holder.tvId.setText(arrayList.get(position).get("Id"));
            //holder.tvSub1.setText(arrayList.get(position).get("Sub1"));
            //holder.tvSub2.setText(arrayList.get(position).get("Sub2"));
            holder.tvAvg.setText(arrayList.get(position).get("Avg"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

    void setFocusDistance(ExtendableBuilder<?> builder, float distance) {
        Camera2Interop.Extender extender = new Camera2Interop.Extender(builder);
        extender.setCaptureRequestOption(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_OFF);
        extender.setCaptureRequestOption(CaptureRequest.LENS_FOCUS_DISTANCE, distance);
    }


    private boolean focus(View v, MotionEvent event, Camera camcrtl) {
        final float x = (event != null) ? event.getX() : v.getX() + v.getWidth() / 2f;
        final float y = (event != null) ? event.getY() : v.getY() + v.getHeight() / 2f;
        toast_is_good_to_eat("x="+String.valueOf(x)+";y="+String.valueOf(y));
        MeteringPointFactory pointFactory = mPreviewView.getMeteringPointFactory();
        float afPointWidth = 1.0f / 6.0f;  // 1/6 total area
        float aePointWidth = afPointWidth * 1.5f;
        MeteringPoint afPoint = pointFactory.createPoint(x, y/*, afPointWidth, 1.0f*/);
        MeteringPoint aePoint = pointFactory.createPoint(x, y/*, aePointWidth, 1.0f*/);

        // try {
        FocusMeteringAction wtfijustwanttofuckingfocusashit;
        wtfijustwanttofuckingfocusashit = new FocusMeteringAction.Builder(afPoint).build();
        camcrtl.getCameraControl().startFocusAndMetering(wtfijustwanttofuckingfocusashit);

        return true;
    }

    public void toast_is_good_to_eat(String msg_to_toast_out) {
        Toast toast3 = Toast.makeText(this, msg_to_toast_out, Toast.LENGTH_SHORT);
        toast3.show();
    }

}

