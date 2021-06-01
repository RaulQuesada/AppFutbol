package com.raulquesada.appfutbol.listeners.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;
import com.raulquesada.appfutbol.fragments.favorito.FragmentFavorito;
import com.raulquesada.appfutbol.models.Equipo;

import java.util.List;

/**
 * The interface Get equipos favoritos listener.
 */
public interface IGetEquiposFavoritosListener {
    /**
     * On get equipos favoritos.
     *
     * @param task the task
     */
    void onGetEquiposFavoritos(Task<QuerySnapshot> task);
}
