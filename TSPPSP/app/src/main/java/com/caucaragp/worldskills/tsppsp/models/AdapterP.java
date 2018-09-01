package com.caucaragp.worldskills.tsppsp.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caucaragp.worldskills.tsppsp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterP extends RecyclerView.Adapter<AdapterP.Holder> {
    //Declaración de variables
    List<CProyecto> proyectoList = new ArrayList<>();
    private OnItemClickListener mlistener;

    public AdapterP(List<CProyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    //Método para ingresar la variable mlistener
    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    //Interface para la implementación del click a los items
    public interface OnItemClickListener{
        //Método para obtener la posición del item
        void itemClick(int position);
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proyecto,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(proyectoList.get(position));
    }

    @Override
    public int getItemCount() {
        return proyectoList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txtNombreP = itemView.findViewById(R.id.txtNombreP);

        public Holder(View itemView, final OnItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener!=null){
                        int position =getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        //Método para ingresar datos al item
        public void connectData(CProyecto proyecto){
            txtNombreP.setText(proyecto.getName());

        }

    }
}
