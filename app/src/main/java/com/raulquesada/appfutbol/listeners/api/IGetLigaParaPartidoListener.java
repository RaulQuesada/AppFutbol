package com.raulquesada.appfutbol.listeners.api;

import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.InfoLiga;

/**
 * The interface Get liga para partido listener.
 */
public interface IGetLigaParaPartidoListener {
    /**
     * On get liga para partido.
     *
     * @param infoLiga the info liga
     * @param equipo   the equipo
     */
    void OnGetLigaParaPartido(InfoLiga infoLiga, Equipo equipo);
}
