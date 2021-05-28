package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class LigaTest {

    @Test
    void testLiga(){
        List<EquipoEnLiga> equiposEnLiga = new ArrayList<>();

        Liga liga = new Liga();
        liga.setEquiposEnLiga(equiposEnLiga);

        Assert.assertEquals(0, liga.getEquiposEnLiga().size());
    }
}
