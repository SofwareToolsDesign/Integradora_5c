package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GpsActivity extends AppCompatActivity {
    Button mapa,posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        //Set the conecction between elements
        mapa = (Button) findViewById(R.id.btngpsmapa);
        posicion = (Button) findViewById(R.id.btngpsposicion);

        //Set the action to the buttons
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GpsActivity.this, MapaActivity.class);
                startActivityForResult(i, 0);
            }
        });
        posicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GpsActivity.this, PosicionActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }
}
