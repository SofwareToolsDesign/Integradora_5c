package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenusmsActivity extends AppCompatActivity {
    Button grupal,individual,regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menusms);

        //Connect the xml and java buttons
        grupal = (Button) findViewById(R.id.btnsmsgrupal);
        individual = (Button) findViewById(R.id.btnsmsindiv);
        regresar = (Button) findViewById(R.id.btnsmsreg);

        //Set the functions to the buttons
        grupal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MenusmsActivity.this, SmsgrupalActivity.class);
                startActivityForResult(intento,0);
            }
        });
        individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MenusmsActivity.this, EnviarsmsActivity.class);
                startActivityForResult(intento,0);
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MenusmsActivity.this, MainActivity.class);
                startActivityForResult(intento,0);
            }
        });
    }
}
