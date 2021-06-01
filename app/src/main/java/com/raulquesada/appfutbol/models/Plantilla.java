package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Plantilla.
 */
public class Plantilla {

    /**
     * The Jugadors.
     */
    @SerializedName("player")
    @Expose
    private List<Jugador> jugadors;

    /**
     * Gets jugadores.
     *
     * @return the jugadores
     */
    public List<Jugador> getJugadores() {
        return jugadors;
    }

    /**
     * Sets jugadores.
     *
     * @param jugadors the jugadors
     */
    public void setJugadores(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }
}
