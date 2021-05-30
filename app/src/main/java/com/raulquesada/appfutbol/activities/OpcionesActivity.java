package com.raulquesada.appfutbol.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.raulquesada.appfutbol.fragments.config.ConfigFragment;

/**
 * Activity que alberga el fragment de configuración de la prefernece screen
 */
public class OpcionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new ConfigFragment()).commit();
    }
}