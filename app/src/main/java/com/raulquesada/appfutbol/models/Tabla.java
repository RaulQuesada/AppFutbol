package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Tabla.
 */
public class Tabla {

    /**
     * The Equipos.
     */
    @SerializedName("table")
    @Expose
    private List<Equipo> equipos = null;

    /**
     * The Info leyendas.
     */
    @SerializedName("legends")
    @Expose
    private List<InfoLeyenda> infoLeyendas = null;

    /**
     * Gets leyendas.
     *
     * @return the leyendas
     */
    public List<InfoLeyenda> getinfoLeyendas() {
        return infoLeyendas;
    }

    /**
     * Gets equipos.
     *
     * @return the equipos
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * Sets equipos.
     *
     * @param equipos the equipos
     */
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    /**
     * Sets info leyendas.
     *
     * @param infoLeyendas the info leyendas
     */
    public void setInfoLeyendas(List<InfoLeyenda> infoLeyendas) {
        this.infoLeyendas = infoLeyendas;
    }
}
