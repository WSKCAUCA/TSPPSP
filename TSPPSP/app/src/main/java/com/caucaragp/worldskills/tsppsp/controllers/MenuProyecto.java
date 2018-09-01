package com.caucaragp.worldskills.tsppsp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.caucaragp.worldskills.tsppsp.R;

public class MenuProyecto extends AppCompatActivity implements View.OnClickListener{
    //Declaramos Elementos
    Button btnTime, btnDefect,btnProjectPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyecto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        inicializar();
        escuchar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Metodo que nos ayuda a que todos los botones tenga funcionalidad
    private void escuchar() {
        btnDefect.setOnClickListener(this);
        btnProjectPS.setOnClickListener(this);
        btnTime.setOnClickListener(this);
    }

    //Referenciamos los elementos que tiene el layout
    private void inicializar() {

        btnTime = findViewById(R.id.btnTime);
        btnProjectPS = findViewById(R.id.btnPlanPS);
        btnDefect = findViewById(R.id.btnDefect);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnTime:

                Intent intent = new Intent(MenuProyecto.this, TimeLog.class);
                startActivity(intent);

                break;


            case R.id.btnDefect:

                Intent intent1 = new Intent(MenuProyecto.this, DefectLog.class);
                startActivity(intent1);


                break;


            case R.id.btnPlanPS:

                Intent intent2 = new Intent(MenuProyecto.this, ProjectPlanSummary.class);
                startActivity(intent2);

                break;

        }
    }
}
