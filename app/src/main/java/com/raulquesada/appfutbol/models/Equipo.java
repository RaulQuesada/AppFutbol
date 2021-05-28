package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Equipo implements Serializable {

    @SerializedName("team")
    @Expose
    private String team;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("points")
    @Expose
    private String points;

    @SerializedName("mark")
    @Expose
    private String mark;

    @SerializedName("wins")
    @Expose
    private int wins;

    @SerializedName("draws")
    @Expose
    private int draws;

    @SerializedName("losses")
    @Expose
    private int losses;

    @SerializedName("cflag")
    @Expose
    private String cflag;

    @SerializedName("shield")
    @Expose
    private String shield;

    @SerializedName("gf")
    @Expose
    private String gf;

    @SerializedName("ga")
    @Expose
    private String ga;

    @SerializedName("avg")
    @Expose
    private int avg;

    @SerializedName("round")
    @Expose
    private String round;

    @SerializedName("pos")
    @Expose
    private String pos;

    @SerializedName("countrycode")
    @Expose
    private String countrycode;

    @SerializedName("abbr")
    @Expose
    private String abbr;

    @SerializedName("form")
    @Expose
    private String form;

    private int division;

    public Equipo(String team, String mark, String points, int wins, int draws, int losses, String shield, String gf, String ga, int avg, String round, String pos, String countrycode, String abbr, String form) {
        this.team = team;
        this.points = points;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.shield = shield;
        this.gf = gf;
        this.ga = ga;
        this.avg = avg;
        this.round = round;
        this.pos = pos;
        this.countrycode = countrycode;
        this.abbr = abbr;
        this.form = form;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCflag() {
        return cflag;
    }

    public Equipo() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getGa() {
        return ga;
    }

    public void setGa(String ga) {
        this.ga = ga;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getPlayedMatches(){
        return getWins()+getDraws()+getLosses();
    }

    public void setCflag(String cflag) {
        this.cflag = cflag;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }
}
