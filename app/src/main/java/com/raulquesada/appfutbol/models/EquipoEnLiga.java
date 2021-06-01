package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Equipo en liga.
 */
public class EquipoEnLiga {

    /**
     * The Id.
     */
    @SerializedName("id")
    @Expose
    private String id;

    /**
     * The Id comp.
     */
    @SerializedName("id_comp")
    @Expose
    private String id_comp;

    /**
     * Instantiates a new Equipo en liga.
     *
     * @param id      the id
     * @param id_comp the id comp
     */
    public EquipoEnLiga(String id, String id_comp) {
        this.id = id;
        this.id_comp = id_comp;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets id comp.
     *
     * @return the id comp
     */
    public String getId_comp() {
        return id_comp;
    }

    /**
     * Sets id comp.
     *
     * @param id_comp the id comp
     */
    public void setId_comp(String id_comp) {
        this.id_comp = id_comp;
    }
}
