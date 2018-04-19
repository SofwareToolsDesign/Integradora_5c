package com.stdesign.bitacorasutd.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stdesign.bitacorasutd.R;
import com.stdesign.bitacorasutd.api.APIService;
import com.stdesign.bitacorasutd.api.APIUrl;
import com.stdesign.bitacorasutd.helper.SharedPrefManager;
import com.stdesign.bitacorasutd.model.ResultUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button bentrar;
    private EditText etuser, etpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect the java and xml elements
        bentrar = findViewById(R.id.btnentrar);
        etuser = findViewById(R.id.etusu);
        etpass = findViewById(R.id.etcontra);

        //if user is already logged in openeing the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, SystemActivity.class));
        }

        //Set function to the button entrar
        bentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarDatos();
            }
        });
    }//Close method onCreate

    private void comprobarDatos() {
        final ProgressDialog progreso = new ProgressDialog(this);
        progreso.setMessage("Iniciando sesión...");// Setting Message
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);// Progress Dialog Style Horizontal
        progreso.show();
        progreso.setCancelable(false);

        String email = etuser.getText().toString().trim();
        String password = etpass.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultUser> call = service.userLogin(email, password);

        call.enqueue(new Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                progreso.dismiss();
                if (!response.body().getError()) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(MainActivity.this, SystemActivity.class));
                } else {
                    AlertDialog.Builder construir = new AlertDialog.Builder(MainActivity.this);
                    construir.setTitle("Inicio de sesión");
                    construir.setMessage("Usuario o contraseña inválidos.");
                    construir.setCancelable(false);
                    construir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog msj = construir.create();
                    msj.show();
                }
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {
                progreso.dismiss();
                AlertDialog.Builder construir = new AlertDialog.Builder(MainActivity.this);
                construir.setTitle("Inicio de sesión");
                construir.setMessage("Algo salió mal. Verifica que los campos estén llenados y que tengas acceso a Internet.");//"Algo salió mal. Verifica que los campos estén llenados y que tengas acceso a Internet."
                construir.setCancelable(false);
                construir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog msj = construir.create();
                msj.show();
            }
        });
    }//Close method comprobarDatos

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

        Toast.makeText(MainActivity.this, "Pulse de nuevo para salir",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        },2000);
    }//Close method onBackPressed
}
