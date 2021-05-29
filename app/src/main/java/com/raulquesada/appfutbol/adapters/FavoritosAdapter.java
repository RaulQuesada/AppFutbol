package com.raulquesada.appfutbol.adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.listeners.listas.IEquipoClasificacionListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.Lib;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder> {
    private List<Equipo> equipos;
    private List<Partido> partidos;
    private IEquipoClasificacionListener listener;

    public FavoritosAdapter(List<Equipo> equipos, List<Partido> partidos, IEquipoClasificacionListener listener) {
        this.equipos = equipos;
        this.partidos = partidos;
        this.listener=listener;
    }
    @NonNull
    @Override
    public FavoritosAdapter.FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_equipo_favorito,parent,false);
        return new FavoritosViewHolder(itemView, listener, equipos, partidos);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapter.FavoritosViewHolder holder, int position) {
        Equipo equipo = equipos.get(position);
        Partido partido = partidos.get(position);
        holder.bindEquipo(equipo, partido);
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class FavoritosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNomEquipoFavorito;
        private ImageView ivShieldEquipoFavorito;
        private TextView tvPartidoLocalFavorito;
        private TextView tvPartidoVisitorFavorito;
        private TextView tvGoalsLocalFavorito;
        private TextView tvGoalsVisitorFavorito;
        private TextView tvLiveMinuteFavorito;
        private ImageView ivShieldLocalFavorito;
        private ImageView ivShieldVisitorFavorito;
        private IEquipoClasificacionListener listener;
        private List<Equipo> equipo;

        public FavoritosViewHolder(@NonNull View itemView, IEquipoClasificacionListener listener, List<Equipo> equipo, List<Partido> partidos) {
            super(itemView);
            this.tvNomEquipoFavorito = itemView.findViewById(R.id.tvNomEquipoFavorito);
            this.ivShieldEquipoFavorito = itemView.findViewById(R.id.ivShieldEquipoFavorito);
            this.tvPartidoLocalFavorito = itemView.findViewById(R.id.tvPartidoLocalFavorito);
            this.tvPartidoVisitorFavorito = itemView.findViewById(R.id.tvPartidoVisitorFavorito);
            this.tvGoalsLocalFavorito = itemView.findViewById(R.id.tvGoalsLocalFavorito);
            this.tvGoalsVisitorFavorito = itemView.findViewById(R.id.tvGoalsVisitorFavorito);
            this.tvLiveMinuteFavorito = itemView.findViewById(R.id.tvLiveMinutesFavorito);
            this.ivShieldLocalFavorito = itemView.findViewById(R.id.ivPartidoLocalFavorito);
            this.ivShieldVisitorFavorito = itemView.findViewById(R.id.ivPartidoVisitorFavorito);
            this.listener = listener;
            this.equipo = equipo;
            itemView.setOnClickListener(this);
        }

        public void bindEquipo(Equipo equipo, Partido partido) {
            tvNomEquipoFavorito.setText(equipo.getTeam());

            Picasso.get()
                    .load(equipo.getShield())
                    .into(ivShieldEquipoFavorito);

            tvPartidoLocalFavorito.setText(partido.getLocal());
            tvPartidoVisitorFavorito.setText(partido.getVisitor());

            if (partido.getLive_minute().equals("")){
                tvLiveMinuteFavorito.setVisibility(View.GONE);
                if (partido.getLocal_goals().equals("x") || partido.getVisitor_goals().equals("x")){
                    tvGoalsLocalFavorito.setText(Lib.changeFormatDate(partido.getDate()));
                    tvGoalsVisitorFavorito.setText(partido.getHour()+":"+partido.getMinute());
                }else {
                    tvGoalsLocalFavorito.setText(partido.getLocal_goals());
                    tvGoalsVisitorFavorito.setText(partido.getVisitor_goals());

                    if (Integer.parseInt(partido.getLocal_goals())>Integer.parseInt(partido.getVisitor_goals())){
                        tvPartidoLocalFavorito.setTypeface(null, Typeface.BOLD);
                    }else if (Integer.parseInt(partido.getLocal_goals())<Integer.parseInt(partido.getVisitor_goals())){
                        tvPartidoVisitorFavorito.setTypeface(null, Typeface.BOLD);
                    }
                }
            }else {
                tvLiveMinuteFavorito.setText(partido.getLive_minute()+"'");
                tvGoalsLocalFavorito.setText(partido.getLocal_goals());
                tvGoalsVisitorFavorito.setText(partido.getVisitor_goals());
                tvGoalsLocalFavorito.setTextColor(Color.parseColor("#FF0000"));
                tvGoalsVisitorFavorito.setTextColor(Color.parseColor("#FF0000"));
            }


            Picasso.get()
                    .load(partido.getLocal_shield())
                    .into(ivShieldLocalFavorito);

            Picasso.get()
                    .load(partido.getVisitor_shield())
                    .into(ivShieldVisitorFavorito);
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onEquipoSeleccionado(equipo.get(getAdapterPosition()));
            }
        }
    }
}
