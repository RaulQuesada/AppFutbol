package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

class JugadorTest {

    @Test
    void testJugador(){
        final String NAME = "name";
        final String LAST_NAME = "lastname";
        final String ROLE = "role";
        final String SQUADNUMBER = "2";
        final String CC = "ES";
        final String IMAGE = "urlimage";

        Jugador jugador = new Jugador(NAME,LAST_NAME,ROLE,SQUADNUMBER,CC,IMAGE);

        jugador.setImage(IMAGE+"test");
        jugador.setCountryCode(CC+"test");
        jugador.setLast_name(LAST_NAME+"test");
        jugador.setName(NAME+"test");
        jugador.setRole(ROLE+"test");
        jugador.setSquadNumber(SQUADNUMBER+"test");

        Assert.assertEquals("nametest",jugador.getName());
        Assert.assertEquals("lastnametest",jugador.getLast_name());
        Assert.assertEquals("roletest",jugador.getRole());
        Assert.assertEquals("2test",jugador.getSquadNumber());
        Assert.assertEquals("EStest",jugador.getCountryCode());
        Assert.assertEquals("urlimagetest",jugador.getImage());
    }
}
