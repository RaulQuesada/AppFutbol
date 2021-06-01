package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Info liga.
 */
public class InfoLiga {

    /**
     * The League.
     */
    @SerializedName("league")
    @Expose
    private LigaDetails league;

    /**
     * Gets league.
     *
     * @return the league
     */
    public LigaDetails getLeague() {
        return league;
    }

    /**
     * Sets league.
     *
     * @param league the league
     */
    public void setLeague(LigaDetails league) {
        this.league = league;
    }

    /**
     * The type Liga details.
     */
    public static class LigaDetails{
        /**
         * The Current round.
         */
        @SerializedName("current_round")
        @Expose
        private String current_round;

        /**
         * The Total rounds.
         */
        @SerializedName("total_rounds")
        @Expose
        private String total_rounds;

        /**
         * Instantiates a new Liga details.
         *
         * @param current_round the current round
         * @param total_rounds  the total rounds
         */
        public LigaDetails(String current_round, String total_rounds) {
            this.current_round = current_round;
            this.total_rounds = total_rounds;
        }

        /**
         * Gets current round.
         *
         * @return the current round
         */
        public String getCurrent_round() {
            return current_round;
        }

        /**
         * Sets current round.
         *
         * @param current_round the current round
         */
        public void setCurrent_round(String current_round) {
            this.current_round = current_round;
        }

        /**
         * Gets total rounds.
         *
         * @return the total rounds
         */
        public String getTotal_rounds() {
            return total_rounds;
        }

        /**
         * Sets total rounds.
         *
         * @param total_rounds the total rounds
         */
        public void setTotal_rounds(String total_rounds) {
            this.total_rounds = total_rounds;
        }
    }
}
