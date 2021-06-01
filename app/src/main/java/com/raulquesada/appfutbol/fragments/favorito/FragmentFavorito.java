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

/**
 * The type Fragment favorito.
 */
public class FragmentFavorito extends Fragment implements IEquipoClasificacionListener, IGetLigaParaPartidoListener, IGetPartidosParaUnPartidoListener {
    /**
     * The Equipos favoritos.
     */
    private List<Equipo> equiposFavoritos;
    /**
     * The Rv listado equipos favoritos.
     */
    private RecyclerView rvListadoEquiposFavoritos;
    /**
     * The Api manager.
     */
    private APIManager apiManager;
    /**
     * Si el usaurio esta logeado o no
     */
    private boolean signin;
    /**
     * Cantidad de Equipos con partido favorito.
     */
    private int equiposConPartidoFavorito;

    /**
     * Instantiates a new Fragment favorito.
     *
     * @param equiposFavoritos the equipos favoritos
     * @param signin           the signin
     */
    public FragmentFavorito(List<Equipo> equiposFavoritos, boolean signin){
        this.equiposFavoritos = equiposFavoritos;
        this.signin = signin;
        equiposConPartidoFavorito = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (signin){//Si esta logeado
            if (!equiposFavoritos.isEmpty()){//Si hay equipos favoritos
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
        if (signin && !equiposFavoritos.isEmpty()){//Si esta logeado y hay equipos favoritos
            apiManager = new APIManager(getContext());
            apiManager.setGetLigaParaPartidoListener(this);
            apiManager.setGetPartidosParaUnPartidoListener(this);
            initPartidos();

        }
    }

    /**
     * Cuando el usaurio pincha en uno de sus equipos
     * Redirigo a la activity de ese equipo
     * @param eEC equipo seleccioando
     */
    @Override
    public void onEquipoSeleccionado(Equipo eEC) {
        Intent i = new Intent(getContext(), EquipoActivity.class);
        i.putExtra("Equipo",eEC);
        i.putExtra(MainActivity.EXTRA_MAIN,2);//extra para decir que proviene de la pestaña Favoritos
        startActivity(i);//Carga activity equipo
    }

    /**
     * Init partidos.
     */
    private void initPartidos(){
        for (int i = 0; i <equiposFavoritos.size() ; i++) {
            apiManager.getInfoLigaParaPartido(equiposFavoritos.get(i).getDivision(), equiposFavoritos.get(i));
        }
    }

    /**
     * Cuando recibo la información de la liga desde la API
     * @param infoLiga información de la liga
     * @param equipo al que obtener partido favorito
     */
    @Override
    public void OnGetLigaParaPartido(InfoLiga infoLiga, Equipo equipo) {
        apiManager.getJornadaParaPartido(equipo.getDivision(),Integer.parseInt(infoLiga.getLeague().getCurrent_round()),equipo);
    }

    /**
     * Cuando recibo los partidos de un equipo
     * @param jornada del partido
     * @param equipo del partido
     */
    @Override
    public void OnGetPartidosParaUnPartido(Jornada jornada, Equipo equipo) {
        for (Partido partido : jornada.getPartidos()){
            //Si el partido contiene al equipoo
            if (partido.getIdLocal().equals(equipo.getId()) || partido.getIdVisitor().equals(equipo.getId())){
                for (Equipo equipoFavorito : equiposFavoritos){
                    //asigno el partido, la equipo favorito correspondiente
                    if (equipo.getId()==equipoFavorito.getId()){
                        equipoFavorito.setPartidoFavorito(partido);
                        equiposConPartidoFavorito++;
                        //Cuando estén todos los equipos con partido favorito, hago el set al adapter del RecyclerView
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