package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

class PlantillaTest {

    @Test
    void testPlantilla(){
        ArrayList<Jugador> list = new ArrayList<>();

        Plantilla plantilla = new Plantilla();
        plantilla.setJugadores(list);

        Assert.assertEquals(0,plantilla.getJugadores().size());
    }
}
