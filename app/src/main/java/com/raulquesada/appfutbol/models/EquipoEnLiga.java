package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EquipoEnLiga {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("id_comp")
    @Expose
    private String id_comp;

    public EquipoEnLiga(String id, String id_comp) {
        this.id = id;
        this.id_comp = id_comp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_comp() {
        return id_comp;
    }

    public void setId_comp(String id_comp) {
        this.id_comp = id_comp;
    }
}
