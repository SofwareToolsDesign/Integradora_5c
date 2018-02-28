package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactosActivity extends AppCompatActivity {
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        //Connect xml and java elements
        aceptar = (Button) findViewById(R.id.btnagenaceptar);

        //Set the functions to the elements
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ContactosActivity.this, SmsgrupalActivity.class);
                startActivityForResult(intento,0);
            }
        });
    }
}
