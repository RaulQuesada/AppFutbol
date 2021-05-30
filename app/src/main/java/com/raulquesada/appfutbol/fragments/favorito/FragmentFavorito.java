package com.raulquesada.appfutbol.fragments.favorito;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.activities.EquipoActivity;
import com.raulquesada.appfutbol.activities.MainActivity;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.FavoritosAdapter;
import com.raulquesada.appfutbol.listeners.api.IGetLigaParaPartidoListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosParaUnPartidoListener;
import com.raulquesada.appfutbol.listeners.listas.IEquipoClasificacionListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.APIManager;

import java.util.List;

public class FragmentFavorito extends Fragment implements IEquipoClasificacionListener, IGetLigaParaPartidoListener, IGetPartidosParaUnPartidoListener {
    private List<Equipo> equiposFavoritos;
    private RecyclerView rvListadoEquiposFavoritos;
    private APIManager apiManager;
    private boolean signin;
    private int equiposConPartidoFavorito;

    public FragmentFavorito(List<Equipo> equiposFavoritos, boolean signin){
        this.equiposFavoritos = equiposFavoritos;
        this.signin = signin;
        equiposConPartidoFavorito = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (signin){
            if (!equiposFavoritos.isEmpty()){
                return inflater.inflate(R.layout.fragment_favorito, container, false);
            }
            return inflater.inflate(R.layout.fragment_favorito_empty, container, false);
        }
        return inflater.inflate(R.layout.fragment_favorito_nouser, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvListadoEquiposFavoritos = getView().findViewById(R.id.rvListadoEquiposFavoritos);

        getActivity().setTitle("Favoritos");
        if (signin && !equiposFavoritos.isEmpty()){
            apiManager = new APIManager(getContext());
            apiManager.setGetLigaParaPartidoListener(this);
            apiManager.setGetPartidosParaUnPartidoListener(this);
            initPartidos();

        }
    }

    @Override
    public void onEquipoSeleccionado(Equipo eEC) {
        Intent i = new Intent(getContext(), EquipoActivity.class);
        i.putExtra("Equipo",eEC);
        i.putExtra(MainActivity.EXTRA_MAIN,2);
        startActivity(i);//Carga activity equipo
    }

    private void initPartidos(){
        for (int i = 0; i <equiposFavoritos.size() ; i++) {
            apiManager.getInfoLigaParaPartido(equiposFavoritos.get(i).getDivision(), equiposFavoritos.get(i));
        }
    }

    @Override
    public void OnGetLigaParaPartido(InfoLiga infoLiga, Equipo equipo) {
        apiManager.getJornadaParaPartido(equipo.getDivision(),Integer.parseInt(infoLiga.getLeague().getCurrent_round()),equipo);
    }

    @Override
    public void OnGetPartidosParaUnPartido(Jornada jornada, Equipo equipo) {
        for (Partido partido : jornada.getPartidos()){
            if (partido.getIdLocal().equals(equipo.getId()) || partido.getIdVisitor().equals(equipo.getId())){
                for (Equipo equipoFavorito : equiposFavoritos){
                    if (equipo.getId()==equipoFavorito.getId()){
                        equipoFavorito.setPartidoFavorito(partido);
                        equiposConPartidoFavorito++;
                        if (equiposConPartidoFavorito==equiposFavoritos.size()){
                            rvListadoEquiposFavoritos.setAdapter(new FavoritosAdapter(equiposFavoritos,this));
                            rvListadoEquiposFavoritos.setHasFixedSize(true);
                            rvListadoEquiposFavoritos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        }
                    }
                }
            }
        }
    }
}