package com.raulquesada.appfutbol.listeners.api;

import com.raulquesada.appfutbol.models.Tabla;

import java.util.List;

/**
 * Interfaz listener para cuando se obtienen los equipos de la clasificación
 */
public interface IGetEquiposEnClasificacionListener {
    /**
     * On get equipos en clasificacion.
     *
     * @param equipoEnClasificacions the equipo en clasificacions
     */
    void OnGetEquiposEnClasificacion(Tabla equipoEnClasificacions);
}
