package com.tomtomy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.tomtomy.myapplication.MainActivity.EXTRA_IP;
import static com.tomtomy.myapplication.MainActivity.EXTRA_IPR;
import static com.tomtomy.myapplication.MainActivity.EXTRA_JENISROUTER;
import static com.tomtomy.myapplication.MainActivity.EXTRA_LOKASI;
import static com.tomtomy.myapplication.MainActivity.EXTRA_MAC;
import static com.tomtomy.myapplication.MainActivity.EXTRA_PASSWORDSSID;
import static com.tomtomy.myapplication.MainActivity.EXTRA_PASSWORDUSERADMIN;
import static com.tomtomy.myapplication.MainActivity.EXTRA_SSID;
import static com.tomtomy.myapplication.MainActivity.EXTRA_STATUS;
import static com.tomtomy.myapplication.MainActivity.EXTRA_USERADMIN;

public class DetailApActivity extends AppCompatActivity {
    String ip, gedung, status, jenisrouter, ssid, passwordssid, mac, ipremote, useradmin, passworduseradmin;
    String result;
    int tersabung;
    TextView mip, mgedung, mstatus, mjenisrouter, mssid, mpasswordssid, mmac, mipremote, museradmin, mpassworduseradmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ap);
        init();

        Intent intent = getIntent();
        ip = intent.getStringExtra(EXTRA_IP);
        gedung = intent.getStringExtra(EXTRA_LOKASI);
        status = intent.getStringExtra(EXTRA_STATUS);
        jenisrouter = intent.getStringExtra(EXTRA_JENISROUTER);
        ssid = intent.getStringExtra(EXTRA_SSID);
        passwordssid = intent.getStringExtra(EXTRA_PASSWORDSSID);
        mac = intent.getStringExtra(EXTRA_MAC);
        ipremote = intent.getStringExtra(EXTRA_IPR);
        useradmin = intent.getStringExtra(EXTRA_USERADMIN);
        passworduseradmin = intent.getStringExtra(EXTRA_PASSWORDUSERADMIN);

        if (status.equals("1")){
            result = "Terkoneksi";
            mip.setText(": "+ip);
            mgedung.setText(": "+gedung);

            mstatus.setText(": "+result);
            mstatus.setTextColor(Color.DKGRAY);

            mjenisrouter.setText(": "+jenisrouter);
            mssid.setText(": "+ssid);
            mpasswordssid.setText(": "+passwordssid);
            mmac.setText(": "+mac);
            mipremote.setText(": "+ipremote);
            museradmin.setText(": "+useradmin);
            mpassworduseradmin.setText(": "+passworduseradmin);
        }

        else {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
                tersabung = ipProcess.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (tersabung==0){
                result = "Terkoneksi";
                mip.setText(": "+ip);
                mgedung.setText(": "+gedung);

                mstatus.setText(": "+result);
                mstatus.setTextColor(Color.DKGRAY);

                mjenisrouter.setText(": "+jenisrouter);
                mssid.setText(": "+ssid);
                mpasswordssid.setText(": "+passwordssid);
                mmac.setText(": "+mac);
                mipremote.setText(": "+ipremote);
                museradmin.setText(": "+useradmin);
                mpassworduseradmin.setText(": "+passworduseradmin);
            } else {
                result = "Terputus";
                mip.setText(": "+ip);
                mgedung.setText(": "+gedung);

                mstatus.setText(": "+result);
                mstatus.setTextColor(Color.RED);

                mjenisrouter.setText(": "+jenisrouter);
                mssid.setText(": "+ssid);
                mpasswordssid.setText(": "+passwordssid);
                mmac.setText(": "+mac);
                mipremote.setText(": "+ipremote);
                museradmin.setText(": "+useradmin);
                mpassworduseradmin.setText(": "+passworduseradmin);
            }
        }
    }

    private void init() {
        mip = findViewById(R.id.iddetail_ipaddress);
        mgedung = findViewById(R.id.iddetail_lokasi);
        mstatus = findViewById(R.id.iddetail_status);
        mjenisrouter = findViewById(R.id.iddetail_jenisrouter);
        mssid = findViewById(R.id.iddetail_ssid);
        mpasswordssid = findViewById(R.id.iddetail_passwordssid);
        mmac = findViewById(R.id.iddetail_mac);
        mipremote = findViewById(R.id.iddetail_ipremote);
        museradmin = findViewById(R.id.iddetail_useradmin);
        mpassworduseradmin = findViewById(R.id.iddetail_passworduseradmin);
    }

}