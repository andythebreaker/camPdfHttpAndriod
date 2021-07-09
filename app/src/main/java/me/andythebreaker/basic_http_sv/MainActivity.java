package me.andythebreaker.basic_http_sv;

import androidx.appcompat.app.AppCompatActivity;
import WebServer.MyWebServer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private String hostip; //本機IP
    private MyWebServer mywebserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hostip = getLocalIpAddress();
        String tmp="";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    tmp+=inetAddress.getHostAddress().toString()+"->"+
                            (inetAddress.isAnyLocalAddress()?"isAnyLocalAddress, ":"-")+
                            (  inetAddress.isLinkLocalAddress()?"isLinkLocalAddress, ":"-")+
                            (  inetAddress.isLoopbackAddress()?"isLoopbackAddress, ":"-")+
                            (  inetAddress.isMCGlobal()?"isMCGlobal, ":"-")+
                            (  inetAddress.isMCLinkLocal()?"isMCLinkLocal, ":"-")+
                            (  inetAddress.isMCNodeLocal()?"isMCNodeLocal, ":"-")+
                            (  inetAddress.isMCOrgLocal()?"isMCOrgLocal, ":"-")+
                            ( inetAddress.isMCSiteLocal()?"isMCSiteLocal, ":"-")+
                            (  inetAddress.isMulticastAddress()?"isMulticastAddress, ":"-")+
                            (  inetAddress.isSiteLocalAddress()?"isSiteLocalAddress, ":"-")+"#";
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }


        TextView tt = (TextView)findViewById(R.id.t1);
        tt.setText(tmp);
        try {
            mywebserver = new MyWebServer(this);
            Toast toast = Toast.makeText(this, "onResume->WebServer started", Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "onResume->WebServer start failed..."+ e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
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

    //獲取本地IP
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
        toast.show();if (mywebserver != null) {
            mywebserver.closeAllConnections();
            mywebserver = null;
            Toast toast2 = Toast.makeText(this, "onPause->app pause, so web server close...", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void checkIpClick(View v){
        String tmp="";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    tmp+= inetAddress.getHostAddress().toString()+";";
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        Toast toast = Toast.makeText(this, tmp, Toast.LENGTH_SHORT);
        toast.show();
    }

}
