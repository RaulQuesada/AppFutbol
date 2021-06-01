package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Jugador.
 */
public class Jugador {

    /**
     * The Name.
     */
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * The Last name.
     */
    @SerializedName("last_name")
    @Expose
    private String last_name;

    /**
     * The Role.
     */
    @SerializedName("role")
    @Expose
    private String role;

    /**
     * The Squad number.
     */
    @SerializedName("squadNumber")
    @Expose
    private String squadNumber;

    /**
     * The Country code.
     */
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;

    /**
     * The Image.
     */
    @SerializedName("image")
    @Expose
    private String image;

    /**
     * Instantiates a new Jugador.
     *
     * @param name        the name
     * @param last_name   the last name
     * @param role        the role
     * @param squadNumber the squad number
     * @param countryCode the country code
     * @param image       the image
     */
    public Jugador(String name, String last_name, String role, String squadNumber, String countryCode, String image) {
        this.name = name;
        this.last_name = last_name;
        this.role = role;
        this.squadNumber = squadNumber;
        this.countryCode = countryCode;
        this.image = image;
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
     * Gets last name.
     *
     * @return the last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets last name.
     *
     * @param last_name the last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets squad number.
     *
     * @return the squad number
     */
    public String getSquadNumber() {
        return squadNumber;
    }

    /**
     * Sets squad number.
     *
     * @param squadNumber the squad number
     */
    public void setSquadNumber(String squadNumber) {
        this.squadNumber = squadNumber;
    }

    /**
     * Gets country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets country code.
     *
     * @param countryCode the country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
