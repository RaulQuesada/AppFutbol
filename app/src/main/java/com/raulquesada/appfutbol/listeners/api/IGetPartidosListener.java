package com.raulquesada.appfutbol.listeners.api;

import com.raulquesada.appfutbol.models.Jornada;

/**
 * The interface Get partidos listener.
 */
public interface IGetPartidosListener {
    /**
     * On get partidos.
     *
     * @param jornada the jornada
     */
    void OnGetPartidos(Jornada jornada);
}
