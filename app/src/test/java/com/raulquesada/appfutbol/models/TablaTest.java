package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * The type Tabla test.
 */
public class TablaTest {

    /**
     * Test tabla.
     */
    @Test
    public void testTabla(){
        ArrayList<InfoLeyenda> listInfo = new ArrayList<>();
        ArrayList<Equipo> listEquipo = new ArrayList<>();

        Tabla tabla = new Tabla();
        tabla.setEquipos(listEquipo);
        tabla.setInfoLeyendas(listInfo);

        Assert.assertEquals(0,tabla.getEquipos().size());
        Assert.assertEquals(0,tabla.getinfoLeyendas().size());
    }
}
