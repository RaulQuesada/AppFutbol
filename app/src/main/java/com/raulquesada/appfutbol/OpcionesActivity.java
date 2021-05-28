package com.raulquesada.appfutbol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.raulquesada.appfutbol.fragments.ConfigFragment;

/**
 * Activity que alberga el fragment de configuración de la prefernece screen
 */
public class OpcionesActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new ConfigFragment()).commit();
    }
}