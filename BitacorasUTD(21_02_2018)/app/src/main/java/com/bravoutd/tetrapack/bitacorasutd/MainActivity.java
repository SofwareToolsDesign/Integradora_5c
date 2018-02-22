package com.bravoutd.tetrapack.bitacorasutd;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrar = (Button)findViewById(R.id.btnentrar);
        entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent iraInicio = new Intent(MainActivity.this, InicioActivity.class);
                startActivity(iraInicio);
            }
        });
    }
}
