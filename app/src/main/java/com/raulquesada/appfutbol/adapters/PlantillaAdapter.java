package com.raulquesada.appfutbol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.models.Jugador;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlantillaAdapter extends RecyclerView.Adapter<PlantillaAdapter.PlantillaViewHolder> {
    private List<Jugador> jugadors;

    public PlantillaAdapter(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }
    @NonNull
    @Override
    public PlantillaAdapter.PlantillaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_plantilla,parent,false);
        return new PlantillaAdapter.PlantillaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantillaAdapter.PlantillaViewHolder holder, int position) {
        Jugador jugador = jugadors.get(position);
        holder.bindPartido(jugador);
    }

    @Override
    public int getItemCount() {
        return jugadors.size();
    }

    public static class PlantillaViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPaisJugador;
        private TextView tvDorsalJugador;
        private TextView tvPosicionJugador;
        private TextView tvApellidosJugador;
        private TextView tvNombreJugador;
        private ImageView ivJugadorPlantilla;

        public PlantillaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPaisJugador = itemView.findViewById(R.id.tvPaisJugador);
            this.tvPosicionJugador = itemView.findViewById(R.id.tvPosicionJugador);
            this.tvDorsalJugador = itemView.findViewById(R.id.tvDorsalJugador);
            this.tvApellidosJugador = itemView.findViewById(R.id.tvApellidosJugador);
            this.tvNombreJugador = itemView.findViewById(R.id.tvNombreJugador);
            this.ivJugadorPlantilla = itemView.findViewById(R.id.ivJugadorPlantilla);
        }

        public void bindPartido(Jugador jugador) {
            tvPaisJugador.setText(jugador.getCountryCode());
            tvDorsalJugador.setText(jugador.getSquadNumber());
            tvNombreJugador.setText(jugador.getName());
            tvApellidosJugador.setText(jugador.getLast_name());
            switch (Integer.parseInt(jugador.getRole())){
                case 1:
                    tvPosicionJugador.setText("Portero");
                    break;

                case 2:
                    tvPosicionJugador.setText("Defensa");
                    break;

                case 3:
                    tvPosicionJugador.setText("Centrocampista");
                    break;

                case 4:
                    tvPosicionJugador.setText("Delantero");
            }
            Picasso.get()
                    .load(jugador.getImage())
                    .into(ivJugadorPlantilla);

        }
    }
}
