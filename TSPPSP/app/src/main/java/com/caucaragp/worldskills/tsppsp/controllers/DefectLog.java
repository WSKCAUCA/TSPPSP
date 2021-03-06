package com.caucaragp.worldskills.tsppsp.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.caucaragp.worldskills.tsppsp.R;
import com.caucaragp.worldskills.tsppsp.models.CDefectLog;
import com.caucaragp.worldskills.tsppsp.models.CTimeLog;
import com.caucaragp.worldskills.tsppsp.models.ManageDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefectLog extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    TextView txtDate, txtFixTime, txtDefectDes;
    Button btnDate, btnGo, btnStop, btnRestart;
    Spinner spinnerType,spinnerPhaseI, spinnerPhaseR;
    boolean bandera =true, bandera1=false;
    int[] tiempo ={0,0};

    int validar;
    private TextView mTextMessage;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_log);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        inizialite();
        escucharBotones();
        chronometer();
        listarSpinners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Método que nos ayuda a limpiar los campos
    private void clean() {
        txtDate.setText("");
        txtFixTime.setText("");
    }

    //Método para validar los campos que no estén vacios
    private void validacion() {
        validar = 0;
        if (txtDate.getText().toString().length()>0){
            validar++;
        }else {
            txtDate.setError("Falta esté campo");
        }
        if (txtFixTime.getText().toString().length()>0){
            validar++;
        }else {
            txtFixTime.setError("Falta esté campo");
        }
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
                bandera1=true;
                Toast.makeText(this, "El tiempo empezo a correr en minutos", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnStop:
                bandera1=false;
                Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnRestart:
                bandera1=false;
                tiempo[0]=0;
                tiempo[1]=0;
                Toast.makeText(this, "Reinicio cronometro", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //Método para el ingresar al txtDate la fecha y hora actual
    private void inputDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        txtDate.setText(format.format(date));
    }

    //Método para correr el fixTime
    private void chronometer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1000);

                    }catch (Exception e){

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bandera1) {
                                tiempo[0]++;
                                if (tiempo[0]==60){
                                    tiempo[1]++;
                                }
                                txtFixTime.setText(Integer.toString(tiempo[1]));

                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    //Ingresamos o listamos elementos al spinner
    private void listarSpinners() {

        List<String> phase = new ArrayList<>();
        phase.add("PLAN");
        phase.add("DLD");
        phase.add("CODE");
        phase.add("COMPILE");
        phase.add("UT");
        phase.add("PM");
        List<String> type = new ArrayList<>();
        type.add("Documentation");
        type.add("Syntax");
        type.add("Build");
        type.add("Package");
        type.add("Assigment");
        type.add("Interface");
        type.add("Checking");
        type.add("Data");
        type.add("Fuction");
        type.add("System");
        type.add("Environment");

        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, phase);
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, type);
        spinnerPhaseR.setAdapter(adapter);
        spinnerPhaseI.setAdapter(adapter);
        spinnerType.setAdapter(adapter1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera1=false;
        bandera=false;
    }

    //Método para pasar del formulario a la base de datos
    public void inputData(){
        validacion();
        if (validar==2) {
            CDefectLog defectLog = new CDefectLog();
            defectLog.setDate(txtDate.getText().toString());
            defectLog.setDefectD(txtDefectDes.getText().toString());
            defectLog.setFixTime(tiempo[1]);
            defectLog.setPhaseI(spinnerPhaseI.getSelectedItem().toString());
            defectLog.setPhaseR(spinnerPhaseR.getSelectedItem().toString());
            defectLog.setType(spinnerType.getSelectedItem().toString());
            defectLog.setProject(MenuPrincipal.proyecto.getId());
            ManageDB manageDB = new ManageDB(this);
            manageDB.insertDefectLog(defectLog);
            Toast.makeText(this, "Se ha guardado en la base de datos correctamene", Toast.LENGTH_SHORT).show();
            tiempo[0]=0;
            tiempo[1]=0;
            bandera1=false;
            clean();
        }

    }


}
