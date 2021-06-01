package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The type Equipo.
 */
public class Equipo implements Serializable {

    //nombre del equipo
    @SerializedName("team")
    @Expose
    private String team;

    //id del equipo
    @SerializedName("id")
    @Expose
    private String id;

    //puntos del equipo
    @SerializedName("points")
    @Expose
    private String points;

    //leyenda del equipo
    @SerializedName("mark")
    @Expose
    private String mark;

    //victorias del equipo
    @SerializedName("wins")
    @Expose
    private int wins;

    //empates del equipo
    @SerializedName("draws")
    @Expose
    private int draws;

    //derrotas del equipo
    @SerializedName("losses")
    @Expose
    private int losses;

    //bandera del pais del equipo
    @SerializedName("cflag")
    @Expose
    private String cflag;

    //escudo del equipo
    @SerializedName("shield")
    @Expose
    private String shield;

    //Goles a favor del equipo
    @SerializedName("gf")
    @Expose
    private String gf;

    //goles en contra del equipo
    @SerializedName("ga")
    @Expose
    private String ga;

    //media goles a favot y en contra
    @SerializedName("avg")
    @Expose
    private int avg;

    //jornada actual
    @SerializedName("round")
    @Expose
    private String round;

    //posicion del equipo
    @SerializedName("pos")
    @Expose
    private String pos;

    //codigo del pais del equipo
    @SerializedName("countrycode")
    @Expose
    private String countrycode;

    //nombre abreviado del equipo
    @SerializedName("abbr")
    @Expose
    private String abbr;

    //forma del equipo
    @SerializedName("form")
    @Expose
    private String form;

    //division del equipo
    private int division;

    //partido de la jornada actual
    private Partido partidoFavorito;

    /**
     * Instantiates a new Equipo.
     *
     * @param team        the team
     * @param mark        the mark
     * @param points      the points
     * @param wins        the wins
     * @param draws       the draws
     * @param losses      the losses
     * @param shield      the shield
     * @param gf          the gf
     * @param ga          the ga
     * @param avg         the avg
     * @param round       the round
     * @param pos         the pos
     * @param countrycode the countrycode
     * @param abbr        the abbr
     * @param form        the form
     */
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

    /**
     * Gets partido favorito.
     *
     * @return the partido favorito
     */
    public Partido getPartidoFavorito() {
        return partidoFavorito;
    }

    /**
     * Sets partido favorito.
     *
     * @param partidoFavorito the partido favorito
     */
    public void setPartidoFavorito(Partido partidoFavorito) {
        this.partidoFavorito = partidoFavorito;
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
     * Gets cflag.
     *
     * @return the cflag
     */
    public String getCflag() {
        return cflag;
    }

    /**
     * Instantiates a new Equipo.
     */
    public Equipo() {
    }

    /**
     * Gets mark.
     *
     * @return the mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public String getPoints() {
        return points;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(String points) {
        this.points = points;
    }

    /**
     * Gets wins.
     *
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets wins.
     *
     * @param wins the wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Gets draws.
     *
     * @return the draws
     */
    public int getDraws() {
        return draws;
    }

    /**
     * Sets draws.
     *
     * @param draws the draws
     */
    public void setDraws(int draws) {
        this.draws = draws;
    }

    /**
     * Gets losses.
     *
     * @return the losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Sets losses.
     *
     * @param losses the losses
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Gets shield.
     *
     * @return the shield
     */
    public String getShield() {
        return shield;
    }

    /**
     * Sets shield.
     *
     * @param shield the shield
     */
    public void setShield(String shield) {
        this.shield = shield;
    }

    /**
     * Gets gf.
     *
     * @return the gf
     */
    public String getGf() {
        return gf;
    }

    /**
     * Sets gf.
     *
     * @param gf the gf
     */
    public void setGf(String gf) {
        this.gf = gf;
    }

    /**
     * Gets ga.
     *
     * @return the ga
     */
    public String getGa() {
        return ga;
    }

    /**
     * Sets ga.
     *
     * @param ga the ga
     */
    public void setGa(String ga) {
        this.ga = ga;
    }

    /**
     * Gets avg.
     *
     * @return the avg
     */
    public int getAvg() {
        return avg;
    }

    /**
     * Sets avg.
     *
     * @param avg the avg
     */
    public void setAvg(int avg) {
        this.avg = avg;
    }

    /**
     * Gets round.
     *
     * @return the round
     */
    public String getRound() {
        return round;
    }

    /**
     * Sets round.
     *
     * @param round the round
     */
    public void setRound(String round) {
        this.round = round;
    }

    /**
     * Gets pos.
     *
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * Sets pos.
     *
     * @param pos the pos
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * Gets countrycode.
     *
     * @return the countrycode
     */
    public String getCountrycode() {
        return countrycode;
    }

    /**
     * Sets countrycode.
     *
     * @param countrycode the countrycode
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    /**
     * Gets abbr.
     *
     * @return the abbr
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * Sets abbr.
     *
     * @param abbr the abbr
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    /**
     * Gets form.
     *
     * @return the form
     */
    public String getForm() {
        return form;
    }

    /**
     * Sets form.
     *
     * @param form the form
     */
    public void setForm(String form) {
        this.form = form;
    }

    /**
     * Get played matches int.
     *
     * @return the int
     */
    public int getPlayedMatches(){
        return getWins()+getDraws()+getLosses();
    }

    /**
     * Sets cflag.
     *
     * @param cflag the cflag
     */
    public void setCflag(String cflag) {
        this.cflag = cflag;
    }

    /**
     * Gets division.
     *
     * @return the division
     */
    public int getDivision() {
        return division;
    }

    /**
     * Sets division.
     *
     * @param division the division
     */
    public void setDivision(int division) {
        this.division = division;
    }
}
