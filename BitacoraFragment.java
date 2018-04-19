package com.stdesign.bitacorasutd.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.stdesign.bitacorasutd.R;
import com.stdesign.bitacorasutd.model.Bitacora;
import com.stdesign.bitacorasutd.model.ResultBitacora;
import com.stdesign.bitacorasutd.model.Schedule;

import java.util.Calendar;

import com.stdesign.bitacorasutd.api.APIService;
import com.stdesign.bitacorasutd.api.APIUrl;
import com.stdesign.bitacorasutd.helper.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BitacoraFragment extends Fragment implements View.OnClickListener{

    private Button btregistrar;
    private View view;
    private EditText etfecha,ethora,etpractica,etmaestro,etequipo_usado,etobservaciones,laboratorio,materia,grupo;
    private RadioButton rbObSi, rbObNo;
    private ImageButton ibfecha,ibhora;
    private int dia, mes, anio, horas, minutos;

    public BitacoraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bitacora, container, false);

        //Bitacora activity components
        btregistrar = view.findViewById(R.id.btnbitregistrar);
        etfecha = view.findViewById(R.id.etfecha);
        ethora = view.findViewById(R.id.ethora);
        etpractica = view.findViewById(R.id.etpractica);
        etmaestro = view.findViewById(R.id.etmaestro);
        etequipo_usado = view.findViewById(R.id.etequipou);
        etobservaciones = view.findViewById(R.id.etobserv);
        ibfecha = view.findViewById(R.id.ibfecha);
        ibhora = view.findViewById(R.id.ibhora);
        laboratorio = view.findViewById(R.id.etlab);
        materia = view.findViewById(R.id.etmateria);
        grupo = view.findViewById(R.id.etgrupo);
        rbObSi = view.findViewById(R.id.rbobsi);
        rbObNo = view.findViewById(R.id.rbobno);

        if(SharedPrefManager.getInstance(getContext()).isQrScanned()){
            Schedule schedule = SharedPrefManager.getInstance(getContext()).getSchedule();
            laboratorio.setText(schedule.getLaboratorio());
            materia.setText(schedule.getMateria());
            grupo.setText(schedule.getGrupo());
            SharedPrefManager.getInstance(getContext()).setQRScanned(false);
        }
    /*
        if(rbObSi.isChecked()){
            etobservaciones.setText("");
            etobservaciones.setVisibility(view.VISIBLE);
        }
        else{
            etobservaciones.setText("NINGUNA");
            etobservaciones.setVisibility(view.INVISIBLE);
        }
*/
        etmaestro.setText(String.valueOf(SharedPrefManager.getInstance(getContext()).getUser().getName()));
        etmaestro.setEnabled(false);

        //Date and hour buttons
        ibfecha.setOnClickListener(this);
        ibhora.setOnClickListener(this);
        //Date and hour textviews
        etfecha.setEnabled(false);
        ethora.setEnabled(false);
        //Register button function
        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarDatos();
            }
        });

        return view;
    }//Close method onCreateView

    @Override
    public void onClick(View view) {
        if(view == ibfecha){
            final Calendar c = Calendar.getInstance();
            anio = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                    etfecha.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                }
            },anio,mes,dia);
            datePickerDialog.show();
        }

        if(view == ibhora){
            final Calendar c = Calendar.getInstance();
            horas = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    ethora.setText(hourOfDay+":"+minute+":00");
                }
            },horas,minutos,false);
            timePickerDialog.show();
        }
    }//Close method onClick

    private void mandarDatos() {
        final ProgressDialog progreso = new ProgressDialog(getContext());
        progreso.setMessage("Registrando...");// Setting Message
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);// Progress Dialog Style Spinner
        progreso.show();
        progreso.setCancelable(false);

        //getting the user values
        String date = etfecha.getText().toString().trim();
        String hour = ethora.getText().toString().trim();
        int lab = Integer.parseInt(laboratorio.getText().toString());
        String subject = materia.getText().toString().trim();
        String group = grupo.getText().toString().trim();
        String practice = etpractica.getText().toString().trim();
        int teacher = Integer.parseInt(etmaestro.getText().toString());
        String equipment = etequipo_usado.getText().toString().trim();
        String observations = etobservaciones.getText().toString().trim();

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        Bitacora bitacora = new Bitacora(date, hour, lab, subject, group, practice, teacher, equipment, observations);

        //defining the call
        Call<ResultBitacora> call = service.registerBitacora(
                bitacora.getDate(),
                bitacora.getHour(),
                bitacora.getLab(),
                bitacora.getSubject(),
                bitacora.getGroup(),
                bitacora.getPractice(),
                bitacora.getTeacher(),
                bitacora.getEquipment(),
                bitacora.getObservations(),
                SharedPrefManager.getInstance(getContext()).getUser().getUs()
        );

        //calling the api
        call.enqueue(new Callback<ResultBitacora>() {
            @Override
            public void onResponse(Call<ResultBitacora> call, Response<ResultBitacora> response) {
                //hiding progress dialog
                progreso.dismiss();

                //if there is no error
                if (!response.body().getError()) {
                    //starting profile activity
                    ethora.setText("");
                    laboratorio.setText("");
                    materia.setText("");
                    grupo.setText("");
                    etpractica.setText("");
                    etequipo_usado.setText("");
                    etobservaciones.setText("");

                    //displaying the message from the response as AlertDialog
                    AlertDialog.Builder construir = new AlertDialog.Builder(getContext());
                    construir.setTitle("Registro");
                    construir.setMessage("Bitácora registrada exitosamente.");
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
                else{
                    //displaying the message from the response as AlertDialog
                    AlertDialog.Builder construir = new AlertDialog.Builder(getContext());
                    construir.setTitle("Registro");
                    construir.setMessage("Revisa que tu información sea correcta y que el tipo de dato sea adecuado para cada campo.");
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
            public void onFailure(Call<ResultBitacora> call, Throwable t) {
                progreso.dismiss();
                AlertDialog.Builder construir = new AlertDialog.Builder(getContext());
                construir.setTitle("Registro");
                construir.setMessage("Algo salió mal. Verifica que los campos estén llenados y que tengas acceso a Internet.");//Algo salió mal. Verifica que los campos estén llenados y que tengas acceso a Internet.
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
    }//Close method mandarDatos
}
