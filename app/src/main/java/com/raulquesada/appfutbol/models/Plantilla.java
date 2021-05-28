package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Plantilla {

    @SerializedName("player")
    @Expose
    private List<Jugador> jugadors;

    public List<Jugador> getJugadores() {
        return jugadors;
    }

    public void setJugadores(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }
}
