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

import com.raulquesada.appfutbol.EquipoActivity;
import com.raulquesada.appfutbol.MainActivity;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.ClasificacionAdapter;
import com.raulquesada.appfutbol.adapters.FavoritosAdapter;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposEnClasificacionListener;
import com.raulquesada.appfutbol.listeners.listas.IEquipoClasificacionListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Tabla;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavorito extends Fragment implements IEquipoClasificacionListener {
    private List<Equipo> equiposFavoritos;
    private RecyclerView rvListadoEquiposFavoritos;
    private boolean signin;

    public FragmentFavorito(List<Equipo> equiposFavoritos, boolean signin){
        this.equiposFavoritos = equiposFavoritos;
        this.signin = signin;
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
        getActivity().setTitle("Favoritos");
        if (signin && !equiposFavoritos.isEmpty()){
            rvListadoEquiposFavoritos = getView().findViewById(R.id.rvListadoEquiposFavoritos);
            rvListadoEquiposFavoritos.setAdapter(new FavoritosAdapter(equiposFavoritos,this));
            rvListadoEquiposFavoritos.setHasFixedSize(true);
            rvListadoEquiposFavoritos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }
    }

    @Override
    public void onEquipoSeleccionado(Equipo eEC) {
        Intent i = new Intent(getContext(), EquipoActivity.class);
        i.putExtra("Equipo",eEC);
        i.putExtra(MainActivity.EXTRA_MAIN,2);
        startActivity(i);//Carga activity equipo
    }
}
