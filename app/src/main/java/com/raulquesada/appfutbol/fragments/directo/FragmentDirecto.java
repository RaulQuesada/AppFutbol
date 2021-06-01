package com.raulquesada.appfutbol.fragments.directo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.listeners.listas.IPartidoJornadaListener;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.JornadaAdapter;
import com.raulquesada.appfutbol.dialog.ResultDialog;
import com.raulquesada.appfutbol.listeners.api.IGetLigaListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosListener;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.APIManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentDirecto extends Fragment implements IGetPartidosListener, IGetLigaListener, IPartidoJornadaListener {
    private int currentJornada; //jornada actual
    /**
     * The Iv sad tv.
     */
    private ImageView ivSadTv;
    /**
     * The Rv listado jornada.
     */
    private RecyclerView rvListadoJornada;
    /**
     * The Tv jornada.
     */
    private TextView tvJornada;
    /**
     * The Tv no live partidos.
     */
    private TextView tvNoLivePartidos;
    private Button bSeleccionarJornada;
    private APIManager apiManager;//API manager
    private SharedPreferences myPrefs;//Mis preferencias
    private ConstraintLayout layoutDirecto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jornada, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        //Inicializo variables y componentes

        super.onActivityCreated(savedInstanceState);
        ivSadTv = getView().findViewById(R.id.ivSadTv);
        ivSadTv.setVisibility(View.GONE);
        layoutDirecto = getView().findViewById(R.id.layoutDirecto);
        myPrefs = this.getActivity().getSharedPreferences("Admin", Context.MODE_PRIVATE);
        rvListadoJornada = getView().findViewById(R.id.rvListadoJornada);
        tvJornada = getView().findViewById(R.id.tvJornada);
        tvNoLivePartidos = getView().findViewById(R.id.tvNoLivePartidos);
        bSeleccionarJornada = getView().findViewById(R.id.bSeleccionarJornada);
        apiManager = new APIManager(getContext());
        apiManager.setGetPartidos(this);
        apiManager.setGetLigaListener(this);
        apiManager.getInfoLiga(myPrefs.getInt("division",1));
        tvJornada.setText("LIVE");
        tvJornada.setVisibility(View.GONE);
        tvJornada.setTextColor(Color.parseColor("#FF0000"));
        bSeleccionarJornada.setVisibility(View.GONE);
    }

    /**
     * Cuando recibo los partidos de la API
     * Set adapter el RecyclerView con los partidos
     * @param jornada con los partidos
     */
    @Override
    public void OnGetPartidos(Jornada jornada) {
        rvListadoJornada.setAdapter(new JornadaAdapter(getLivePartidos(jornada), this));
        rvListadoJornada.setHasFixedSize(true);
        rvListadoJornada.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Método el cual recibe una lista de partidos y aplica un filtro para quedarme con los que estan en directo
     * @param jornada con los partidos
     * @return partidos de la jornada en directo
     */
    private List<Partido> getLivePartidos(Jornada jornada){
        List<Partido> partidos = new ArrayList<>();

        for (int i = 0; i <jornada.getPartidos().size(); i++) {
            if (!jornada.getPartidos().get(i).getLive_minute().equals("")){
                partidos.add(jornada.getPartidos().get(i));
            }
        }
        if (partidos.size()==0){//Si no hay partidos en directo se muestra el siguiente mensaje
            ivSadTv.setVisibility(View.VISIBLE);
            tvNoLivePartidos.setText("!No hay partidos en Directo ahora mismo!");
            layoutDirecto.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutDirecto.requestLayout();
        }else {
            tvJornada.setVisibility(View.VISIBLE);
        }
        return partidos;
    }

    /**
     * Cuando recibo la información de la liga
     * @param infoLiga información de la liga
     */
    @Override
    public void OnGetLiga(InfoLiga infoLiga) {
        currentJornada = Integer.parseInt(infoLiga.getLeague().getCurrent_round());
        apiManager.getJornada(myPrefs.getInt("division",1), currentJornada);
    }

    /**
     * Cuando el usuario pincha en un partido
     * Salta un diálogo con datos del partido
     * @param partido seleccionado
     */
    @Override
    public void onPartidoSeleccionado(Partido partido) {
        ResultDialog dialog = new ResultDialog(partido);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        dialog.show(fragmentTransaction,partido.getLocal()+"-"+partido.getVisitor());
    }
}
