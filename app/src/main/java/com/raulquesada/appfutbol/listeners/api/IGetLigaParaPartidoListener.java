package com.raulquesada.appfutbol.listeners.api;

import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;

public interface IGetLigaParaPartidoListener {
    void OnGetLigaParaPartido(InfoLiga infoLiga, Equipo equipo);
}
