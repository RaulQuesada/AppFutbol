package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TipoLeyenda {

    @SerializedName("pos")
    @Expose
    private int pos;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("class_color")
    @Expose
    private String class_color;

    public TipoLeyenda(int pos, String title, String class_color) {
        this.pos = pos;
        this.title = title;
        this.class_color = class_color;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClass_color() {
        return class_color;
    }

    public void setClass_color(String class_color) {
        this.class_color = class_color;
    }
}
