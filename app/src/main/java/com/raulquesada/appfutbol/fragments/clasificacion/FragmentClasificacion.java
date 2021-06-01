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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.activities.EquipoActivity;
import com.raulquesada.appfutbol.activities.MainActivity;
import com.raulquesada.appfutbol.listeners.listas.IEquipoClasificacionListener;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.ClasificacionAdapter;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposEnClasificacionListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Tabla;
import com.raulquesada.appfutbol.util.APIManager;

import java.util.Arrays;
import java.util.List;

public class FragmentClasificacion extends Fragment implements IGetEquiposEnClasificacionListener, IEquipoClasificacionListener {
    private final static String[] TEMPORADAS = {"2020-2021","2019-2020","2018-2019","2017-2018"};//Temporadas disponibles
    private final static int CURRENT_SEASON = 2021;//Temporada actual

    private int selectedSeason;//Temporada seleccionada
    /**
     * The Iv competicion clasificacion.
     */
    private ImageView ivCompeticionClasificacion;
    /**
     * The Tv competicion name clasificacion.
     */
    private TextView tvCompeticionNameClasificacion;
    /**
     * The Spin temporada.
     */
    private Spinner spinTemporada;
    /**
     * The Rv listado.
     */
    private RecyclerView rvListado;
    /**
     * The C leyenda primera division.
     */
    private ConstraintLayout cLeyendaPrimeraDivision;
    /**
     * The C leyenda segunda division.
     */
    private ConstraintLayout cLeyendaSegundaDivision;
    /**
     * The Api manager.
     */
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

        //Inicializo variables y componentes

        selectedSeason = CURRENT_SEASON;
        myPrefs = this.getActivity().getSharedPreferences("Admin", Context.MODE_PRIVATE);
        rvListado = getView().findViewById(R.id.rvListado);
        cLeyendaPrimeraDivision = getView().findViewById(R.id.include_leyenda_primera);
        cLeyendaSegundaDivision = getView().findViewById(R.id.include_leyenda_segunda);
        spinTemporada = getView().findViewById(R.id.spinTemporada);
        setAdapterSpinnerTemporada(Arrays.asList(TEMPORADAS));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvListado.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.layer,null));
        rvListado.addItemDecoration(dividerItemDecoration);
        ivCompeticionClasificacion = getView().findViewById(R.id.ivCompeticionClasificacion);
        tvCompeticionNameClasificacion = getView().findViewById(R.id.tvCompeticionNameClasificacion);

        //Datos de la liga seleccionada
        switch (myPrefs.getInt("division",1)){
            case 1:
                ivCompeticionClasificacion.setImageDrawable(getResources().getDrawable(R.drawable.laliga));
                tvCompeticionNameClasificacion.setText("LaLiga Santander");
                cLeyendaPrimeraDivision.setVisibility(View.VISIBLE);
                break;
            case 2:
                ivCompeticionClasificacion.setImageDrawable(getResources().getDrawable(R.drawable.laliga2));
                tvCompeticionNameClasificacion.setText("LaLiga SmartBank");
                cLeyendaSegundaDivision.setVisibility(View.VISIBLE);
                break;
        }

        //API Manager
        apiManager = new APIManager(getContext());
        apiManager.setGetEquiposEnClasificacionListener(this);
        apiManager.getEquiposEnClasificacion(myPrefs.getInt("division",1),CURRENT_SEASON);

        //Clasificación según el item que haya seleccionado el usuario
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

    /**
     * Listener cuando recibo los equipos de la API
     * Set adapter al RecyclerView con la clasificación
     * @param equipoEnClasificacions equipos de la clasificación
     */
    @Override
    public void OnGetEquiposEnClasificacion(Tabla equipoEnClasificacions) {
        rvListado.setAdapter(new ClasificacionAdapter(equipoEnClasificacions,this));
        rvListado.setHasFixedSize(true);
        rvListado.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Cuando el usuario pincha en un equipo
     * Lo redirigo a la activity del equipo
     * @param eEC equipo seleccionado
     */
    @Override
    public void onEquipoSeleccionado(Equipo eEC) {
        if (selectedSeason != CURRENT_SEASON){//Si no es la temporada actual, no hay información
            Toast.makeText(getContext(),"Para ver información de equipos debes seleccionar la temporada actual",Toast.LENGTH_LONG).show();
        }else {
            eEC.setDivision(myPrefs.getInt("division",1));
            Intent i = new Intent(getContext(), EquipoActivity.class);
            i.putExtra("Equipo",eEC);
            i.putExtra(MainActivity.EXTRA_MAIN,1);//extra para decir que viene del fragment clasificación
            startActivity(i);//Carga activity equipo
        }
    }

    /**
     * set adapter del Spinner
     * @param temporadas disponibles
     */
    private void setAdapterSpinnerTemporada(List<String> temporadas){
        ArrayAdapter<String> adaptadorTemporadas = new ArrayAdapter<>(getActivity(), R.layout.item_spinner, temporadas);
        spinTemporada.setAdapter(adaptadorTemporadas);
        spinTemporada.setSelection(0);
    }
}
