package com.stdesign.bitacorasutd.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.stdesign.bitacorasutd.R;
import com.stdesign.bitacorasutd.fragments.BitacoraFragment;
import com.stdesign.bitacorasutd.fragments.CuentaFragment;
import com.stdesign.bitacorasutd.fragments.InicioFragment;
import com.stdesign.bitacorasutd.fragments.QrFragment;
import com.stdesign.bitacorasutd.helper.SharedPrefManager;

public class SystemActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    transaction.replace(R.id.content, new InicioFragment()).commit();
                    return true;
                case R.id.navigation_qr:
                    transaction.replace(R.id.content, new QrFragment()).commit();
                    return true;
                case R.id.navigation_bitacora:
                    transaction.replace(R.id.content, new BitacoraFragment()).commit();
                    return true;
                case R.id.navigation_cuenta:
                    transaction.replace(R.id.content, new CuentaFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, new InicioFragment()).commit();
    }

    boolean twice = false;
    public void onBackPressed(){
        if(twice){
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            System.exit(0);
        }
        twice = true;

        Toast.makeText(SystemActivity.this, "Pulse de nuevo para salir",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        },2000);
    }
}