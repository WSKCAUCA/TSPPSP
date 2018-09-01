package com.caucaragp.worldskills.tsppsp.controllers;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caucaragp.worldskills.tsppsp.controllers.MenuProyecto;
import com.caucaragp.worldskills.tsppsp.models.AdapterP;
import com.caucaragp.worldskills.tsppsp.models.CProyecto;
import com.caucaragp.worldskills.tsppsp.models.ManageDB;

import java.util.List;

import com.caucaragp.worldskills.tsppsp.R;

public class MenuPrincipal extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton btnAgregar;
    public static CProyecto proyecto = new CProyecto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        inizialite();
        escucharBoton();
        inputAdapter();
    }


    //Método para inicializar las variables de la clase MenuPrincipal
    private void inizialite() {
        recyclerView = findViewById(R.id.recyclerView);
        btnAgregar = findViewById(R.id.btnAgregar);

    }

    //Método para escuchar los botones cuando se hace click
    private void escucharBoton() {
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MenuPrincipal.this);
                dialog.setContentView(R.layout.item_agregar);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final EditText txtNombre = dialog.findViewById(R.id.txtNombre);
                Button btnGuardar = dialog.findViewById(R.id.btnGuardar);
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombre = txtNombre.getText().toString();
                        if (nombre.length()>0){
                            ManageDB manageDB = new ManageDB(MenuPrincipal.this);
                            CProyecto proyecto = new CProyecto();
                            proyecto.setName(nombre);
                            proyecto.setTime(0);
                            manageDB.insertProject(proyecto);
                            Toast.makeText(MenuPrincipal.this, "Se ha guaardado correctamene", Toast.LENGTH_SHORT).show();
                            inputAdapter();
                            dialog.cancel();
                        }else {
                            Toast.makeText(MenuPrincipal.this, "Por favor no deje el campo vacio", Toast.LENGTH_SHORT).show();
                            txtNombre.setError("Este campo no puede ir incompleto");
                        }
                    }
                });

                dialog.show();
            }
        });
    }


    //Método para ingresar el adapterP al recyclerView
    private void inputAdapter() {
        ManageDB manageDB = new ManageDB(this);
        final List<CProyecto> proyectoList = manageDB.proyectoList();
        AdapterP adapterP = new AdapterP(proyectoList);
        recyclerView.setAdapter(adapterP);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterP.setMlistener(new AdapterP.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                proyecto = proyectoList.get(position);
                Intent intent = new Intent(MenuPrincipal.this, MenuProyecto.class);
                startActivity(intent);
            }
        });

    }

}
