package com.raulquesada.appfutbol.fragments.equipo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.PlantillaAdapter;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposEnLiga;
import com.raulquesada.appfutbol.listeners.api.IGetPlantillaListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Liga;
import com.raulquesada.appfutbol.models.Plantilla;
import com.raulquesada.appfutbol.util.APIManager;

/**
 * The type Fragment detalle equipo plantilla.
 */
public class FragmentDetalleEquipoPlantilla extends Fragment implements IGetPlantillaListener, IGetEquiposEnLiga {
    /**
     * The Rv listado detalle.
     */
    private RecyclerView rvListadoDetalle;
    /**
     * The Api manager.
     */
    private APIManager apiManager;
    /**
     * The Equipo.
     */
    private Equipo equipo;

    /**
     * Instantiates a new Fragment detalle equipo plantilla.
     */
    public FragmentDetalleEquipoPlantilla(){

    }

    /**
     * Instantiates a new Fragment detalle equipo plantilla.
     *
     * @param equipo the equipo
     */
    public FragmentDetalleEquipoPlantilla(Equipo equipo){
        this.equipo = equipo;
        apiManager = new APIManager(getContext());
        apiManager.setGetEquiposEnLiga(this);
        apiManager.setGetPlantillaListener(this);
        apiManager.getEquipos(equipo.getDivision());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_equipo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Inicializo RecyclerView
        rvListadoDetalle = getView().findViewById(R.id.rvListadoDetalle);
    }

    /**
     * Cuando recibo la plantilla del equipo desde la API
     * Set adapter al RecyclerView de la plantilla
     * @param plantilla del equipo
     */
    @Override
    public void OnGetPlantilla(Plantilla plantilla) {
        rvListadoDetalle.setAdapter(new PlantillaAdapter(plantilla.getJugadores()));
        rvListadoDetalle.setHasFixedSize(true);
        rvListadoDetalle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Cuando recibo los equipos de una liga
     * @param liga con los equipos que la componen
     */
    @Override
    public void OnGetEquipos(Liga liga) {
        boolean encontrado = false;
        final int limit = 30;//limite de equipos que puede llegar a haber en una liga
        int i = 0;
        while (!encontrado && i<limit){
            if (Integer.parseInt(equipo.getId())==Integer.parseInt(liga.getEquiposEnLiga().get(i).getId())){
                apiManager.getPlantilla(Integer.parseInt(liga.getEquiposEnLiga().get(i).getId_comp()));
                encontrado = true;
            }else {
                i++;
            }
        }
    }
}
