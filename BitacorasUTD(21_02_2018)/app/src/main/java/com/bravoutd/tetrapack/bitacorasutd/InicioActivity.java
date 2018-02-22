package com.bravoutd.tetrapack.bitacorasutd;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Spinner lenguage = (Spinner) findViewById(R.id.spinlenguage);
        String[] idiomas = {"Español","Inglés"};
        lenguage.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idiomas));

        TabHost th;
        //Creación del objeto para relacionarlo con el objeto de xml
        th = (TabHost) findViewById(R.id.thmenu);
        th.setup();

        //Dar nombre idenificador a las Tabs
        TabHost.TabSpec ts1=th.newTabSpec("Inicio");
        TabHost.TabSpec ts2=th.newTabSpec("QR");
        TabHost.TabSpec ts3=th.newTabSpec("Bit");
        TabHost.TabSpec ts4=th.newTabSpec("Cuenta");

        //Poner imagen en cada una de las Tabs
        ts1.setIndicator("",getResources().getDrawable(R.drawable.ic_home_white_24dp));
        ts2.setIndicator("",getResources().getDrawable(R.drawable.whiteqr));
        ts3.setIndicator("",getResources().getDrawable(R.drawable.ic_assignment_white_24dp));
        ts4.setIndicator("",getResources().getDrawable(R.drawable.ic_account_circle_white_24dp));

        //Poner contenido en cada una de las Tabs
        ts1.setContent(R.id.tabin);
        ts2.setContent(R.id.tabqr);
        ts3.setContent(R.id.tabbi);
        ts4.setContent(R.id.tabcu);

        //Agregar tabs
        th.addTab(ts1);
        th.addTab(ts2);
        th.addTab(ts3);
        th.addTab(ts4);

    }

}
