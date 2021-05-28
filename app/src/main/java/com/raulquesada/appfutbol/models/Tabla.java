package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tabla {

    @SerializedName("table")
    @Expose
    private List<Equipo> equipos = null;

    @SerializedName("legends")
    @Expose
    private List<InfoLeyenda> infoLeyendas = null;

    public List<InfoLeyenda> getinfoLeyendas() {
        return infoLeyendas;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void setInfoLeyendas(List<InfoLeyenda> infoLeyendas) {
        this.infoLeyendas = infoLeyendas;
    }
}
