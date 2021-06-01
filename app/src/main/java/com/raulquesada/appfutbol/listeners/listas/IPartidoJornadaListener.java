package com.raulquesada.appfutbol.listeners.listas;

import com.raulquesada.appfutbol.models.Partido;

/**
 * The interface Partido jornada listener.
 */
public interface IPartidoJornadaListener {
    /**
     * On partido seleccionado.
     *
     * @param partido the partido
     */
    void onPartidoSeleccionado(Partido partido);
}
