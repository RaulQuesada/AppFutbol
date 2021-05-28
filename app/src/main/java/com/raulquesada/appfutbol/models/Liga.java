package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Liga {
    @SerializedName("team")
    @Expose
    private List<EquipoEnLiga> equiposEnLiga;

    public List<EquipoEnLiga> getEquiposEnLiga() {
        return equiposEnLiga;
    }

    public void setEquiposEnLiga(List<EquipoEnLiga> equiposEnLiga) {
        this.equiposEnLiga = equiposEnLiga;
    }
}
