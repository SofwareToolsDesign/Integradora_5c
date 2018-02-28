package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapaActivity extends AppCompatActivity {
    Button regresar, menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        //Connect the xml and java buttons
        regresar = (Button) findViewById(R.id.btnmapareg);
        menu = (Button) findViewById(R.id.btnmapamenu);

        //Set the functions to the buttons
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MapaActivity.this, GpsActivity.class);
                startActivityForResult(intento, 0);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MapaActivity.this, MainActivity.class);
                startActivityForResult(intento, 0);
            }
        });
    }
}
