package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsgrupalActivity extends AppCompatActivity {
    Button agenda, menu, borrar, enviar;
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsgrupal);

        //Connect xml and java elements
        agenda = (Button) findViewById(R.id.btngrupagenda);
        menu = (Button) findViewById(R.id.btngrupmenu);
        borrar = (Button) findViewById(R.id.btngrupborrar);
        enviar = (Button) findViewById(R.id.btngrupenviar);

        msg = (EditText) findViewById(R.id.etgrupmsg);

        //Set the functions to the elements
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(SmsgrupalActivity.this, ContactosActivity.class);
                startActivityForResult(intento,0);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(SmsgrupalActivity.this, MainActivity.class);
                startActivityForResult(intento,0);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set empty fields
                msg.setText("");
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
