package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The type Jornada.
 */
public class Jornada implements Serializable {

    /**
     * The Partidos.
     */
    @SerializedName("match")
    @Expose
    private List<Partido> partidos = null;

    /**
     * Gets partidos.
     *
     * @return the partidos
     */
    public List<Partido> getPartidos() {
        return partidos;
    }

    /**
     * Sets partidos.
     *
     * @param partidos the partidos
     */
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}
