package com.tomtomy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class RemoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
    }

    public void ult1(View view) {
        go("http://account.meraki.com/secure/login/dashboard_login");
    }

    public void ult2(View view) {
        go("http://account.meraki.com/secure/login/dashboard_login");
    }

    public void ult3(View view) {
        go("http://account.meraki.com/secure/login/dashboard_login");
    }

    public void bka(View view) {
        go("http://account.meraki.com/secure/login/dashboard_login");
    }

    public void diga(View view) {
        go("http://account.meraki.com/secure/login/dashboard_login");
    }

    public void piblt2p(View view) {
        go("http://192.168.40.74:8080");
    }

    public void piblt2(View view) {
        go("http://192.168.0.61:8080");
    }

    public void piblt1(View view) {
        go("http://192.168.0.134:8080");
    }

    public void pia(View view) {
        go("http://192.168.40.64:8080");
    }

    public void foundry(View view) {
        go("http://192.168.40.154:8080");
    }

    public void go (String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//        finish();
//        super.onBackPressed();
//    }
}
