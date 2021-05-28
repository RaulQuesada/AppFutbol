package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

class TipoLeyendaTest {

    @Test
    void testTipoLeyenda(){
        final int POS = 1;
        final String TITLE = "title";
        final String CLASS_COLOR = "class_color";

        TipoLeyenda tipoLeyenda = new TipoLeyenda(POS,TITLE,CLASS_COLOR);

        tipoLeyenda.setPos(POS+1);
        tipoLeyenda.setTitle(TITLE+"test");
        tipoLeyenda.setClass_color(CLASS_COLOR+"test");

        Assert.assertEquals(2, tipoLeyenda.getPos());
        Assert.assertEquals("titletest", tipoLeyenda.getTitle());
        Assert.assertEquals("class_colortest", tipoLeyenda.getClass_color());
    }
}
