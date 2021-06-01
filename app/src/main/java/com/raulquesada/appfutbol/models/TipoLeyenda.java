package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Tipo leyenda.
 */
public class TipoLeyenda {

    /**
     * The Pos.
     */
    @SerializedName("pos")
    @Expose
    private int pos;

    /**
     * The Title.
     */
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * The Class color.
     */
    @SerializedName("class_color")
    @Expose
    private String class_color;

    /**
     * Instantiates a new Tipo leyenda.
     *
     * @param pos         the pos
     * @param title       the title
     * @param class_color the class color
     */
    public TipoLeyenda(int pos, String title, String class_color) {
        this.pos = pos;
        this.title = title;
        this.class_color = class_color;
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets class color.
     *
     * @return the class color
     */
    public String getClass_color() {
        return class_color;
    }

    /**
     * Sets class color.
     *
     * @param class_color the class color
     */
    public void setClass_color(String class_color) {
        this.class_color = class_color;
    }
}
