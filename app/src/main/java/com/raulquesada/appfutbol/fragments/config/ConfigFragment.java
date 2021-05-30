package com.raulquesada.appfutbol.fragments.config;

import android.os.Bundle;

import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;

import com.raulquesada.appfutbol.R;

public class ConfigFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencescreen, rootKey);
    }
}
