package com.raulquesada.appfutbol.dialog;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.raulquesada.appfutbol.util.Lib;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.models.Partido;
import com.squareup.picasso.Picasso;

/**
 * The type Result dialog.
 */
public class ResultDialog extends DialogFragment {
    /**
     * The Iv dialog competicion result.
     */
    private ImageView ivDialogCompeticionResult;
    /**
     * The Iv dialog result local.
     */
    private ImageView ivDialogResultLocal;
    /**
     * The Iv dialog result visitor.
     */
    private ImageView ivDialogResultVisitor;
    /**
     * The Iv dialog channel.
     */
    private ImageView ivDialogChannel;
    /**
     * The Tv dialog cometicion name.
     */
    private TextView tvDialogCometicionName;
    /**
     * The Tv dialog jornada.
     */
    private TextView tvDialogJornada;
    /**
     * The Tv dialog fecha.
     */
    private TextView tvDialogFecha;
    /**
     * The Tv dialog result.
     */
    private TextView tvDialogResult;
    /**
     * The Tv dialog channel.
     */
    private TextView tvDialogChannel;
    /**
     * The Partido.
     */
    private Partido partido;

    /**
     * Instantiates a new Result dialog.
     *
     * @param partido the partido
     */
    public ResultDialog(Partido partido) {
        this.partido = partido;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_partido, null);

        //Elementos de la vista
        ivDialogCompeticionResult = view.findViewById(R.id.ivCompeticionClasificacion);
        ivDialogResultLocal = view.findViewById(R.id.ivDialogResultLocal);
        ivDialogResultVisitor = view.findViewById(R.id.ivDialogResultVisitor);
        ivDialogChannel = view.findViewById(R.id.ivDialogChannel);
        tvDialogCometicionName = view.findViewById(R.id.tvCompeticionNameClasificacion);
        tvDialogJornada = view.findViewById(R.id.tvDialogJornada);
        tvDialogFecha = view.findViewById(R.id.tvDialogFecha);
        tvDialogResult = view.findViewById(R.id.tvDialogResult);
        tvDialogChannel = view.findViewById(R.id.tvDialogChannel);

        setFieldsFromPartido();

        builder.setView(view).setTitle(partido.getLocal()+"-"+partido.getVisitor());

        return builder.create();
    }

    /**
     * Metodo que rellena los elementos de la vista dependiendo de la frase
     */
    private void setFieldsFromPartido(){
        switch (Integer.parseInt(partido.getCompetition_id())){
            case 1:
                ivDialogCompeticionResult.setImageDrawable(getResources().getDrawable(R.drawable.laliga));
                break;
            case 2:
                ivDialogCompeticionResult.setImageDrawable(getResources().getDrawable(R.drawable.laliga2));
                break;
        }
        tvDialogCometicionName.setText(partido.getCompetition_name());
        tvDialogJornada.setText("Jornada "+partido.getJornada()+ " - Estadio: "+Lib.getStadium(Integer.parseInt(partido.getIdLocal())));
        //Si el partido esta en directo
        if (partido.getLive_minute().equals("")){
            tvDialogFecha.setText(Lib.changeFormatDate(partido.getDate())+" - "+partido.getHour()+":"+partido.getMinute());
        //Si no esta en directo
        }else {
            tvDialogFecha.setText(partido.getLive_minute()+"'");
            tvDialogFecha.setTextColor(Color.parseColor("#FF0000"));
            tvDialogResult.setTextColor(Color.parseColor("#FF0000"));
        }
        tvDialogResult.setText(partido.getResult());

        Picasso.get()
                .load(partido.getLocal_shield())
                .into(ivDialogResultLocal);

        Picasso.get()
                .load(partido.getVisitor_shield())
                .into(ivDialogResultVisitor);

        //Si no hay canales de retransmisión
        if (partido.getCanales().size()==0){
            ivDialogChannel.setVisibility(View.GONE);
            tvDialogChannel.setVisibility(View.GONE);
        //Si si los hay
        }else {
            tvDialogChannel.setText(partido.getCanales().get(0).getName());

            Picasso.get()
                    .load(partido.getCanales().get(0).getUrlImage())
                    .into(ivDialogChannel);
        }
    }
}
