package com.raulquesada.appfutbol.listeners.api;

import com.google.common.collect.EvictingQueue;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Jornada;

/**
 * The interface Get partidos para un partido listener.
 */
public interface IGetPartidosParaUnPartidoListener {
    /**
     * On get partidos para un partido.
     *
     * @param jornada the jornada
     * @param equipo  the equipo
     */
    void OnGetPartidosParaUnPartido(Jornada jornada, Equipo equipo);
}
