package com.tomtomy.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tomtomy.myapplication.adaptor.PingAdaptor;
import com.tomtomy.myapplication.model.PingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements PingAdaptor.OnItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_IP = "ip";
    public static final String EXTRA_LOKASI = "lokasi";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_JENISROUTER = "jenisrouter";
    public static final String EXTRA_SSID = "ssid";
    public static final String EXTRA_PASSWORDSSID = "passwordssid";
    public static final String EXTRA_MAC = "mac";
    public static final String EXTRA_IPR = "ipr";
    public static final String EXTRA_USERADMIN = "useradmin";
    public static final String EXTRA_PASSWORDUSERADMIN = "passworduseradmin";

    TextView etTvip;
    String ip;
    public ProgressBar p_Tunggu;

    private RecyclerView mRecyclerView;
    private PingAdaptor mPingAdaptor;
    private ArrayList<PingModel> mPingModel;
    private RequestQueue mRequestQueue;
    private static String et_json_url = "http://192.168.111.111:5000/app/key";
    private Handler mRepeatHandler;
    private Runnable mRepeatRunnable;
    private final static int UPDATE_INTERVAL = 30000;

    private final static int KODEINTERNET = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        checkReadExternalStoragePermission();

        WifiManager wm= (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        etTvip.setText(ip);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mPingModel = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        parseJSON();

    }

    private void parseJSON() {
        mPingModel.clear();
        p_Tunggu.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        JsonObjectRequest stringRequest= new JsonObjectRequest(Request.Method.GET, et_json_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject result = jsonArray.getJSONObject(i);

                                String ip_address = result.getString("ip_address");
                                String lokasi = result.getString("lokasi");
                                String status = result.getString("status");
                                String jenisrouter = result.getString("jenis_perangkat");
                                String ssid = result.getString("ssid");
                                String passwordssid = result.getString("password_ssid");
                                String mac = result.getString("mac");
                                String ipremote = result.getString("ip_web");
                                String useradmin = result.getString("u_a");
                                String passworduseradmin = result.getString("p_a");

                                mPingModel.add(new PingModel(ip_address, lokasi, status, jenisrouter, ssid, passwordssid, mac, ipremote, useradmin, passworduseradmin));
                            }
                            p_Tunggu.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            mPingAdaptor = new PingAdaptor(MainActivity.this, mPingModel);
                            mRecyclerView.setAdapter(mPingAdaptor);
                            mPingAdaptor.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Maaf " + e.toString(), Toast.LENGTH_SHORT).show();
                            p_Tunggu.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Gagal " + error.toString(), Toast.LENGTH_SHORT).show();
                p_Tunggu.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        mRequestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 60000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 60000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
    }

    private void checkReadExternalStoragePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED) {
            } else {
                if(shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
                    Toast.makeText(this, "App needs to view permission", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[] {Manifest.permission.INTERNET},
                        KODEINTERNET);
            }
        } else {
            // Start cursor loader
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case KODEINTERNET:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    void init(){
        etTvip = findViewById(R.id.idtvip);
        p_Tunggu = findViewById(R.id.idtunggum);
    }



//    @Override
//    protected void onStop() {
//        finish();
//        super.onStop();
//    }
//
//    @Override
//    protected void onPause() {
//        finish();
//        super.onPause();
//    }

    public void imgremote(View view) {
        Intent intent = new Intent(this,RemoteActivity.class);
        startActivity(intent);
    }

    public void imgtestping(View view) {
        Intent intent = new Intent(this, TestPingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent detail = new Intent(this, DetailApActivity.class);
        PingModel clicked = mPingModel.get(position);

        detail.putExtra(EXTRA_IP, clicked.getIp());
        detail.putExtra(EXTRA_LOKASI, clicked.getGedung());
        detail.putExtra(EXTRA_STATUS, clicked.getStatus());
        detail.putExtra(EXTRA_JENISROUTER, clicked.getJenisrouter());
        detail.putExtra(EXTRA_SSID, clicked.getSsid());
        detail.putExtra(EXTRA_PASSWORDSSID, clicked.getPasswordssid());
        detail.putExtra(EXTRA_IPR, clicked.getIpremote());
        detail.putExtra(EXTRA_MAC, clicked.getMac());
        detail.putExtra(EXTRA_PASSWORDUSERADMIN, clicked.getPassworduseradmin());
        detail.putExtra(EXTRA_USERADMIN, clicked.getUseradmin());

        startActivity(detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idrefresh:
                parseJSON();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
