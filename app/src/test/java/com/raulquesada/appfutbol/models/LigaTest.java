package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Liga test.
 */
public class LigaTest {

    /**
     * Test liga.
     */
    @Test
    public void testLiga(){
        List<EquipoEnLiga> equiposEnLiga = new ArrayList<>();

        Liga liga = new Liga();
        liga.setEquiposEnLiga(equiposEnLiga);

        Assert.assertEquals(0, liga.getEquiposEnLiga().size());
    }
}
