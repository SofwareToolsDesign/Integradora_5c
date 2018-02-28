package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnviarsmsActivity extends AppCompatActivity {
    Button menu, borrar, enviar;
    EditText para,msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviarsms);

        //Connect xml and java elements
        menu = (Button) findViewById(R.id.btnindivmenu);
        borrar = (Button) findViewById(R.id.btnindivborrar);
        enviar = (Button) findViewById(R.id.btnindivenviar);

        para = (EditText) findViewById(R.id.etindivpara);
        msg = (EditText) findViewById(R.id.etindivmsg);

        //Set the functions to the elements
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(EnviarsmsActivity.this, MainActivity.class);
                startActivityForResult(intento,0);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                para.setText("");//Set empty fields
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
