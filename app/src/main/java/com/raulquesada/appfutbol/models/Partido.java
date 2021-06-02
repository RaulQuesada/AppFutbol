package com.raulquesada.appfutbol.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Partido.
 */
public class Partido implements Serializable {

    /**
     * The Jornada.
     */
    @SerializedName("round")
    @Expose
    private String jornada;

    /**
     * The Id local.
     */
    @SerializedName("dteam1")
    @Expose
    private String idLocal;

    /**
     * The Id visitor.
     */
    @SerializedName("dteam2")
    @Expose
    private String idVisitor;

    /**
     * The Local.
     */
    @SerializedName("local")
    @Expose
    private String local;

    /**
     * The Visitor.
     */
    @SerializedName("visitor")
    @Expose
    private String visitor;

    /**
     * The Local shield.
     */
    @SerializedName("local_shield")
    @Expose
    private String local_shield;

    /**
     * The Visitor shield.
     */
    @SerializedName("visitor_shield")
    @Expose
    private String visitor_shield;

    /**
     * The Date.
     */
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * The Hour.
     */
    @SerializedName("hour")
    @Expose
    private String hour;

    /**
     * The Competition name.
     */
    @SerializedName("competition_name")
    @Expose
    private String competition_name;

    /**
     * The Competition id.
     */
    @SerializedName("competition_id")
    @Expose
    private String competition_id;

    /**
     * The Minute.
     */
    @SerializedName("minute")
    @Expose
    private String minute;

    /**
     * The Local goals.
     */
    @SerializedName("local_goals")
    @Expose
    private String local_goals;

    /**
     * The Visitor goals.
     */
    @SerializedName("visitor_goals")
    @Expose
    private String visitor_goals;

    /**
     * The Live minute.
     */
    @SerializedName("live_minute")
    @Expose
    private String live_minute;

    /**
     * The Result.
     */
    @SerializedName("result")
    @Expose
    private String result;

    /**
     * The Canales.
     */
    @SerializedName("channels")
    @Expose
    private List<Canal> canales;

    /**
     * Instantiates a new Partido.
     */
    public Partido() {
    }

    /**
     * Instantiates a new Partido.
     *
     * @param jornada        the jornada
     * @param local          the local
     * @param visitor        the visitor
     * @param local_shield   the local shield
     * @param visitor_shield the visitor shield
     * @param date           the date
     * @param hour           the hour
     * @param minute         the minute
     * @param local_goals    the local goals
     * @param visitor_goals  the visitor goals
     * @param live_minute    the live minute
     */
    public Partido(String jornada, String local, String visitor, String local_shield, String visitor_shield, String date, String hour, String minute, String local_goals, String visitor_goals, String live_minute) {
        this.jornada = jornada;
        this.local = local;
        this.visitor = visitor;
        this.local_shield = local_shield;
        this.visitor_shield = visitor_shield;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.local_goals = local_goals;
        this.visitor_goals = visitor_goals;
        this.live_minute = live_minute;
    }

    /**
     * Sets canales.
     *
     * @param canales the canales
     */
    public void setCanales(List<Canal> canales) {
        this.canales = canales;
    }

    /**
     * Get canales canal [ ].
     *
     * @return the canal [ ]
     */
    public List<Canal> getCanales() {
        return canales;
    }

    /**
     * Gets competition id.
     *
     * @return the competition id
     */
    public String getCompetition_id() {
        return competition_id;
    }

    /**
     * Sets competition id.
     *
     * @param competition_id the competition id
     */
    public void setCompetition_id(String competition_id) {
        this.competition_id = competition_id;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Gets live minute.
     *
     * @return the live minute
     */
    public String getLive_minute() {
        return live_minute;
    }

    /**
     * Sets live minute.
     *
     * @param live_minute the live minute
     */
    public void setLive_minute(String live_minute) {
        this.live_minute = live_minute;
    }

    /**
     * Gets jornada.
     *
     * @return the jornada
     */
    public String getJornada() {
        return jornada;
    }

    /**
     * Sets jornada.
     *
     * @param jornada the jornada
     */
    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    /**
     * Gets local.
     *
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * Sets local.
     *
     * @param local the local
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Gets visitor.
     *
     * @return the visitor
     */
    public String getVisitor() {
        return visitor;
    }

    /**
     * Sets visitor.
     *
     * @param visitor the visitor
     */
    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    /**
     * Gets local shield.
     *
     * @return the local shield
     */
    public String getLocal_shield() {
        return local_shield;
    }

    /**
     * Sets local shield.
     *
     * @param local_shield the local shield
     */
    public void setLocal_shield(String local_shield) {
        this.local_shield = local_shield;
    }

    /**
     * Gets visitor shield.
     *
     * @return the visitor shield
     */
    public String getVisitor_shield() {
        return visitor_shield;
    }

    /**
     * Sets visitor shield.
     *
     * @param visitor_shield the visitor shield
     */
    public void setVisitor_shield(String visitor_shield) {
        this.visitor_shield = visitor_shield;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * Sets hour.
     *
     * @param hour the hour
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * Gets minute.
     *
     * @return the minute
     */
    public String getMinute() {
        return minute;
    }

    /**
     * Sets minute.
     *
     * @param minute the minute
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * Gets local goals.
     *
     * @return the local goals
     */
    public String getLocal_goals() {
        return local_goals;
    }

    /**
     * Sets local goals.
     *
     * @param local_goals the local goals
     */
    public void setLocal_goals(String local_goals) {
        this.local_goals = local_goals;
    }

    /**
     * Gets visitor goals.
     *
     * @return the visitor goals
     */
    public String getVisitor_goals() {
        return visitor_goals;
    }

    /**
     * Sets visitor goals.
     *
     * @param visitor_goals the visitor goals
     */
    public void setVisitor_goals(String visitor_goals) {
        this.visitor_goals = visitor_goals;
    }

    /**
     * Gets id local.
     *
     * @return the id local
     */
    public String getIdLocal() {
        return idLocal;
    }

    /**
     * Sets id local.
     *
     * @param idLocal the id local
     */
    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    /**
     * Gets id visitor.
     *
     * @return the id visitor
     */
    public String getIdVisitor() {
        return idVisitor;
    }

    /**
     * Sets id visitor.
     *
     * @param idVisitor the id visitor
     */
    public void setIdVisitor(String idVisitor) {
        this.idVisitor = idVisitor;
    }

    /**
     * Gets competition name.
     *
     * @return the competition name
     */
    public String getCompetition_name() {
        return competition_name;
    }

    /**
     * Sets competition name.
     *
     * @param competition_name the competition name
     */
    public void setCompetition_name(String competition_name) {
        this.competition_name = competition_name;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "jornada='" + jornada + '\'' +
                ", idLocal='" + idLocal + '\'' +
                ", idVisitor='" + idVisitor + '\'' +
                ", local='" + local + '\'' +
                ", visitor='" + visitor + '\'' +
                ", local_shield='" + local_shield + '\'' +
                ", visitor_shield='" + visitor_shield + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", competition_name='" + competition_name + '\'' +
                ", competition_id='" + competition_id + '\'' +
                ", minute='" + minute + '\'' +
                ", local_goals='" + local_goals + '\'' +
                ", visitor_goals='" + visitor_goals + '\'' +
                ", live_minute='" + live_minute + '\'' +
                ", result='" + result + '\'' +
                ", canales=" + canales +
                '}';
    }
}
