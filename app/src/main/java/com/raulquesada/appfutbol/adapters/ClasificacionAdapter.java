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

/**
 * The type Clasificacion adapter.
 */
public class ClasificacionAdapter extends RecyclerView.Adapter<ClasificacionAdapter.ClasificacionViewHolder> {
    /**
     * The constant tabla.
     */
    private static Tabla tabla;
    /**
     * The Equipos.
     */
    private List<Equipo> equipos;
    /**
     * The Listener.
     */
    private IEquipoClasificacionListener listener;

    /**
     * Instantiates a new Clasificacion adapter.
     *
     * @param tabla    the tabla
     * @param listener the listener
     */
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

    /**
     * The type Clasificacion view holder.
     */
    public static class ClasificacionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         * The Tv posicion clasificacion.
         */
        private TextView tvPosicionClasificacion;
        /**
         * The Tv equipo clasificacion.
         */
        private TextView tvEquipoClasificacion;
        /**
         * The Tv partidos clasificacion.
         */
        private TextView tvPartidosClasificacion;
        /**
         * The Tv goals clasificacion.
         */
        private TextView tvGoalsClasificacion;
        /**
         * The Tv puntos clasificacion.
         */
        private TextView tvPuntosClasificacion;
        /**
         * The Iv shield.
         */
        private ImageView ivShield;
        /**
         * The Listener.
         */
        private IEquipoClasificacionListener listener;
        /**
         * The Equipo.
         */
        private List<Equipo> equipo;

        /**
         * Instantiates a new Clasificacion view holder.
         *
         * @param itemView the item view
         * @param listener the listener
         * @param equipo   the equipo
         */
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

        /**
         * Bind equipo.
         *
         * @param equipo the equipo
         */
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

        /**
         * Cuando el usuario pulsa en un equipo
         * @param v vista
         */
        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onEquipoSeleccionado(equipo.get(getAdapterPosition()));
            }
        }
    }

    /**
     * Comprobacion leyenda.
     *
     * @param equipo the equipo
     * @param tv     the tv
     */
    private static void comprobacionLeyenda(Equipo equipo, TextView tv){
        for (int i = 0; i <tabla.getinfoLeyendas().get(0).getTipoLeyendas().size() ; i++) {
            //En caso de que el equipo tenga leyenda
            if (equipo.getMark().equals(String.valueOf(tabla.getinfoLeyendas().get(0).getTipoLeyendas().get(i).getPos()))){
                switch (tabla.getinfoLeyendas().get(0).getTipoLeyendas().get(i).getClass_color()){
                    case "cha"://Champions League
                        tv.setBackgroundResource(R.color.cha);
                        break;
                    case "uefa"://UEFA Europa League
                        tv.setBackgroundResource(R.color.uefa);
                        break;
                    case "conf"://Conference League
                        tv.setBackgroundResource(R.color.conf);
                        break;
                    case "desc"://Descenso
                        tv.setBackgroundResource(R.color.desc);
                        break;
                    case "asc"://Ascenso
                        tv.setBackgroundResource(R.color.asc);
                        break;
                    case "play"://Play off
                        tv.setBackgroundResource(R.color.play);
                        break;

                }
                tv.setTextColor(Color.rgb(255,255,255));
            }
        }
    }
}
