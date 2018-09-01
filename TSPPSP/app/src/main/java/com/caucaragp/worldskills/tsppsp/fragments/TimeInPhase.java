package com.caucaragp.worldskills.tsppsp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caucaragp.worldskills.tsppsp.R;
import com.caucaragp.worldskills.tsppsp.controllers.MenuPrincipal;
import com.caucaragp.worldskills.tsppsp.models.AdapterR;
import com.caucaragp.worldskills.tsppsp.models.CProyecto;
import com.caucaragp.worldskills.tsppsp.models.ManageDB;


public class TimeInPhase extends Fragment {

    //Declaración de variables
    View view;
    RecyclerView recyclerView;
    EditText txtTiempo;
    Button btnGuardar;
    public TimeInPhase() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_time_in_phase, container, false);
        inizialite();
        inputAdapter();
        inputTime();
        escucharBoton();
        return view;
    }

    //Método para inicializar las variables
    private void inizialite() {
        recyclerView = view.findViewById(R.id.recyclerView);
        txtTiempo = view.findViewById(R.id.txtTiempo);
        btnGuardar = view.findViewById(R.id.btnGuardar);
    }

    //Método para ingresar el adaptador al recyclerView
    private void inputAdapter() {
        ManageDB manageDB = new ManageDB(getContext());
        AdapterR adapterR = new AdapterR(manageDB.timeInPhase(MenuPrincipal.proyecto.getId()));
        recyclerView.setAdapter(adapterR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }

    //Método para ingresar el tiempo guardado
    private void inputTime() {
        txtTiempo.setText(Integer.toString(MenuPrincipal.proyecto.getTime()));

    }

    //Método para ecuchar el boton guardar que permitirá actualizar el tiempo del proyecto
    private void escucharBoton() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MenuPrincipal.proyecto.getTime()==0){
                    try {
                        int tiempo = Integer.parseInt(txtTiempo.getText().toString());
                        if (tiempo>0){
                            ManageDB manageDB = new ManageDB(getContext());
                            MenuPrincipal.proyecto.setTime(tiempo);
                            manageDB.updateProject(MenuPrincipal.proyecto);
                            inputAdapter();
                            Toast.makeText(getContext(), "Se ha agregado el tiempo del proyecto correctamente", Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(getContext(), "Coloque un valor mayor a 0", Toast.LENGTH_SHORT).show();
                            txtTiempo.setError("Por favor Coloque un valor mayor a 0");
                        }
                    }catch (Exception e){
                        Toast.makeText(getContext(), "No puedes dejar el campo txtTiempo vacio", Toast.LENGTH_SHORT).show();
                        txtTiempo.setError("Por favor no deje este campo vacio");
                    }

                }else {
                    Toast.makeText(getContext(), "Ya no se puede actualizar el tiempo del proyecto, solo se permite una vez por proyecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
