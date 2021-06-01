package com.raulquesada.appfutbol.api;

import com.raulquesada.appfutbol.models.InfoLiga;
import com.raulquesada.appfutbol.models.Jornada;
import com.raulquesada.appfutbol.models.Liga;
import com.raulquesada.appfutbol.models.Plantilla;
import com.raulquesada.appfutbol.models.Tabla;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAPIService {

    /**
     * Obtener la clasificación
     * @param division liga
     * @param season temporaeda seleccioanda
     * @return Tabla con los equipos
     */
    @GET("api/api.php?key=7dbcbc800b3ab22dc134fd1d9366b32f&tz=Europe/Madrid&format=json&req=tables&group=1")
    Call<Tabla> getTabla(@Query("league")int division,@Query("year")int season);

    /**
     * Obtener la jornada
     * @param division liga
     * @param jornada jornada seleccionada
     * @return jornada con los partidos
     */
    @GET("api/api.php?key=7dbcbc800b3ab22dc134fd1d9366b32f&tz=Europe/Madrid&format=json&req=matchs&order=twin&twolegged=1&year=2021")
    Call<Jornada> getJornada(@Query("league")int division, @Query("round")int jornada);

    /**
     * Obtener datos de la liga
     * @param nLiga liga
     * @return datos de la liga
     */
    @GET("api/api.php?key=7dbcbc800b3ab22dc134fd1d9366b32f&tz=Europe/Madrid&format=json&req=league_status&group=1&year=2021")
    Call<InfoLiga> getLiga(@Query("id")int nLiga);

    /**
     * Obtener la plantilla
     * @param team id equipo
     * @return plantilla del equipo
     */
    @GET("api/api.php?key=7dbcbc800b3ab22dc134fd1d9366b32f&tz=Europe/Madrid&format=json&req=team_players&year=2021")
    Call<Plantilla> getPlantilla(@Query("team")int team);

    /**
     * Obtener los equipo
     * @param division liga
     * @return equipos de la liga
     */
    @GET("api/api.php?key=7dbcbc800b3ab22dc134fd1d9366b32f&tz=Europe/Madrid&format=json&req=teams&year=2021")
    Call<Liga> getEquipos(@Query("league")int division);
}
