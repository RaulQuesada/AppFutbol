package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

class EquipoEnLigaTest {

    @Test
    void testEquipoEnliga(){
        final String ID = "id";
        final String ID_COMP = "id_comp";

        EquipoEnLiga equipoEnLigaTest = new EquipoEnLiga(ID,ID_COMP);
        equipoEnLigaTest.setId(ID+"test");
        equipoEnLigaTest.setId_comp(ID_COMP+"test");

        Assert.assertEquals("idtest",equipoEnLigaTest.getId());
        Assert.assertEquals("id_comptest",equipoEnLigaTest.getId_comp());
    }
}
