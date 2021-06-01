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

/**
 * The type Fragment equipo.
 */
public class FragmentEquipo extends Fragment implements View.OnClickListener {

    /**
     * The Equipo.
     */
    private Equipo equipo;
    /**
     * The Iv flag equipo.
     */
    private ImageView ivFlagEquipo;
    /**
     * The Iv shield equipo.
     */
    private ImageView ivShieldEquipo;
    /**
     * The Tv nom equipo.
     */
    private TextView tvNomEquipo;
    /**
     * The Tv estadio.
     */
    private TextView tvEstadio;
    /**
     * The Tv competicion equipo.
     */
    private TextView tvCompeticionEquipo;
    /**
     * The Tv posicion equipo.
     */
    private TextView tvPosicionEquipo;
    /**
     * The Tv forma.
     */
    private TextView [] tvForma;
    /**
     * The B plantilla equipo.
     */
    private TextView bPlantillaEquipo;
    /**
     * The B resultados equipo.
     */
    private TextView bResultadosEquipo;
    /**
     * The B partidos equipo.
     */
    private TextView bPartidosEquipo;
    /**
     * The Button plantilla listener.
     */
    private IButtonPlantillaListener buttonPlantillaListener;
    /**
     * The Button partidos listener.
     */
    private IButtonPartidosListener buttonPartidosListener;
    /**
     * The Button resultados listener.
     */
    private IButtonResultadosListener buttonResultadosListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

    /**
     * Set equipo.
     *
     * @param equipo the equipo
     */
    public void setEquipo(Equipo equipo){
        this.equipo = equipo;
    }

    /**
     * Init variables y componentes según el equipo
     */
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

    /**
     * Sets button plantilla listener.
     *
     * @param buttonPlantillaListener the button plantilla listener
     */
    public void setButtonPlantillaListener(IButtonPlantillaListener buttonPlantillaListener) {
        this.buttonPlantillaListener = buttonPlantillaListener;
    }

    /**
     * Sets button partidos listener.
     *
     * @param buttonPartidosListener the button partidos listener
     */
    public void setButtonPartidosListener(IButtonPartidosListener buttonPartidosListener) {
        this.buttonPartidosListener = buttonPartidosListener;
    }

    /**
     * Sets button resultados listener.
     *
     * @param buttonResultadosListener the button resultados listener
     */
    public void setButtonResultadosListener(IButtonResultadosListener buttonResultadosListener) {
        this.buttonResultadosListener = buttonResultadosListener;
    }

    /**
     * Set active button.
     * Para cuando el usuario pincha una opción del menú, deshabilitar esa opción y habilitar las demás
     *
     * @param activeButton the active button
     * @param resetButton  the reset button
     */
    private void setActiveButton(TextView activeButton, TextView[] resetButton){
        activeButton.setTextColor(getResources().getColor(R.color.primary));
        activeButton.setBackgroundResource(R.drawable.buttonselected);

        for (int i = 0; i <resetButton.length ; i++) {
            resetButton[i].setBackgroundResource(R.drawable.nobuttonselected);
            resetButton[i].setTextColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * Cuando el usuari opulsa en una opción del menú
     * @param v vista
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bPartidosEquipo://Partidos
                buttonPartidosListener.OnPartidosButtonPressed();
                setActiveButton(bPartidosEquipo, new TextView[]{bResultadosEquipo,bPlantillaEquipo});
                break;

            case R.id.bResultadosEquipo://Resultados
                buttonResultadosListener.OnResultadosButtonPressed();
                setActiveButton(bResultadosEquipo, new TextView[]{bPartidosEquipo,bPlantillaEquipo});
                break;

            case R.id.bPlantillaEquipo://Plantila
                buttonPlantillaListener.OnPlantillaButtonPressed();
                setActiveButton(bPlantillaEquipo, new TextView[]{bPartidosEquipo,bResultadosEquipo});
                break;

            default:
                break;
        }
    }
}
