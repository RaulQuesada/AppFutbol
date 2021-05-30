package com.raulquesada.appfutbol.listeners.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;
import com.raulquesada.appfutbol.fragments.favorito.FragmentFavorito;
import com.raulquesada.appfutbol.models.Equipo;

import java.util.List;

public interface IGetEquiposFavoritosListener {
    void onGetEquiposFavoritos(Task<QuerySnapshot> task);
}
