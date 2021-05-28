package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoLeyenda {

    @SerializedName("group")
    @Expose
    private int pos;

    @SerializedName("legend")
    @Expose
    private List<TipoLeyenda> tipoLeyendas;

    public InfoLeyenda(int pos, List<TipoLeyenda> tipoLeyendas) {
        this.pos = pos;
        this.tipoLeyendas = tipoLeyendas;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public List<TipoLeyenda> getTipoLeyendas() {
        return tipoLeyendas;
    }

    public void setTipoLeyendas(List<TipoLeyenda> tipoLeyendas) {
        this.tipoLeyendas = tipoLeyendas;
    }
}
