package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jugador {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("squadNumber")
    @Expose
    private String squadNumber;

    @SerializedName("CountryCode")
    @Expose
    private String countryCode;

    @SerializedName("image")
    @Expose
    private String image;

    public Jugador(String name, String last_name, String role, String squadNumber, String countryCode, String image) {
        this.name = name;
        this.last_name = last_name;
        this.role = role;
        this.squadNumber = squadNumber;
        this.countryCode = countryCode;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(String squadNumber) {
        this.squadNumber = squadNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
