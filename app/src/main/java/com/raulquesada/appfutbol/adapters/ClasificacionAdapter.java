package com.raulquesada.appfutbol.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.listeners.listas.IEquipoClasificacionListener;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Tabla;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClasificacionAdapter extends RecyclerView.Adapter<ClasificacionAdapter.ClasificacionViewHolder> {
    private static Tabla tabla;
    private List<Equipo> equipos;
    private IEquipoClasificacionListener listener;

    public ClasificacionAdapter(Tabla tabla, IEquipoClasificacionListener listener) {
        this.tabla=tabla;
        this.equipos = tabla.getEquipos();
        this.listener=listener;
    }
    @NonNull
    @Override
    public ClasificacionAdapter.ClasificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_clasificacion,parent,false);
        return new ClasificacionViewHolder(itemView, listener, equipos);
    }

    @Override
    public void onBindViewHolder(@NonNull ClasificacionAdapter.ClasificacionViewHolder holder, int position) {
        Equipo equipo = equipos.get(position);
        holder.bindEquipo(equipo);
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class ClasificacionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvPosicionClasificacion;
        private TextView tvEquipoClasificacion;
        private TextView tvPartidosClasificacion;
        private TextView tvGoalsClasificacion;
        private TextView tvPuntosClasificacion;
        private ImageView ivShield;
        private IEquipoClasificacionListener listener;
        private List<Equipo> equipo;

        public ClasificacionViewHolder(@NonNull View itemView, IEquipoClasificacionListener listener, List<Equipo> equipo) {
            super(itemView);
            this.tvPosicionClasificacion = itemView.findViewById(R.id.tvPosicionClasificacion);
            this.tvEquipoClasificacion = itemView.findViewById(R.id.tvEquipoClasificacion);
            this.tvPartidosClasificacion = itemView.findViewById(R.id.tvPartidosClasificacion);
            this.tvGoalsClasificacion = itemView.findViewById(R.id.tvGoalClasificacion);
            this.tvPuntosClasificacion = itemView.findViewById(R.id.tvPuntosClasificacion);
            this.ivShield = itemView.findViewById(R.id.ivShield);
            this.listener = listener;
            this.equipo = equipo;
            itemView.setOnClickListener(this);
        }

        public void bindEquipo(Equipo equipo) {
            tvPosicionClasificacion.setText(equipo.getPos());
            tvEquipoClasificacion.setText(equipo.getTeam());
            tvPartidosClasificacion.setText(String.valueOf(equipo.getPlayedMatches()));
            tvGoalsClasificacion.setText(equipo.getGf()+":"+ equipo.getGa());
            tvPuntosClasificacion.setText(equipo.getPoints());

            Picasso.get()
                    .load(equipo.getShield())
                    .into(ivShield);

            comprobacionLeyenda(equipo, tvPosicionClasificacion);
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onEquipoSeleccionado(equipo.get(getAdapterPosition()));
            }
        }
    }
    private static void comprobacionLeyenda(Equipo equipo, TextView tv){
        for (int i = 0; i <tabla.getinfoLeyendas().get(0).getTipoLeyendas().size() ; i++) {
            if (equipo.getMark().equals(String.valueOf(tabla.getinfoLeyendas().get(0).getTipoLeyendas().get(i).getPos()))){
                switch (tabla.getinfoLeyendas().get(0).getTipoLeyendas().get(i).getClass_color()){
                    case "cha":
                        tv.setBackgroundResource(R.color.cha);
                        break;
                    case "uefa":
                        tv.setBackgroundResource(R.color.uefa);
                        break;
                    case "conf":
                        tv.setBackgroundResource(R.color.conf);
                        break;
                    case "desc":
                        tv.setBackgroundResource(R.color.desc);
                        break;
                    case "asc":
                        tv.setBackgroundResource(R.color.asc);
                        break;
                    case "play":
                        tv.setBackgroundResource(R.color.play);
                        break;

                }
                tv.setTextColor(Color.rgb(255,255,255));
            }
        }
    }
}
