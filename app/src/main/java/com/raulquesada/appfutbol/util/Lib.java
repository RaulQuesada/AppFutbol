package com.raulquesada.appfutbol.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lib {
    public static String getStadium(int idlocalTeam){
        switch (idlocalTeam){
            case 137:
                return "Mendizorroza";
            case 347:
                return "San Mamés";
            case 369:
                return "Wanda Metropolitano";
            case 429:
                return "Camp Nou";
            case 603:
                return "Ramón de Carranza";
            case 712:
                return "Balaídos";
            case 957:
                return "Ipurúa";
            case 975:
                return "Manuel Martínez Valero";
            case 1217:
                return "Coliseum Alfonso Pérez";
            case 4235:
                return "Nuevo Los Cármenes";
            case 1339:
                return "El Alcoraz";
            case 1547:
                return "Ciutat de Valencia";
            case 1887:
                return "El Sadar";
            case 2120:
                return "Reale Arena";
            case 486:
                return "Benito Villamarín";
            case 2107:
                return "Santiago Bernabéu";
            case 2654:
                return "José Zorrilla";
            case 1102:
                return "Sánchez Pizjuán";
            case 2647:
                return "Mestalla";
            case 2716:
                return "La Cerámica";
            case 140:
                return "Carlos Belmonte";
            case 64:
                return "Santo Domingo";
            case 183:
                return "Juegos Mediterráneos";
            case 673:
                return "Nuevo Castalia";
            case 998:
                return "RCDE Stadium";
            case 643:
                return "Municipal Cartagonova";
            case 1179:
                return "Fernando Torres";
            case 1236:
                return "Montilivi";
            case 2563:
                return "Gran Canaria";
            case 1535:
                return "Butarque";
            case 1598:
                return "Ángel Carro";
            case 1617:
                return "La Rosaleda";
            case 1623:
                return "Son Moix";
            case 1699:
                return "Anduva";
            case 3287:
                return "El Toralín";
            case 2080:
                return "Vallecas";
            case 2115:
                return "NMR Carlos Tartiere";
            case 2125:
                return "El Molinón";
            case 2136:
                return "La Romareda";
            case 2198:
                return "Nova Creu Alta";
            case 2477:
                return "Heliodoro Rodríguez López";
            case 1578:
                return "Las Gaunas";
        }
        return "";
    }

    public static String changeFormatDate(String date) {
        try {
            Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(date1);
        }catch (ParseException pe){
            pe.getStackTrace();
        }
        return date;
    }
    public static boolean compareDates(String dateMatch){
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date d1 = sdformat.parse(dateMatch);
            //sdformat.format(new Date())
            Date d2 = sdformat.parse("2021/05/22");
            if(d1.compareTo(d2) == 0) {
                return true;
            }
        }catch (ParseException pe){
            pe.getStackTrace();
        }
        return false;
    }
}
