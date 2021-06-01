package com.raulquesada.appfutbol.util;

import com.raulquesada.appfutbol.models.Liga;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Lib test.
 */
public class LibTest {

    /**
     * Get stadium check return ok.
     */
    @Test
    public void getStadium_CheckReturn_Ok(){
        Assert.assertEquals("Mendizorroza",Lib.getStadium(137));
        Assert.assertEquals("San Mamés",Lib.getStadium(347));
        Assert.assertEquals("Wanda Metropolitano",Lib.getStadium(369));
        Assert.assertEquals("Camp Nou",Lib.getStadium(429));
        Assert.assertEquals("Ramón de Carranza",Lib.getStadium(603));
        Assert.assertEquals("Balaídos",Lib.getStadium(712));
        Assert.assertEquals("Ipurúa",Lib.getStadium(957));
        Assert.assertEquals("Manuel Martínez Valero",Lib.getStadium(975));
        Assert.assertEquals("Coliseum Alfonso Pérez",Lib.getStadium(1217));
        Assert.assertEquals("Nuevo Los Cármenes",Lib.getStadium(4235));
        Assert.assertEquals("El Alcoraz",Lib.getStadium(1339));
        Assert.assertEquals("Ciutat de Valencia",Lib.getStadium(1547));
        Assert.assertEquals("El Sadar",Lib.getStadium(1887));
        Assert.assertEquals("Reale Arena",Lib.getStadium(2120));
        Assert.assertEquals("Benito Villamarín",Lib.getStadium(486));
        Assert.assertEquals("Santiago Bernabéu",Lib.getStadium(2107));
        Assert.assertEquals("José Zorrilla",Lib.getStadium(2654));
        Assert.assertEquals("Sánchez Pizjuán",Lib.getStadium(1102));
        Assert.assertEquals("Mestalla",Lib.getStadium(2647));
        Assert.assertEquals("La Cerámica",Lib.getStadium(2716));
        Assert.assertEquals("Carlos Belmonte",Lib.getStadium(140));
        Assert.assertEquals("Santo Domingo",Lib.getStadium(64));
        Assert.assertEquals("Juegos Mediterráneos",Lib.getStadium(183));
        Assert.assertEquals("Nuevo Castalia",Lib.getStadium(673));
        Assert.assertEquals("RCDE Stadium",Lib.getStadium(998));
        Assert.assertEquals("Municipal Cartagonova",Lib.getStadium(643));
        Assert.assertEquals("Fernando Torres",Lib.getStadium(1179));
        Assert.assertEquals("Montilivi",Lib.getStadium(1236));
        Assert.assertEquals("Gran Canaria",Lib.getStadium(2563));
        Assert.assertEquals("Butarque",Lib.getStadium(1535));
        Assert.assertEquals("Ángel Carro",Lib.getStadium(1598));
        Assert.assertEquals("La Rosaleda",Lib.getStadium(1617));
        Assert.assertEquals("Son Moix",Lib.getStadium(1623));
        Assert.assertEquals("Anduva",Lib.getStadium(1699));
        Assert.assertEquals("El Toralín",Lib.getStadium(3287));
        Assert.assertEquals("Vallecas",Lib.getStadium(2080));
        Assert.assertEquals("NMR Carlos Tartiere",Lib.getStadium(2115));
        Assert.assertEquals("La Romareda",Lib.getStadium(2136));
        Assert.assertEquals("El Molinón",Lib.getStadium(2125));
        Assert.assertEquals("Heliodoro Rodríguez López",Lib.getStadium(2477));
        Assert.assertEquals("Nova Creu Alta",Lib.getStadium(2198));
        Assert.assertEquals("Las Gaunas",Lib.getStadium(1578));
    }

    /**
     * Change format date exists ok.
     */
    @Test
    public void changeFormatDate_Exists_Ok(){
        String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
        Assert.assertTrue(Lib.changeFormatDate("17/08/2000").matches(datePattern));
    }
}
