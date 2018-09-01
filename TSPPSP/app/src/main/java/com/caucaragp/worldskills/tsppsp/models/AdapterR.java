package com.caucaragp.worldskills.tsppsp.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caucaragp.worldskills.tsppsp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterR extends RecyclerView.Adapter<AdapterR.Holder> {
    //Declaración de variables
    List<Results> resultsList = new ArrayList<>();

    public AdapterR(List<Results> resultsList) {
        this.resultsList = resultsList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_results,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(resultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    //Clase la cual nos permite la creación y manipulacion del item
    public class Holder extends RecyclerView.ViewHolder {

        TextView txtPhase = itemView.findViewById(R.id.txtPhase);
        TextView txtValor = itemView.findViewById(R.id.txtValor);
        TextView txtValor2 = itemView.findViewById(R.id.txtValor2);

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        //Método para ingresar datos al item
        public void connectData(Results results){
            txtPhase.setText(results.getPhase());
            txtValor.setText(Integer.toString(results.getTime()));
            txtValor2.setText(results.getPercent()+"%");

        }

    }
}
