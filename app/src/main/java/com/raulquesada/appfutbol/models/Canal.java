package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The type Canal.
 */
public class Canal implements Serializable {

    //id del canal
    @SerializedName("id")
    @Expose
    private String id;

    //nombre del canal
    @SerializedName("name")
    @Expose
    private String name;

    //url de la imagen
    @SerializedName("image")
    @Expose
    private String urlImage;

    /**
     * Instantiates a new Canal.
     */
    public Canal(){}

    /**
     * Instantiates a new Canal.
     *
     * @param id       the id
     * @param name     the name
     * @param urlImage the url image
     */
    public Canal(String id, String name, String urlImage) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets url image.
     *
     * @return the url image
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * Sets url image.
     *
     * @param urlImage the url image
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
