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

import com.raulquesada.appfutbol.listeners.listas.IPartidoJornadaListener;
import com.raulquesada.appfutbol.util.Lib;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.models.Partido;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * The type Jornada adapter.
 */
public class JornadaAdapter extends RecyclerView.Adapter<JornadaAdapter.JornadaViewHolder> {
    /**
     * The Partidos.
     */
    private List<Partido> partidos;
    /**
     * The Listener.
     */
    private IPartidoJornadaListener listener;

    /**
     * Instantiates a new Jornada adapter.
     *
     * @param partidos the partidos
     * @param listener the listener
     */
    public JornadaAdapter(List<Partido> partidos, IPartidoJornadaListener listener) {
        this.partidos = partidos;
        this.listener=listener;
    }
    @NonNull
    @Override
    public JornadaAdapter.JornadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_partido,parent,false);
        return new JornadaAdapter.JornadaViewHolder(itemView, listener, partidos);
    }

    @Override
    public void onBindViewHolder(@NonNull JornadaAdapter.JornadaViewHolder holder, int position) {
        Partido partido = partidos.get(position);
        holder.bindPartido(partido);
    }

    @Override
    public int getItemCount() {
        return partidos.size();
    }

    /**
     * The type Jornada view holder.
     */
    public static class JornadaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         * The Tv partido local.
         */
        private TextView tvPartidoLocal;
        /**
         * The Tv partido visitor.
         */
        private TextView tvPartidoVisitor;
        /**
         * The Tv goals local.
         */
        private TextView tvGoalsLocal;
        /**
         * The Tv goals visitor.
         */
        private TextView tvGoalsVisitor;
        /**
         * The Tv live minute.
         */
        private TextView tvLiveMinute;
        /**
         * The Iv shield local.
         */
        private ImageView ivShieldLocal;
        /**
         * The Iv shield visitor.
         */
        private ImageView ivShieldVisitor;
        /**
         * The Listener.
         */
        private IPartidoJornadaListener listener;
        /**
         * The Partidos.
         */
        private List<Partido> partidos;

        /**
         * Instantiates a new Jornada view holder.
         *
         * @param itemView the item view
         * @param listener the listener
         * @param partidos the partidos
         */
        public JornadaViewHolder(@NonNull View itemView, IPartidoJornadaListener listener, List<Partido> partidos) {
            super(itemView);
            this.tvPartidoLocal = itemView.findViewById(R.id.tvPartidoLocal);
            this.tvPartidoVisitor = itemView.findViewById(R.id.tvPartidoVisitor);
            this.tvGoalsLocal = itemView.findViewById(R.id.tvGoalsLocal);
            this.tvGoalsVisitor = itemView.findViewById(R.id.tvGoalsVisitor);
            this.tvLiveMinute = itemView.findViewById(R.id.tvLiveMinutes);
            this.ivShieldLocal = itemView.findViewById(R.id.ivPartidoLocal);
            this.ivShieldVisitor = itemView.findViewById(R.id.ivPartidoVisitor);
            this.listener = listener;
            this.partidos = partidos;
            itemView.setOnClickListener(this);
        }

        /**
         * Bind partido.
         *
         * @param partido the partido
         */
        public void bindPartido(Partido partido) {
            tvPartidoLocal.setText(partido.getLocal());
            tvPartidoVisitor.setText(partido.getVisitor());

            //Si el partido esta en directo
            if (partido.getLive_minute().equals("")){
                tvLiveMinute.setVisibility(View.GONE);
                if (partido.getLocal_goals().equals("x") || partido.getVisitor_goals().equals("x")){
                    tvGoalsLocal.setText(Lib.changeFormatDate(partido.getDate()));
                    tvGoalsVisitor.setText(partido.getHour()+":"+partido.getMinute());
                }else {
                    tvGoalsLocal.setText(partido.getLocal_goals());
                    tvGoalsVisitor.setText(partido.getVisitor_goals());

                    if (Integer.parseInt(partido.getLocal_goals())>Integer.parseInt(partido.getVisitor_goals())){
                        tvPartidoLocal.setTypeface(null, Typeface.BOLD);
                    }else if (Integer.parseInt(partido.getLocal_goals())<Integer.parseInt(partido.getVisitor_goals())){
                        tvPartidoVisitor.setTypeface(null, Typeface.BOLD);
                    }
                }
                //Si no esta en directo
            }else {
                tvLiveMinute.setText(partido.getLive_minute()+"'");
                tvGoalsLocal.setText(partido.getLocal_goals());
                tvGoalsVisitor.setText(partido.getVisitor_goals());
                tvGoalsLocal.setTextColor(Color.parseColor("#FF0000"));
                tvGoalsVisitor.setTextColor(Color.parseColor("#FF0000"));
            }


            Picasso.get()
                    .load(partido.getLocal_shield())
                    .into(ivShieldLocal);

            Picasso.get()
                    .load(partido.getVisitor_shield())
                    .into(ivShieldVisitor);
        }

        /**
         * Cuando el usaurio pincha en un partido
         * @param v vista
         */
        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onPartidoSeleccionado(partidos.get(getAdapterPosition()));
            }
        }
    }
}
