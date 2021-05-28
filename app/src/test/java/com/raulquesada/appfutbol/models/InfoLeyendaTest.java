package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

class InfoLeyendaTest {

    @Test
    void testInfoLeyenda(){
        final int POS = 1;
        ArrayList<TipoLeyenda> list = new ArrayList<>();

        InfoLeyenda infoLeyendaTest = new InfoLeyenda(POS,list);
        list.add(new TipoLeyenda(1, "ChampionsLeague","blue"));

        infoLeyendaTest.setTipoLeyendas(list);
        infoLeyendaTest.setPos(POS+1);

        Assert.assertEquals(1,infoLeyendaTest.getTipoLeyendas().size());
        Assert.assertEquals(2,infoLeyendaTest.getPos());

    }
}
