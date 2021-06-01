package com.raulquesada.appfutbol.util;

import android.content.Context;
import android.util.Log;

import com.raulquesada.appfutbol.api.IAPIService;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposEnClasificacionListener;
import com.raulquesada.appfutbol.listeners.api.IGetEquiposEnLiga;
import com.raulquesada.appfutbol.listeners.api.IGetLigaListener;
import com.raulquesada.appfutbol.listeners.api.IGetLigaParaPartidoListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosListener;
import com.raulquesada.appfutbol.listeners.api.IGetPartidosParaUnPartidoListener;
import com.raulquesada.appfutbol.listeners.api.IGetPlantillaListener;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Liga;
import com.raulquesada.appfutbol.models.Plantilla;
import com.raulquesada.appfutbol.models.Tabla;
import com.raulquesada.appfutbol.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIManager {
    /**
     * The Iapi service.
     */
    private IAPIService iapiService;

    /**
     * The Get liga listener.
     */
    private IGetLigaListener getLigaListener;
    /**
     * The Get liga para partido listener.
     */
    private IGetLigaParaPartidoListener getLigaParaPartidoListener;
    /**
     * The Get equipos en clasificacion listener.
     */
    private IGetEquiposEnClasificacionListener getEquiposEnClasificacionListener;
    /**
     * The Get partidos listener.
     */
    private IGetPartidosListener getPartidosListener;
    /**
     * The Get partidos para un partido listener.
     */
    private IGetPartidosParaUnPartidoListener getPartidosParaUnPartidoListener;
    /**
     * The Get plantilla listener.
     */
    private IGetPlantillaListener getPlantillaListener;
    /**
     * The Get equipos en liga.
     */
    private IGetEquiposEnLiga getEquiposEnLiga;

    /**
     * The Context.
     */
    private Context context;

    /**
     * Instantiates a new Api manager.
     *
     * @param context the context
     */
    public APIManager(Context context){
        this.context = context;
        iapiService = RestClient.getInstance();
    }

    /**
     * Método que llama a la API para recoger los datos de la clasificación
     * @param division de la clasificación
     */
    public void getEquiposEnClasificacion(int division, int season){
        iapiService.getTabla(division, season).enqueue(new Callback<Tabla>() {
            @Override
            public void onResponse(Call<Tabla> call, Response<Tabla> response) {
                if (response.isSuccessful()){
                    getEquiposEnClasificacionListener.OnGetEquiposEnClasificacion(response.body());
                }
            }

            @Override
            public void onFailure(Call<Tabla> call, Throwable t){
            }
        });
    }

    public void getJornada(int division, int jornada){
        iapiService.getJornada(division, jornada).enqueue(new Callback<Jornada>() {
            @Override
            public void onResponse(Call<Jornada> call, Response<Jornada> response) {
                if (response.isSuccessful()){
                    getPartidosListener.OnGetPartidos(response.body());
                }
            }

            @Override
            public void onFailure(Call<Jornada> call, Throwable t){
            }
        });
    }

    public void getJornadaParaPartido(int division, int jornada, Equipo equipo){
        iapiService.getJornada(division, jornada).enqueue(new Callback<Jornada>() {
            @Override
            public void onResponse(Call<Jornada> call, Response<Jornada> response) {
                if (response.isSuccessful()){
                    getPartidosParaUnPartidoListener.OnGetPartidosParaUnPartido(response.body(), equipo);
                }
            }

            @Override
            public void onFailure(Call<Jornada> call, Throwable t){
            }
        });
    }

    public void getInfoLiga(int nLiga){
        iapiService.getLiga(nLiga).enqueue(new Callback<InfoLiga>() {
            @Override
            public void onResponse(Call<InfoLiga> call, Response<InfoLiga> response) {
                if (response.isSuccessful()){
                    getLigaListener.OnGetLiga(response.body());
                }
            }

            @Override
            public void onFailure(Call<InfoLiga> call, Throwable t) {
            }
        });
    }

    public void getInfoLigaParaPartido(int nLiga, Equipo equipo){
        iapiService.getLiga(nLiga).enqueue(new Callback<InfoLiga>() {
            @Override
            public void onResponse(Call<InfoLiga> call, Response<InfoLiga> response) {
                if (response.isSuccessful()){
                    getLigaParaPartidoListener.OnGetLigaParaPartido(response.body(),equipo);
                }
            }

            @Override
            public void onFailure(Call<InfoLiga> call, Throwable t) {
            }
        });
    }

    public void getPlantilla(int team){
        iapiService.getPlantilla(team).enqueue(new Callback<Plantilla>() {
            @Override
            public void onResponse(Call<Plantilla> call, Response<Plantilla> response) {
                if (response.isSuccessful()){
                    getPlantillaListener.OnGetPlantilla(response.body());
                }
            }

            @Override
            public void onFailure(Call<Plantilla> call, Throwable t) {
                Log.i("hola", "3");
            }
        });
    }

    public void getEquipos(int division){
        iapiService.getEquipos(division).enqueue(new Callback<Liga>() {
            @Override
            public void onResponse(Call<Liga> call, Response<Liga> response) {
                if (response.isSuccessful()){
                    getEquiposEnLiga.OnGetEquipos(response.body());
                }
            }

            @Override
            public void onFailure(Call<Liga> call, Throwable t) {
                Log.i("hola", "3");
            }
        });
    }

    /**
     * Sets get plantilla listener.
     *
     * @param getPlantillaListener the get plantilla listener
     */
    public void setGetPlantillaListener(IGetPlantillaListener getPlantillaListener) {
        this.getPlantillaListener = getPlantillaListener;
    }

    /**
     * Sets get equipos en clasificacion listener.
     *
     * @param getEquiposEnClasificacionListener the get equipos en clasificacion listener
     */
    public void setGetEquiposEnClasificacionListener(IGetEquiposEnClasificacionListener getEquiposEnClasificacionListener) {
        this.getEquiposEnClasificacionListener = getEquiposEnClasificacionListener;
    }

    /**
     * Sets get partidos.
     *
     * @param getPartidos the get partidos
     */
    public void setGetPartidos(IGetPartidosListener getPartidos) {
        this.getPartidosListener = getPartidos;
    }

    /**
     * Sets get liga listener.
     *
     * @param getLigaListener the get liga listener
     */
    public void setGetLigaListener(IGetLigaListener getLigaListener) {
        this.getLigaListener = getLigaListener;
    }

    /**
     * Sets get equipos en liga.
     *
     * @param getEquiposEnLiga the get equipos en liga
     */
    public void setGetEquiposEnLiga(IGetEquiposEnLiga getEquiposEnLiga) {
        this.getEquiposEnLiga = getEquiposEnLiga;
    }

    /**
     * Sets get liga para partido listener.
     *
     * @param getLigaParaPartidoListener the get liga para partido listener
     */
    public void setGetLigaParaPartidoListener(IGetLigaParaPartidoListener getLigaParaPartidoListener) {
        this.getLigaParaPartidoListener = getLigaParaPartidoListener;
    }

    /**
     * Sets get partidos para un partido listener.
     *
     * @param getPartidosParaUnPartidoListener the get partidos para un partido listener
     */
    public void setGetPartidosParaUnPartidoListener(IGetPartidosParaUnPartidoListener getPartidosParaUnPartidoListener) {
        this.getPartidosParaUnPartidoListener = getPartidosParaUnPartidoListener;
    }
}
