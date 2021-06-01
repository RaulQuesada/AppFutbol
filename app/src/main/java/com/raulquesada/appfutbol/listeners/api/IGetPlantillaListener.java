package com.raulquesada.appfutbol.listeners.api;

import com.raulquesada.appfutbol.models.Jugador;
import com.raulquesada.appfutbol.models.Plantilla;

import java.util.List;

/**
 * The interface Get plantilla listener.
 */
public interface IGetPlantillaListener {
    /**
     * On get plantilla.
     *
     * @param plantilla the plantilla
     */
    void OnGetPlantilla(Plantilla plantilla);
}
