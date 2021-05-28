package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

class JornadaTest {

    @Test
    void testJornada(){
        ArrayList<Partido> list = new ArrayList<>();

        Jornada jornada = new Jornada();
        jornada.setPartidos(list);

        Assert.assertEquals(0,jornada.getPartidos().size());
    }
}
