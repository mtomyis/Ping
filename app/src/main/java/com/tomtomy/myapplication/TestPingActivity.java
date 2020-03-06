package com.tomtomy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestPingActivity extends AppCompatActivity {
    private static final String TAG = TestPingActivity.class.getSimpleName();

    TextView rs1, rs2, rs3, rsjml;
    EditText edip;
    String ip, iplocal;
    long timeofping, timeofping2, timeofping3;
    int tersabung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ping);
        init();
        WifiManager wm= (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        iplocal = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    }

    private void init() {
        rs1 = findViewById(R.id.idtvping1);
        rs2 = findViewById(R.id.idtvping2);
        rs3 = findViewById(R.id.idtvping3);
        rsjml = findViewById(R.id.idtvpingjmlpkt);
        edip = findViewById(R.id.idedping);
    }

    public void ping(View view) {
        ip = edip.getText().toString().trim();
        new Thread(ping1).start();

    }

    //ping1
    public Runnable ping1 = new Runnable() {
        @Override
        public void run() {
            Runtime runtime = Runtime.getRuntime();
            try {
                long a = System.currentTimeMillis() % 1000;
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
                tersabung = ipProcess.waitFor();
                timeofping = System.currentTimeMillis() % 1000 - a;

            } catch (IOException e)          { e.printStackTrace(); }
            catch (InterruptedException e) { e.printStackTrace(); }

            // update the ping result - we need to call this on the UI thread
            // because it updates UI elements (TextView)
            Log.i(TAG, "Kode " + tersabung);
            if (tersabung==0){
                runOnUiThread(new Runnable() {
                    public void run() {
                        setPingResult(timeofping);
                    }
                });


            }
            else {
                runOnUiThread(new Runnable() {
                    public void run() {
                        setgagalPingResult(timeofping);
                    }
                });
            }

        }
    };

    void setPingResult(long pingtime) {
        rs1.setText("#"+iplocal+" ping to "+ip+ " : time "+Math.abs(pingtime)+" ms");
        rs1.setTextColor(Color.BLACK);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(ping2).start();
    }
    void setgagalPingResult(long pingtime) {
        rs1.setText("Terputus");
        rs1.setTextColor(Color.RED);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(ping2).start();
    }
    //ping1

    //ping2
    public Runnable ping2 = new Runnable() {
        @Override
        public void run() {
            Runtime runtime = Runtime.getRuntime();
            try {
                long a = System.currentTimeMillis() % 1000;
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
                tersabung = ipProcess.waitFor();
                timeofping2 = System.currentTimeMillis() % 1000 - a;

            } catch (IOException e)          { e.printStackTrace(); }
            catch (InterruptedException e) { e.printStackTrace(); }

            // update the ping result - we need to call this on the UI thread
            // because it updates UI elements (TextView)
            if (tersabung==0){
                runOnUiThread(new Runnable() {
                    public void run() {
                        setPingResul2(timeofping2);
                    }
                });


            }
            else {
                runOnUiThread(new Runnable() {
                    public void run() {
                        setgagalPingResul2(timeofping2);
                    }
                });
            }

        }
    };

    void setPingResul2(long pingtime) {
        rs2.setText("#"+iplocal+" ping to "+ip+ " : time "+Math.abs(pingtime)+" ms");
        rs2.setTextColor(Color.BLACK);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(ping3).start();
    }
    void setgagalPingResul2(long pingtime) {
        rs2.setText("Terputus");
        rs2.setTextColor(Color.RED);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(ping3).start();
    }
    //ping2

    //ping3
    public Runnable ping3 = new Runnable() {
        @Override
        public void run() {
            Runtime runtime = Runtime.getRuntime();
            try {
                long a = System.currentTimeMillis() % 1000;
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
                tersabung = ipProcess.waitFor();
                timeofping3 = System.currentTimeMillis() % 1000 - a;

            } catch (IOException e)          { e.printStackTrace(); }
            catch (InterruptedException e) { e.printStackTrace(); }

            // update the ping result - we need to call this on the UI thread
            // because it updates UI elements (TextView)
            if (tersabung==0){
                runOnUiThread(new Runnable() {
                    public void run() {
                        setPingResul3(timeofping3);
                    }
                });

            }
            else {
                runOnUiThread(new Runnable() {
                    public void run() {
                        setgagalPingResul3(timeofping3);
                    }
                });
            }

        }
    };

    void setPingResul3(long pingtime) {
        rs3.setText("#"+iplocal+" ping to "+ip+ " : time "+Math.abs(pingtime)+" ms");
        rs3.setTextColor(Color.BLACK);
        rsjml.setText("#3 packets transmitted to "+ip);
//        new Thread(ping3).start();
    }
    void setgagalPingResul3(long pingtime) {
        rs3.setText("Terputus");
        rs3.setTextColor(Color.RED);
        rsjml.setText("#3 packets transmitted to "+ip);
//        new Thread(ping3).start();
    }
    //ping3

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//        finish();
//        super.onBackPressed();
//    }
}
