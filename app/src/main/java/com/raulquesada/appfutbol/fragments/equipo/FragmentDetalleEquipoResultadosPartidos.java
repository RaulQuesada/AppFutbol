package com.raulquesada.appfutbol.fragments.equipo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.JornadaAdapter;
import com.raulquesada.appfutbol.dialog.ResultDialog;
import com.raulquesada.appfutbol.listeners.api.IGetLigaListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosListener;
import com.raulquesada.appfutbol.listeners.listas.IPartidoJornadaListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.APIManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentDetalleEquipoResultadosPartidos extends Fragment implements IGetLigaListener, IGetPartidosListener, IPartidoJornadaListener {
    private static final int RESULTADOS_LIMIT = 5;
    private RecyclerView rvListadoDetalle;
    private APIManager apiManager;
    private boolean playedMatches;
    private Equipo equipo;
    private List<Partido> partidos;

    public FragmentDetalleEquipoResultadosPartidos(){

    }

    public FragmentDetalleEquipoResultadosPartidos(Equipo equipo, boolean playedMatches){
        this.equipo = equipo;
        this.playedMatches=playedMatches;
        partidos = new ArrayList<>();
        apiManager = new APIManager(getContext());
        apiManager.setGetLigaListener(this);
        apiManager.setGetPartidos(this);
        apiManager.getInfoLiga(equipo.getDivision());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvListadoDetalle = getView().findViewById(R.id.rvListadoDetalle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_equipo, container, false);
    }

    @Override
    public void OnGetLiga(InfoLiga infoLiga) {
        for (int i = 1; i <=RESULTADOS_LIMIT ; i++) {
            if (playedMatches){
                apiManager.getJornada(equipo.getDivision(),Integer.parseInt(infoLiga.getLeague().getCurrent_round())-i);
            }else {
                apiManager.getJornada(equipo.getDivision(),Integer.parseInt(infoLiga.getLeague().getCurrent_round())+i-1);
            }
        }
    }

    @Override
    public void OnGetPartidos(Jornada jornada) {
        for (Partido partido : jornada.getPartidos()){
            if (partido.getIdLocal().equals(equipo.getId()) || partido.getIdVisitor().equals(equipo.getId())){
                partidos.add(partido);
            }
        }
        initRecyclerView();
    }

    @Override
    public void onPartidoSeleccionado(Partido partido) {
        ResultDialog dialog = new ResultDialog(partido);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        dialog.show(fragmentTransaction,partido.getLocal()+"-"+partido.getVisitor());
    }

    private void initRecyclerView(){
        rvListadoDetalle.setAdapter(new JornadaAdapter(partidos, this));
        rvListadoDetalle.setHasFixedSize(true);
        rvListadoDetalle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
