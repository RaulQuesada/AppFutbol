package com.raulquesada.appfutbol.fragments.jornada;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raulquesada.appfutbol.listeners.listas.IPartidoJornadaListener;
import com.raulquesada.appfutbol.R;
import com.raulquesada.appfutbol.adapters.JornadaAdapter;
import com.raulquesada.appfutbol.dialog.ResultDialog;
import com.raulquesada.appfutbol.dialog.SelectJornadaDialog;
import com.raulquesada.appfutbol.listeners.api.IGetLigaListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosListener;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Partido;
import com.raulquesada.appfutbol.util.APIManager;

/**
 * The type Fragment jornada.
 */
public class FragmentJornada extends Fragment implements IGetPartidosListener, IGetLigaListener, NumberPicker.OnValueChangeListener, IPartidoJornadaListener {
    /**
     * The Current jornada.
     */
    private int currentJornada;
    /**
     * The Max jornada.
     */
    private int maxJornada;
    /**
     * The Rv listado jornada.
     */
    private RecyclerView rvListadoJornada;
    /**
     * The Tv jornada.
     */
    private TextView tvJornada;
    /**
     * The B seleccionar jornada.
     */
    private Button bSeleccionarJornada;
    /**
     * The Api manager.
     */
    private APIManager apiManager;
    /**
     * The My prefs.
     */
    private SharedPreferences myPrefs;//Mis preferencias

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jornada, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Inicializo variables y componentes

        myPrefs = this.getActivity().getSharedPreferences("Admin", Context.MODE_PRIVATE);
        rvListadoJornada = getView().findViewById(R.id.rvListadoJornada);
        tvJornada = getView().findViewById(R.id.tvJornada);
        bSeleccionarJornada = getView().findViewById(R.id.bSeleccionarJornada);
        apiManager = new APIManager(getContext());
        apiManager.setGetPartidos(this);
        apiManager.setGetLigaListener(this);
        apiManager.getInfoLiga(myPrefs.getInt("division",1));

        //Cuando el usaurio pulsa en el botón de seleccionarJornada
        bSeleccionarJornada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectJornadaDialog f;//Dialogo NumberPicker
                if (myPrefs.getInt("division",1)==1){
                    f = new SelectJornadaDialog(maxJornada,currentJornada);
                }else {
                    f = new SelectJornadaDialog(maxJornada,currentJornada);
                }
                f.setValueChangeListener(FragmentJornada.this);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//Actualizo la jornada
                f.show(fragmentTransaction,"Seleccionar Jornada ");
            }
        });
    }

    /**
     * Cuando recibo los partidos de la API
     * Set adapter el RecyclerView con los partidos
     * @param jornada con los partidos
     */
    @Override
    public void OnGetPartidos(Jornada jornada) {
        rvListadoJornada.setAdapter(new JornadaAdapter(jornada.getPartidos(), this));
        rvListadoJornada.setHasFixedSize(true);
        rvListadoJornada.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Cuando el valor del NumberPicker cambia
     * @param picker NumberPicker
     * @param oldVal valor antiguo
     * @param newVal valor nuevo
     */
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        apiManager.getJornada(myPrefs.getInt("division",1),picker.getValue());
        tvJornada.setText("Jornada "+ picker.getValue());
    }

    /**
     * Cuando recibo la inforamción de la liga
     * @param infoLiga información de la liga
     */
    @Override
    public void OnGetLiga(InfoLiga infoLiga) {
        currentJornada = Integer.parseInt(infoLiga.getLeague().getCurrent_round());
        maxJornada = Integer.parseInt(infoLiga.getLeague().getTotal_rounds());

        apiManager.getJornada(myPrefs.getInt("division",1),currentJornada);
        tvJornada.setText("Jornada "+ currentJornada);
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
