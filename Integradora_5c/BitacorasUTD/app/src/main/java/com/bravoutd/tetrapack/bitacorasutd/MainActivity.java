package com.bravoutd.tetrapack.bitacorasutd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConexionBD c;
    EditText us, ps;
    Button entrar;
    String sql = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        us = (EditText)findViewById(R.id.etuser);
        ps = (EditText)findViewById(R.id.etpass);
        entrar = (Button) findViewById(R.id.btnentrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //entablar conexión
                c = new ConexionBD();
                try
                {   c.conectar();
                    sql = "SELECT * FROM usuarios WHERE usern='"+us.getText()+"' and passw='"+ps.getText()+"'";
                    c.rs = c.sentencia.executeQuery(sql);

                    if(c.rs.next())
                    {Intent entrar = new Intent(MainActivity.this, InicioActivity.class); startActivity(entrar);}

                    else
                    {Toast.makeText(MainActivity.this,"Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"También compruebe su conexión a Internet", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e1){e1.getMessage();}

            }
        });

    }
}
