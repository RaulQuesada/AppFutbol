package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Info leyenda.
 */
public class InfoLeyenda {

    /**
     * The Pos.
     */
    @SerializedName("group")
    @Expose
    private int pos;

    /**
     * The Tipo leyendas.
     */
    @SerializedName("legend")
    @Expose
    private List<TipoLeyenda> tipoLeyendas;

    /**
     * Instantiates a new Info leyenda.
     *
     * @param pos          the pos
     * @param tipoLeyendas the tipo leyendas
     */
    public InfoLeyenda(int pos, List<TipoLeyenda> tipoLeyendas) {
        this.pos = pos;
        this.tipoLeyendas = tipoLeyendas;
    }

    /**
     * Gets pos.
     *
     * @return the pos
     */
    public int getPos() {
        return pos;
    }

    /**
     * Sets pos.
     *
     * @param pos the pos
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Gets tipo leyendas.
     *
     * @return the tipo leyendas
     */
    public List<TipoLeyenda> getTipoLeyendas() {
        return tipoLeyendas;
    }

    /**
     * Sets tipo leyendas.
     *
     * @param tipoLeyendas the tipo leyendas
     */
    public void setTipoLeyendas(List<TipoLeyenda> tipoLeyendas) {
        this.tipoLeyendas = tipoLeyendas;
    }
}
