package com.caucaragp.worldskills.tsppsp.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.caucaragp.worldskills.tsppsp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeLog extends AppCompatActivity implements View.OnClickListener{

    //Declaramos variables

    EditText txtStart, txtStop, txtInterruption, txtComment, txtDelta;
    Button btnStart, btnStop;
    Spinner spinnerPhase;

    int delta = 0;
    int interrupciones = 0;

    Date dateStart, dateStop;
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
        setContentView(R.layout.activity_time_log);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        inicializamos();
        listarPhse();
        validar();
        escuchar();
    }

    //Llamar o escuchar los botones
    private void escuchar() {

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    //Creamos metodo para validar que los campos no esten vacios
    private void validar() {

        int validar = 0;

        if (txtStart.getText().toString().length()< 0){

            validar++;
        }else {
            txtStart.setError("Ingresa esté campo");
        }
        if (txtStop.getText().toString().length()< 0){

            validar++;
        }else {
            txtStop.setError("Ingresa esté campo");
        }
        if (delta >= 0){

            txtDelta.setError("Ingresa esté campo");
        }



    }

    //Ingresamos o listamos elementos al spinner
    private void listarPhse() {

        List<String>phase = new ArrayList<>();
        phase.add("PLAN");
        phase.add("DLC");
        phase.add("CODE");
        phase.add("COMPILE");
        phase.add("UT");
        phase.add("PM");

        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, phase);
        spinnerPhase.setAdapter(adapter);
    }


    //Referenciamos los elementos que tiene el layout
    private void inicializamos() {

        txtStart = findViewById(R.id.txtStart);
        txtStop = findViewById(R.id.txtStop);
        txtInterruption = findViewById(R.id.txtInterruption);
        txtDelta = findViewById(R.id.txtDelta);
        txtComment = findViewById(R.id.txtComments);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStopt);

        spinnerPhase = findViewById(R.id.spinnerPhase);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnStart:

                obtenerHora();
                break;

            case R.id.btnStopt:

                obtenerHora2();
                calcularInterrupciones();
                calcularDelta();

                break;

        }
    }

    //Calculamos delta que es el tiempo que total que se demoro en el timer
    private void calcularDelta() {



    }

    //Este metodo sirve para ver si el usuario ingresa o no interrupciones en el timer
    private void calcularInterrupciones() {





    }


    //Obtenemos la hora y fecha del dispositivo
    private void obtenerHora2() {
        dateStop = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha1 = fecha.format(dateStop);
        txtStop.setText(fecha1);

    }

    //Obtenemos la hora y fecha del dispositivo
    private void obtenerHora() {
        dateStart = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha1 = fecha.format(dateStart);
        txtStart.setText(fecha1);
    }
}
