package com.raulquesada.appfutbol.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class Partido implements Serializable {

    @SerializedName("round")
    @Expose
    private String jornada;

    @SerializedName("dteam1")
    @Expose
    private String idLocal;

    @SerializedName("dteam2")
    @Expose
    private String idVisitor;

    @SerializedName("local")
    @Expose
    private String local;

    @SerializedName("visitor")
    @Expose
    private String visitor;

    @SerializedName("local_shield")
    @Expose
    private String local_shield;

    @SerializedName("visitor_shield")
    @Expose
    private String visitor_shield;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("hour")
    @Expose
    private String hour;

    @SerializedName("competition_name")
    @Expose
    private String competition_name;

    @SerializedName("competition_id")
    @Expose
    private String competition_id;

    @SerializedName("minute")
    @Expose
    private String minute;

    @SerializedName("local_goals")
    @Expose
    private String local_goals;

    @SerializedName("visitor_goals")
    @Expose
    private String visitor_goals;

    @SerializedName("live_minute")
    @Expose
    private String live_minute;

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("channels")
    @Expose
    private Canal[] canales;

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

    public void setCanales(Canal[] canales) {
        this.canales = canales;
    }

    public Canal[] getCanales() {
        return canales;
    }

    public String getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(String competition_id) {
        this.competition_id = competition_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLive_minute() {
        return live_minute;
    }

    public void setLive_minute(String live_minute) {
        this.live_minute = live_minute;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getLocal_shield() {
        return local_shield;
    }

    public void setLocal_shield(String local_shield) {
        this.local_shield = local_shield;
    }

    public String getVisitor_shield() {
        return visitor_shield;
    }

    public void setVisitor_shield(String visitor_shield) {
        this.visitor_shield = visitor_shield;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getLocal_goals() {
        return local_goals;
    }

    public void setLocal_goals(String local_goals) {
        this.local_goals = local_goals;
    }

    public String getVisitor_goals() {
        return visitor_goals;
    }

    public void setVisitor_goals(String visitor_goals) {
        this.visitor_goals = visitor_goals;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(String idVisitor) {
        this.idVisitor = idVisitor;
    }

    public String getCompetition_name() {
        return competition_name;
    }

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
                ", canales=" + Arrays.toString(canales) +
                '}';
    }
}
