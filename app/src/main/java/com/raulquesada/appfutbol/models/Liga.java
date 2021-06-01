package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Liga.
 */
public class Liga {
    /**
     * The Equipos en liga.
     */
    @SerializedName("team")
    @Expose
    private List<EquipoEnLiga> equiposEnLiga;

    /**
     * Gets equipos en liga.
     *
     * @return the equipos en liga
     */
    public List<EquipoEnLiga> getEquiposEnLiga() {
        return equiposEnLiga;
    }

    /**
     * Sets equipos en liga.
     *
     * @param equiposEnLiga the equipos en liga
     */
    public void setEquiposEnLiga(List<EquipoEnLiga> equiposEnLiga) {
        this.equiposEnLiga = equiposEnLiga;
    }
}
