package com.raulquesada.appfutbol.adapters;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder> {
    private List<Equipo> equipos;
    private IEquipoClasificacionListener listener;

    public FavoritosAdapter(List<Equipo> equipos, IEquipoClasificacionListener listener) {
        this.equipos = equipos;
        this.listener=listener;
    }
    @NonNull
    @Override
    public FavoritosAdapter.FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_equipo_favorito,parent,false);
        return new FavoritosViewHolder(itemView, listener, equipos);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapter.FavoritosViewHolder holder, int position) {
        Equipo equipo = equipos.get(position);
        holder.bindEquipo(equipo);
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class FavoritosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNomEquipoFavorito;
        private ImageView ivShieldEquipoFavorito;
        private IEquipoClasificacionListener listener;
        private List<Equipo> equipo;

        public FavoritosViewHolder(@NonNull View itemView, IEquipoClasificacionListener listener, List<Equipo> equipo) {
            super(itemView);
            this.tvNomEquipoFavorito = itemView.findViewById(R.id.tvNomEquipoFavorito);
            this.ivShieldEquipoFavorito = itemView.findViewById(R.id.ivShieldEquipoFavorito);
            this.listener = listener;
            this.equipo = equipo;
            itemView.setOnClickListener(this);
        }

        public void bindEquipo(Equipo equipo) {
            tvNomEquipoFavorito.setText(equipo.getTeam());

            Picasso.get()
                    .load(equipo.getShield())
                    .into(ivShieldEquipoFavorito);
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onEquipoSeleccionado(equipo.get(getAdapterPosition()));
            }
        }
    }
}
