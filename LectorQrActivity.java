package com.stdesign.bitacorasutd.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;
import com.stdesign.bitacorasutd.R;
import com.stdesign.bitacorasutd.api.APIService;
import com.stdesign.bitacorasutd.api.APIUrl;
import com.stdesign.bitacorasutd.helper.SharedPrefManager;
import com.stdesign.bitacorasutd.model.ResultSchedule;

import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.CAMERA;

public class LectorQrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView escanerView;
    private String laboratorio="", dia="", hora="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);
        setUpActionBar();

        escanerView = new ZXingScannerView(this);
        setContentView(escanerView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checarPermiso()){
                Toast.makeText(LectorQrActivity.this,"Se han concedido permisos sobre la cámara.",Toast.LENGTH_SHORT).show();
            }//Close if checarPermiso
            else{
                solicitarPermiso();
            }//Close else checarPermiso
        }//Close first IF
        escanerView.setResultHandler(this);
        escanerView.startCamera();
    }//Close onCreate method

    public void setUpActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //Show the up button in the action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Escáner de QR");
        }
    }//Close method setUpActionBar


    private boolean checarPermiso(){//If the permission is granted, it returns true
        return (ContextCompat.checkSelfPermission(LectorQrActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }//Close checarPermiso method

    private void solicitarPermiso(){
        ActivityCompat.requestPermissions(this, new String []{CAMERA}, REQUEST_CAMERA);
    }//Close solicitarPermiso method

    public void onRequestPermissionsResult(int requestCode, String permission[], int grantResults[]){
        switch(requestCode){
            case REQUEST_CAMERA:
                if(grantResults.length > 0){
                    boolean camaraAceptada = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(camaraAceptada){
                        Toast.makeText(LectorQrActivity.this, "Permisos concedidos sobre la cámara.", Toast.LENGTH_SHORT).show();
                    }//Close IF camaraAceptada == true
                    else{
                        Toast.makeText(LectorQrActivity.this, "Permisos denegados sobre la cámara.", Toast.LENGTH_SHORT).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                mostrarMensaje("Se necesita que se acepten los permisos.",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }//Close IF showRequestPermission
                        }//Close IF version SDK is newer
                    }//Close ELSE camaraAceptada == true
                }//close IF grantResults > 0
                break;
        }//Close SWITCH
    }//close onRequestPermissionsResult method

    @Override
    public void onResume(){
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checarPermiso()){
                if(escanerView == null){
                    escanerView = new ZXingScannerView(this);
                    setContentView(escanerView);
                }//Close IF escanerView == null
                escanerView.setResultHandler(this);
                escanerView.startCamera();
            }//Close IF checar permiso
            else{
                solicitarPermiso();
            }//Close ELSE checarPermiso
        }//Close IF Build version higger
    }//Close onResume method

    @Override
    public void onDestroy(){
        super.onDestroy();
        escanerView.stopCamera();
    }//Close onDestroy method

    public void mostrarMensaje(String mensaje,DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(LectorQrActivity.this)
                .setMessage(mensaje)
                .setTitle("Permisos de aplicación:")
                .setPositiveButton("Aceptar",listener)
                .setNegativeButton("Cancelar",listener)
                .create()
                .show();
    }//Close mostrarMensaje method

    //Method on pause the camera
    public void onPause(){
        super.onPause();
        escanerView.stopCamera();
    }
    //Method to show the result
    @Override
    public void handleResult(Result result) {

        laboratorio = result.getText();
        String dateTimeComplete = Calendar.getInstance().getTime().toString();
        //Wed Apr 18 18:11:42 GTM-05:00 2018
        //Wed Apr 18 19:05:57 CDT 2018

        for(int i=0; i<dateTimeComplete.length(); i++){
            if(i<3)dia = dia + dateTimeComplete.charAt(i);
            if(i>10 && i<13) hora = hora + dateTimeComplete.charAt(i);
        }
        //verificarQr();

        AlertDialog.Builder crear = new AlertDialog.Builder(this);
        crear.setTitle("Resultado del código escaneado:");
        crear.setMessage(laboratorio+"\nDía: "+dia+"\nHora: "+hora);
        crear.setCancelable(false);
        crear.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                onPause();
                startActivity(new Intent(LectorQrActivity.this, SystemActivity.class));

            }
        });
        AlertDialog mensaje = crear.create();
        mensaje.show();


    }//Close method handleResult

    public void verificarQr(){
        final ProgressDialog progreso = new ProgressDialog(this);
        progreso.setMessage("Comprobando...");// Setting Message
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);// Progress Dialog Style Horizontal
        progreso.show();
        progreso.setCancelable(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultSchedule> call = service.getScheduleByQr(laboratorio, dia, hora);

        call.enqueue(new Callback<ResultSchedule>() {
            @Override
            public void onResponse(Call<ResultSchedule> call, Response<ResultSchedule> response) {
                progreso.dismiss();
                if (!response.body().getError()) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).setSchedule(response.body().getSchedule());
                    SharedPrefManager.getInstance(getApplicationContext()).setQRScanned(true);
                    startActivity(new Intent(LectorQrActivity.this, SystemActivity.class));
                } else {
                    android.support.v7.app.AlertDialog.Builder construir = new android.support.v7.app.AlertDialog.Builder(LectorQrActivity.this);
                    construir.setTitle("Escáner de Qr");
                    construir.setMessage("Código de laboratorio NO registrado.");
                    construir.setCancelable(false);
                    construir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            onResume();
                        }
                    });
                    android.support.v7.app.AlertDialog msj = construir.create();
                    msj.show();
                }
            }

            @Override
            public void onFailure(Call<ResultSchedule> call, Throwable t) {
                progreso.dismiss();
                android.support.v7.app.AlertDialog.Builder construir = new android.support.v7.app.AlertDialog.Builder(LectorQrActivity.this);
                construir.setTitle("Inicio de sesión");
                construir.setMessage("Algo salió mal. Verifica que los campos estén llenados y que tengas acceso a Internet.");
                construir.setCancelable(false);
                construir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                android.support.v7.app.AlertDialog msj = construir.create();
                msj.show();
            }
        });
    }
}
