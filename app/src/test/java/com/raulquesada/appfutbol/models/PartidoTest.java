package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Partido test.
 */
public class PartidoTest {

    /**
     * Test partido.
     */
    @Test
    public void testPartido(){
        final String ID_LOCAL = "idlocal";
        final String ID_VISITOR = "idvisitor";
        final String JORNADA = "jornada";
        final String LOCAL = "local";
        final String VISITOR = "visitor";
        final String LOCAL_SHIELD = "lshield";
        final String VISITOR_SHIELD = "vshield";
        final String DATE = "date";
        final String HOUR = "hour";
        final String MINUTE = "minute";
        final String LOCAL_GOALS = "lgoals";
        final String VISITOR_GOALS = "vgoals";
        final String LIVE_MINUTE = "liveminute";
        final String COMPETITION_NAME = "cname";
        final String COMPETITION_ID = "cid";
        final String RESULT = "result";

        Canal[] caneles = new Canal[]{new Canal("1","movistarplus","urlimage")};

        Partido partidoTest = new Partido(JORNADA,LOCAL,VISITOR,LOCAL_SHIELD,VISITOR_SHIELD,DATE,HOUR,MINUTE,LOCAL_GOALS,VISITOR_GOALS,LIVE_MINUTE);

        partidoTest.setJornada(JORNADA+"test");
        partidoTest.setLocal(LOCAL+"test");
        partidoTest.setVisitor(VISITOR+"test");
        partidoTest.setLocal_shield(LOCAL_SHIELD+"test");
        partidoTest.setVisitor_shield(VISITOR_SHIELD+"test");
        partidoTest.setDate(DATE+"test");
        partidoTest.setHour(HOUR+"test");
        partidoTest.setMinute(MINUTE+"test");
        partidoTest.setLocal_goals(LOCAL_GOALS+"test");
        partidoTest.setVisitor_goals(VISITOR_GOALS+"test");
        partidoTest.setLive_minute(LIVE_MINUTE+"test");
        partidoTest.setIdLocal(ID_LOCAL+"test");
        partidoTest.setIdVisitor(ID_VISITOR+"test");
        partidoTest.setCompetition_name(COMPETITION_NAME+"test");
        partidoTest.setCompetition_id(COMPETITION_ID+"test");
        partidoTest.setCanales(caneles);
        partidoTest.setResult(RESULT+"test");

        Assert.assertEquals("jornadatest",partidoTest.getJornada());
        Assert.assertEquals("localtest",partidoTest.getLocal());
        Assert.assertEquals("visitortest",partidoTest.getVisitor());
        Assert.assertEquals("lshieldtest",partidoTest.getLocal_shield());
        Assert.assertEquals("vshieldtest",partidoTest.getVisitor_shield());
        Assert.assertEquals("datetest",partidoTest.getDate());
        Assert.assertEquals("hourtest",partidoTest.getHour());
        Assert.assertEquals("minutetest",partidoTest.getMinute());
        Assert.assertEquals("lgoalstest",partidoTest.getLocal_goals());
        Assert.assertEquals("vgoalstest",partidoTest.getVisitor_goals());
        Assert.assertEquals("liveminutetest",partidoTest.getLive_minute());
        Assert.assertEquals("idlocaltest",partidoTest.getIdLocal());
        Assert.assertEquals("idvisitortest",partidoTest.getIdVisitor());
        Assert.assertEquals("cnametest",partidoTest.getCompetition_name());
        Assert.assertEquals("cidtest",partidoTest.getCompetition_id());
        Assert.assertEquals("resulttest",partidoTest.getResult());
        Assert.assertEquals(1,partidoTest.getCanales().length);
    }
}
