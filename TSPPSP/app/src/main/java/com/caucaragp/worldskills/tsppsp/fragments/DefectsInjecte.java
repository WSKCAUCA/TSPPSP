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
import com.caucaragp.worldskills.tsppsp.controllers.MenuProyecto;
import com.caucaragp.worldskills.tsppsp.models.AdapterR;
import com.caucaragp.worldskills.tsppsp.models.ManageDB;


public class DefectsInjecte extends Fragment {

    //Declaración de varables
    View view;
    RecyclerView recyclerView;

    public DefectsInjecte() {
        // Required empty public constructor
    }


    public static DefectsInjecte newInstance(String param1, String param2) {
        DefectsInjecte fragment = new DefectsInjecte();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        view = inflater.inflate(R.layout.fragment_defects_injecte, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewDI);
        inputAdapter();
        return view;
    }

    //Método para ingresar el adapter al recyclerViewDI
    private void inputAdapter() {
        ManageDB manageDB = new ManageDB(getContext());
        AdapterR adapterR = new AdapterR(manageDB.defectsInjected(MenuPrincipal.proyecto.getId()));
        recyclerView.setAdapter(adapterR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }


}
