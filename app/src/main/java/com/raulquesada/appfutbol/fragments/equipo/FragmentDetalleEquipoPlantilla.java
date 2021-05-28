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

public class FragmentDetalleEquipoPlantilla extends Fragment implements IGetPlantillaListener, IGetEquiposEnLiga {
    private RecyclerView rvListadoDetalle;
    private APIManager apiManager;
    private Equipo equipo;

    public FragmentDetalleEquipoPlantilla(){

    }

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
        rvListadoDetalle = getView().findViewById(R.id.rvListadoDetalle);
    }

    @Override
    public void OnGetPlantilla(Plantilla plantilla) {
        rvListadoDetalle.setAdapter(new PlantillaAdapter(plantilla.getJugadores()));
        rvListadoDetalle.setHasFixedSize(true);
        rvListadoDetalle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void OnGetEquipos(Liga liga) {
        boolean encontrado = false;
        final int limit = 30;
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
