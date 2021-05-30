package com.raulquesada.appfutbol.fragments.equipo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.raulquesada.appfutbol.listeners.button.IButtonPartidosListener;
import com.raulquesada.appfutbol.listeners.button.IButtonPlantillaListener;
import com.raulquesada.appfutbol.listeners.button.IButtonResultadosListener;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.util.Lib;
import com.squareup.picasso.Picasso;

public class FragmentEquipo extends Fragment implements View.OnClickListener {

    private Equipo equipo;
    private ImageView ivFlagEquipo;
    private ImageView ivShieldEquipo;
    private TextView tvNomEquipo;
    private TextView tvEstadio;
    private TextView tvCompeticionEquipo;
    private TextView tvPosicionEquipo;
    private TextView [] tvForma;
    private TextView bPlantillaEquipo;
    private TextView bResultadosEquipo;
    private TextView bPartidosEquipo;
    private IButtonPlantillaListener buttonPlantillaListener;
    private IButtonPartidosListener buttonPartidosListener;
    private IButtonResultadosListener buttonResultadosListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

    public void setEquipo(Equipo equipo){
        this.equipo = equipo;
    }
    public void init(){
        ivFlagEquipo = getView().findViewById(R.id.ivFlagEquipo);
        ivShieldEquipo = getView().findViewById(R.id.ivShieldEquipo);
        tvCompeticionEquipo = getView().findViewById(R.id.tvCompeticionEquipo);
        tvNomEquipo = getView().findViewById(R.id.tvNomEquipo);
        tvPosicionEquipo = getView().findViewById(R.id.tvPosicionEquipo);
        tvEstadio = getView().findViewById(R.id.tvEstadio);
        tvForma = new TextView[5];
        tvForma[0] = getView().findViewById(R.id.tvForma1);
        tvForma[1] = getView().findViewById(R.id.tvForma2);
        tvForma[2] = getView().findViewById(R.id.tvForma3);
        tvForma[3] = getView().findViewById(R.id.tvForma4);
        tvForma[4] = getView().findViewById(R.id.tvForma5);
        bPlantillaEquipo = getView().findViewById(R.id.bPlantillaEquipo);
        bPlantillaEquipo.setOnClickListener(this);
        bResultadosEquipo = getView().findViewById(R.id.bResultadosEquipo);
        bResultadosEquipo.setOnClickListener(this);
        bPartidosEquipo = getView().findViewById(R.id.bPartidosEquipo);
        bPartidosEquipo.setOnClickListener(this);

        tvEstadio.setText(Lib.getStadium(Integer.parseInt(equipo.getId())));
        setActiveButton(bPlantillaEquipo, new TextView[]{bPartidosEquipo,bResultadosEquipo});

        Picasso.get()
              .load(equipo.getShield())
              .into(ivShieldEquipo);


        Picasso.get()
                .load(equipo.getCflag())
                .into(ivFlagEquipo);
        tvPosicionEquipo.setText("#"+ equipo.getPos());
        for (int i = 0; i <tvForma.length ; i++) {
            String forma = String.valueOf(Character.toUpperCase(equipo.getForm().charAt(i)));
            tvForma[i].setText(forma);
            switch (forma){
                case "W":
                    tvForma[i].setBackgroundResource(R.drawable.victory);
                    break;
                case "D":
                    tvForma[i].setBackgroundResource(R.drawable.draw);
                    break;
                case "L":
                    tvForma[i].setBackgroundResource(R.drawable.lose);
                    break;
            }
        }
        tvNomEquipo.setText(equipo.getTeam());
        switch (equipo.getDivision()){
            case 1:
                tvCompeticionEquipo.setText("LaLiga Santander");
                break;

            case 2:
                tvCompeticionEquipo.setText("LaLiga SmartBank");
                break;
        }
    }

    public void setButtonPlantillaListener(IButtonPlantillaListener buttonPlantillaListener) {
        this.buttonPlantillaListener = buttonPlantillaListener;
    }

    public void setButtonPartidosListener(IButtonPartidosListener buttonPartidosListener) {
        this.buttonPartidosListener = buttonPartidosListener;
    }

    public void setButtonResultadosListener(IButtonResultadosListener buttonResultadosListener) {
        this.buttonResultadosListener = buttonResultadosListener;
    }

    private void setActiveButton(TextView activeButton, TextView[] resetButton){
        activeButton.setTextColor(getResources().getColor(R.color.primary));
        activeButton.setBackgroundResource(R.drawable.buttonselected);

        for (int i = 0; i <resetButton.length ; i++) {
            resetButton[i].setBackgroundResource(R.drawable.nobuttonselected);
            resetButton[i].setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bPartidosEquipo:
                buttonPartidosListener.OnPartidosButtonPressed();
                setActiveButton(bPartidosEquipo, new TextView[]{bResultadosEquipo,bPlantillaEquipo});
                break;

            case R.id.bResultadosEquipo:
                buttonResultadosListener.OnResultadosButtonPressed();
                setActiveButton(bResultadosEquipo, new TextView[]{bPartidosEquipo,bPlantillaEquipo});
                break;

            case R.id.bPlantillaEquipo:
                buttonPlantillaListener.OnPlantillaButtonPressed();
                setActiveButton(bPlantillaEquipo, new TextView[]{bPartidosEquipo,bResultadosEquipo});
                break;

            default:
                break;
        }
    }
}
