package com.raulquesada.appfutbol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoLiga {

    @SerializedName("league")
    @Expose
    private LigaDetails league;

    public LigaDetails getLeague() {
        return league;
    }

    public void setLeague(LigaDetails league) {
        this.league = league;
    }

    public static class LigaDetails{
        @SerializedName("current_round")
        @Expose
        private String current_round;

        @SerializedName("total_rounds")
        @Expose
        private String total_rounds;

        public LigaDetails(String current_round, String total_rounds) {
            this.current_round = current_round;
            this.total_rounds = total_rounds;
        }

        public String getCurrent_round() {
            return current_round;
        }

        public void setCurrent_round(String current_round) {
            this.current_round = current_round;
        }

        public String getTotal_rounds() {
            return total_rounds;
        }

        public void setTotal_rounds(String total_rounds) {
            this.total_rounds = total_rounds;
        }
    }
}
