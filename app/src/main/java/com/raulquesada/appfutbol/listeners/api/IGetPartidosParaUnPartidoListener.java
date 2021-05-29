package com.raulquesada.appfutbol.listeners.api;

import com.google.common.collect.EvictingQueue;
import com.raulquesada.appfutbol.models.Equipo;
import com.raulquesada.appfutbol.models.Jornada;

public interface IGetPartidosParaUnPartidoListener {
    void OnGetPartidosParaUnPartido(Jornada jornada, Equipo equipo);
}
