package com.caucaragp.worldskills.tsppsp.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.caucaragp.worldskills.tsppsp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefectLog extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    TextView txtDate, txtFixTime, txtDefectDes;
    Button btnDate, btnGo, btnStop, btnRestart;
    Spinner spinnerType,spinnerPhaseI, spinnerPhaseR;
    boolean bandera, bandera1;
    int[] minutos ={0};

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_log);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        inizialite();
        escucharBotones();
    }

    //Método para inicializar las variables
    private void inizialite() {
        txtDate = findViewById(R.id.txtDate);
        txtFixTime = findViewById(R.id.txtFixTime);
        txtDefectDes = findViewById(R.id.txtDefectDes);

        btnDate = findViewById(R.id.btnDate);
        btnGo = findViewById(R.id.btnGo);
        btnStop = findViewById(R.id.btnStop);
        btnRestart = findViewById(R.id.btnRestart);
        spinnerType = findViewById(R.id.spinnerType);
        spinnerPhaseI = findViewById(R.id.spinnerPhaseI);
        spinnerPhaseR = findViewById(R.id.spinnerPhaseR);
    }

    //Método para escuchar los botones cuando se hace click
    private void escucharBotones() {
        btnDate.setOnClickListener(this);
        btnGo.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnRestart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDate:
                inputDate();
                break;

            case R.id.btnGo:
                break;

            case R.id.btnStop:

                break;

            case R.id.btnRestart:

                break;

        }
    }

    //Método para el ingresar al txtDate la fecha y hora actual
    private void inputDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        txtDate.setText(format.format(date));
    }


}
