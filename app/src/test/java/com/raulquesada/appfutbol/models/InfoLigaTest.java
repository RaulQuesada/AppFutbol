package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

class InfoLigaTest {

    @Test
    void testInfoLiga(){
        final String CURRENT_ROUND = "30";
        final String TOTAL_ROUNDS = "38";

        InfoLiga.LigaDetails ligaDetailsTest = new InfoLiga.LigaDetails(CURRENT_ROUND,TOTAL_ROUNDS);

        ligaDetailsTest.setCurrent_round(CURRENT_ROUND+"test");
        ligaDetailsTest.setTotal_rounds(TOTAL_ROUNDS+"test");

        Assert.assertTrue(ligaDetailsTest.toString().length()>0);

        InfoLiga infoLigaTest = new InfoLiga();
        infoLigaTest.setLeague(ligaDetailsTest);

        Assert.assertEquals("30test",infoLigaTest.getLeague().getCurrent_round());
        Assert.assertEquals("38test",infoLigaTest.getLeague().getTotal_rounds());
    }
}
