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

/**
 * The type Plantilla adapter.
 */
public class PlantillaAdapter extends RecyclerView.Adapter<PlantillaAdapter.PlantillaViewHolder> {
    /**
     * The Jugadors.
     */
    private List<Jugador> jugadors;

    /**
     * Instantiates a new Plantilla adapter.
     *
     * @param jugadors the jugadors
     */
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

    /**
     * The type Plantilla view holder.
     */
    public static class PlantillaViewHolder extends RecyclerView.ViewHolder{
        /**
         * The Tv pais jugador.
         */
        private TextView tvPaisJugador;
        /**
         * The Tv dorsal jugador.
         */
        private TextView tvDorsalJugador;
        /**
         * The Tv posicion jugador.
         */
        private TextView tvPosicionJugador;
        /**
         * The Tv apellidos jugador.
         */
        private TextView tvApellidosJugador;
        /**
         * The Tv nombre jugador.
         */
        private TextView tvNombreJugador;
        /**
         * The Iv jugador plantilla.
         */
        private ImageView ivJugadorPlantilla;

        /**
         * Instantiates a new Plantilla view holder.
         *
         * @param itemView the item view
         */
        public PlantillaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPaisJugador = itemView.findViewById(R.id.tvPaisJugador);
            this.tvPosicionJugador = itemView.findViewById(R.id.tvPosicionJugador);
            this.tvDorsalJugador = itemView.findViewById(R.id.tvDorsalJugador);
            this.tvApellidosJugador = itemView.findViewById(R.id.tvApellidosJugador);
            this.tvNombreJugador = itemView.findViewById(R.id.tvNombreJugador);
            this.ivJugadorPlantilla = itemView.findViewById(R.id.ivJugadorPlantilla);
        }

        /**
         * Bind partido.
         *
         * @param jugador the jugador
         */
        public void bindPartido(Jugador jugador) {
            tvPaisJugador.setText(jugador.getCountryCode());
            tvDorsalJugador.setText(jugador.getSquadNumber());
            tvNombreJugador.setText(jugador.getName());
            tvApellidosJugador.setText(jugador.getLast_name());
            switch (Integer.parseInt(jugador.getRole())){
                case 1://Si el jugador es portero
                    tvPosicionJugador.setText("Portero");
                    break;

                case 2://Si el jugador es defensa
                    tvPosicionJugador.setText("Defensa");
                    break;

                case 3://Si el jugador es centrocampista
                    tvPosicionJugador.setText("Centrocampista");
                    break;

                case 4://Si el jugador es delantero
                    tvPosicionJugador.setText("Delantero");
            }
            Picasso.get()
                    .load(jugador.getImage())
                    .into(ivJugadorPlantilla);

        }
    }
}
