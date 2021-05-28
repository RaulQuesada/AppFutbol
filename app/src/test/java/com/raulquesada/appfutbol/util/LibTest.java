package com.raulquesada.appfutbol.util;

import com.raulquesada.appfutbol.models.Liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LibTest {

    @Test
    void getStadium_CheckReturn_Ok(){
        Assertions.assertEquals("Mendizorroza",Lib.getStadium(137));
        Assertions.assertEquals("San Mamés",Lib.getStadium(347));
        Assertions.assertEquals("Wanda Metropolitano",Lib.getStadium(369));
        Assertions.assertEquals("Camp Nou",Lib.getStadium(429));
        Assertions.assertEquals("Ramón de Carranza",Lib.getStadium(603));
        Assertions.assertEquals("Balaídos",Lib.getStadium(712));
        Assertions.assertEquals("Ipurúa",Lib.getStadium(957));
        Assertions.assertEquals("Manuel Martínez Valero",Lib.getStadium(975));
        Assertions.assertEquals("Coliseum Alfonso Pérez",Lib.getStadium(1217));
        Assertions.assertEquals("Nuevo Los Cármenes",Lib.getStadium(4235));
        Assertions.assertEquals("El Alcoraz",Lib.getStadium(1339));
        Assertions.assertEquals("Ciutat de Valencia",Lib.getStadium(1547));
        Assertions.assertEquals("El Sadar",Lib.getStadium(1887));
        Assertions.assertEquals("Reale Arena",Lib.getStadium(2120));
        Assertions.assertEquals("Benito Villamarín",Lib.getStadium(486));
        Assertions.assertEquals("Santiago Bernabéu",Lib.getStadium(2107));
        Assertions.assertEquals("José Zorrilla",Lib.getStadium(2654));
        Assertions.assertEquals("Sánchez Pizjuán",Lib.getStadium(1102));
        Assertions.assertEquals("Mestalla",Lib.getStadium(2647));
        Assertions.assertEquals("La Cerámica",Lib.getStadium(2716));
        Assertions.assertEquals("Carlos Belmonte",Lib.getStadium(140));
        Assertions.assertEquals("Santo Domingo",Lib.getStadium(64));
        Assertions.assertEquals("Juegos Mediterráneos",Lib.getStadium(183));
        Assertions.assertEquals("Nuevo Castalia",Lib.getStadium(673));
        Assertions.assertEquals("RCDE Stadium",Lib.getStadium(998));
        Assertions.assertEquals("Municipal Cartagonova",Lib.getStadium(643));
        Assertions.assertEquals("Fernando Torres",Lib.getStadium(1179));
        Assertions.assertEquals("Montilivi",Lib.getStadium(1236));
        Assertions.assertEquals("Gran Canaria",Lib.getStadium(2563));
        Assertions.assertEquals("Butarque",Lib.getStadium(1535));
        Assertions.assertEquals("Ángel Carro",Lib.getStadium(1598));
        Assertions.assertEquals("La Rosaleda",Lib.getStadium(1617));
        Assertions.assertEquals("Son Moix",Lib.getStadium(1623));
        Assertions.assertEquals("Anduva",Lib.getStadium(1699));
        Assertions.assertEquals("El Toralín",Lib.getStadium(3287));
        Assertions.assertEquals("Vallecas",Lib.getStadium(2080));
        Assertions.assertEquals("NMR Carlos Tartiere",Lib.getStadium(2115));
        Assertions.assertEquals("La Romareda",Lib.getStadium(2136));
        Assertions.assertEquals("El Molinón",Lib.getStadium(2125));
        Assertions.assertEquals("Heliodoro Rodríguez López",Lib.getStadium(2477));
        Assertions.assertEquals("Nova Creu Alta",Lib.getStadium(2198));
        Assertions.assertEquals("Las Gaunas",Lib.getStadium(1578));
    }

    @Test
    void changeFormatDate_Exists_Ok(){
        String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
        Assertions.assertTrue(Lib.changeFormatDate("17/08/2000").matches(datePattern));
    }
}
