package com.bravoutd.stdesign.a5bpadillahiram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button regresar, limpiar, entrar;
    EditText usuario, contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Connect xml and java elements
        regresar = (Button) findViewById(R.id.btnlogreg);
        limpiar = (Button) findViewById(R.id.btnloglimpiar);
        entrar = (Button) findViewById(R.id.btnlogentrar);

        usuario = (EditText) findViewById(R.id.etlogusuario);
        contra = (EditText) findViewById(R.id.etlogcontra);

        //Set the functions to the elements
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(intento,0);
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setText("");//Set empty fields
                contra.setText("");
            }
        });
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
