package com.raulquesada.appfutbol.fragments.clasificacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.EquipoActivity;
import com.raulquesada.appfutbol.listeners.listas.IEquipoClasificacionListener;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.ClasificacionAdapter;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposEnClasificacionListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Tabla;
import com.raulquesada.appfutbol.util.APIManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentClasificacion extends Fragment implements IGetEquiposEnClasificacionListener, IEquipoClasificacionListener {
    private final static String[] TEMPORADAS = {"2020-2021","2019-2020","2018-2019","2017-2018"};
    private final static int CURRENT_SEASON = 2021;

    private int selectedSeason;
    private ImageView ivCompeticionClasificacion;
    private TextView tvCompeticionNameClasificacion;
    private Spinner spinTemporada;
    private RecyclerView rvListado;
    private APIManager apiManager;
    private SharedPreferences myPrefs;//Mis preferencias

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectedSeason = CURRENT_SEASON;
        myPrefs = this.getActivity().getSharedPreferences("Admin", Context.MODE_PRIVATE);
        rvListado = getView().findViewById(R.id.rvListado);
        spinTemporada = getView().findViewById(R.id.spinTemporada);
        setAdapterSpinnerTemporada(Arrays.asList(TEMPORADAS));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvListado.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.layer,null));
        rvListado.addItemDecoration(dividerItemDecoration);
        ivCompeticionClasificacion = getView().findViewById(R.id.ivCompeticionClasificacion);
        tvCompeticionNameClasificacion = getView().findViewById(R.id.tvCompeticionNameClasificacion);

        switch (myPrefs.getInt("division",1)){
            case 1:
                ivCompeticionClasificacion.setImageDrawable(getResources().getDrawable(R.drawable.laliga));
                tvCompeticionNameClasificacion.setText("LaLiga Santander");
                break;
            case 2:
                ivCompeticionClasificacion.setImageDrawable(getResources().getDrawable(R.drawable.laliga2));
                tvCompeticionNameClasificacion.setText("LaLiga SmartBank");
                break;
        }

        apiManager = new APIManager(getContext());
        apiManager.setGetEquiposEnClasificacionListener(this);
        apiManager.getEquiposEnClasificacion(myPrefs.getInt("division",1),CURRENT_SEASON);

        spinTemporada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (TEMPORADAS[position]){
                    case "2017-2018":
                        selectedSeason = 2018;
                        break;
                    case "2018-2019":
                        selectedSeason = 2019;
                        break;
                    case "2019-2020":
                        selectedSeason = 2020;
                        break;
                    case "2020-2021":
                        selectedSeason = 2021;
                        break;
                }
                apiManager.getEquiposEnClasificacion(myPrefs.getInt("division",1),selectedSeason);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(),"Selecciona una opción",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void OnGetEquiposEnClasificacion(Tabla equipoEnClasificacions) {
        rvListado.setAdapter(new ClasificacionAdapter(equipoEnClasificacions,this));
        rvListado.setHasFixedSize(true);
        rvListado.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onEquipoSeleccionado(Equipo eEC) {
        if (selectedSeason != CURRENT_SEASON){
            Toast.makeText(getContext(),"Para ver información de equipos debes seleccionar la temporada actual",Toast.LENGTH_LONG).show();
        }else {
            eEC.setDivision(myPrefs.getInt("division",1));
            Intent i = new Intent(getContext(), EquipoActivity.class);
            i.putExtra("Equipo",eEC);
            startActivity(i);//Carga activity equipo
        }
    }
    private void setAdapterSpinnerTemporada(List<String> temporadas){
        ArrayAdapter<String> adaptadorTemporadas = new ArrayAdapter<>(getActivity(), R.layout.item_spinner, temporadas);
        spinTemporada.setAdapter(adaptadorTemporadas);
        spinTemporada.setSelection(0);
    }
}
