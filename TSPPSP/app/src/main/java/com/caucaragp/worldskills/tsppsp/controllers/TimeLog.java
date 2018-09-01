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
import android.widget.Toast;

import com.caucaragp.worldskills.tsppsp.R;
import com.caucaragp.worldskills.tsppsp.models.CTimeLog;
import com.caucaragp.worldskills.tsppsp.models.ManageDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeLog extends AppCompatActivity implements View.OnClickListener {

    //Declaramos variables

    EditText txtStart, txtStop, txtInterruption, txtComment, txtDelta;
    Button btnStart, btnStop;
    Spinner spinnerPhase;

    int delta = 0;
    int interrupciones = 0;

    Date dateStart, dateStop;
    private TextView mTextMessage;

    int validar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    inputData();
                    return true;

            }
            return false;
        }
    };

    //Método el cual nos ayuda a limpiar los campos
    private void clean() {
        txtStart.setText("");
        txtStop.setText("");
        txtDelta.setText("");
        txtInterruption.setText("");
        txtComment.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_log);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        inicializamos();
        listarPhse();
        escuchar();
    }

    //Llamar o escuchar los botones
    private void escuchar() {

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    //Creamos metodo para validar que los campos no esten vacios
    private void validar() {

        validar = 0;

        if (txtStart.getText().toString().length() < 0) {

            validar++;
        } else {
            Toast.makeText(this, "Falta ingresar el campo Start", Toast.LENGTH_SHORT).show();
            txtStart.setError("Ingresa esté campo");
        }
        if (txtStop.getText().toString().length() < 0) {

            validar++;
        } else {
            Toast.makeText(this, "Falta ingresar el campo Stop", Toast.LENGTH_SHORT).show();
            txtStop.setError("Ingresa esté campo");
        }
        if (delta >= 0) {
            validar++;
        }else {
            Toast.makeText(this, "El campo delta no puede ser 0", Toast.LENGTH_SHORT).show();
            txtDelta.setError("Esté campo no puede ser menor a 0");
        }


    }

    //Ingresamos o listamos elementos al spinner
    private void listarPhse() {

        List<String> phase = new ArrayList<>();
        phase.add("PLAN");
        phase.add("DLC");
        phase.add("CODE");
        phase.add("COMPILE");
        phase.add("UT");
        phase.add("PM");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, phase);
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
        switch (v.getId()) {

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

        delta = (int) (dateStop.getTime() - dateStart.getTime());
        float tmp1 = delta;
        double tmp2 = tmp1 / 60000 - interrupciones;
        delta = (int) tmp2;
        Toast.makeText(this, "" + delta, Toast.LENGTH_SHORT).show();
        txtDelta.setText(Integer.toString(delta));

    }

    //Este metodo sirve para ver si el usuario ingresa o no interrupciones en el timer
    private void calcularInterrupciones() {

        try {
            interrupciones = Integer.parseInt(txtInterruption.getText().toString());
        } catch (Exception e) {
            txtInterruption.setText("0");
            interrupciones = 0;
        }


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

    //Método para pasar del formulario a la base de datos
    public void inputData(){
        validar();
        if (validar==3) {
            CTimeLog timeLog = new CTimeLog();
            timeLog.setPhase(spinnerPhase.getSelectedItem().toString());
            timeLog.setStart(txtStart.getText().toString());
            timeLog.setStop(txtStop.getText().toString());
            timeLog.setComments(txtComment.getText().toString());
            timeLog.setInterruption(interrupciones);
            timeLog.setDelta(delta);
            timeLog.setProject(MenuPrincipal.proyecto.getId());
            ManageDB manageDB = new ManageDB(this);
            manageDB.insertTimeLog(timeLog);
            Toast.makeText(this, "Se ha guardado en la base de datos correctamene", Toast.LENGTH_SHORT).show();
            clean();
        }

    }

}
