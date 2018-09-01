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

import com.caucaragp.worldskills.tsppsp.R;
import com.caucaragp.worldskills.tsppsp.controllers.MenuPrincipal;
import com.caucaragp.worldskills.tsppsp.models.AdapterR;
import com.caucaragp.worldskills.tsppsp.models.ManageDB;


public class DefectsRemoved extends Fragment {

    //Declaración de variables
    View view;
    RecyclerView recyclerView;

    public DefectsRemoved() {
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
        view = inflater.inflate(R.layout.fragment_defects_removed, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewDR);
        inputAdapter();
        return view;
    }

    //Método para ingresar el adapter al recyclerViewDI
    private void inputAdapter() {
        ManageDB manageDB = new ManageDB(getContext());
        AdapterR adapterR = new AdapterR(manageDB.defectsRemoved(MenuPrincipal.proyecto.getId()));
        recyclerView.setAdapter(adapterR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }

}
