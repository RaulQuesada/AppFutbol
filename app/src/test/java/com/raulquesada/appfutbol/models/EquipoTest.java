package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Equipo test.
 */
public class EquipoTest {

    /**
     * Test equipo.
     */
    @Test
    public void testEquipo(){
        final String ID = "id";
        final String TEAM = "team";
        final String MARK = "mark";
        final String POINTS = "points";
        final int WINS = 1;
        final int DRAWS = 2;
        final int LOSSES = 3;
        final String SHIELD = "shield";
        final String CFLAG = "cFlag";
        final String POS = "pos";
        final String ROUND = "round";
        final String GF = "17";
        final String GA = "16";
        final String CC = "countryCode";
        final String ABBR = "abbr";
        final String FORM = "form";
        final int AVG = 3;

        Equipo testEquipo2 = new Equipo();
        Equipo testEquipo = new Equipo(TEAM, MARK, POINTS, WINS,DRAWS,LOSSES, SHIELD, GF, GA, AVG, ROUND, POS, CC, ABBR, FORM);
        testEquipo.setAbbr(ABBR+"test");
        testEquipo.setAvg(AVG+1);
        testEquipo.setCflag(CFLAG+"test");
        testEquipo.setCountrycode(CC+"test");
        testEquipo.setDivision(1);
        testEquipo.setDraws(DRAWS+1);
        testEquipo.setForm(FORM+"test");
        testEquipo.setGa(GA+"test");
        testEquipo.setGf(GF+"test");
        testEquipo.setId(ID+"test");
        testEquipo.setLosses(LOSSES+1);
        testEquipo.setMark(MARK+"test");
        testEquipo.setPoints(POINTS+"test");
        testEquipo.setPos(POS+"test");
        testEquipo.setShield(SHIELD+"test");
        testEquipo.setRound(ROUND+"test");
        testEquipo.setWins(WINS+1);
        testEquipo.setTeam(TEAM+"test");

        Assert.assertEquals("abbrtest",testEquipo.getAbbr());
        Assert.assertEquals(4,testEquipo.getAvg());
        Assert.assertEquals("cFlagtest",testEquipo.getCflag());
        Assert.assertEquals("countryCodetest",testEquipo.getCountrycode());
        Assert.assertEquals(1,testEquipo.getDivision());
        Assert.assertEquals(3,testEquipo.getDraws());
        Assert.assertEquals("formtest",testEquipo.getForm());
        Assert.assertEquals("16test",testEquipo.getGa());
        Assert.assertEquals("17test",testEquipo.getGf());
        Assert.assertEquals("idtest",testEquipo.getId());
        Assert.assertEquals(4,testEquipo.getLosses());
        Assert.assertEquals("marktest",testEquipo.getMark());
        Assert.assertEquals("pointstest",testEquipo.getPoints());
        Assert.assertEquals("postest",testEquipo.getPos());
        Assert.assertEquals("shieldtest",testEquipo.getShield());
        Assert.assertEquals("roundtest",testEquipo.getRound());
        Assert.assertEquals(2,testEquipo.getWins());
        Assert.assertEquals("teamtest",testEquipo.getTeam());
        Assert.assertEquals(9,testEquipo.getPlayedMatches());
    }
}
