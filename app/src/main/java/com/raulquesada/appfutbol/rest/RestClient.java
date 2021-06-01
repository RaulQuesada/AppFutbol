package com.raulquesada.appfutbol.rest;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.raulquesada.appfutbol.api.IAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static IAPIService instance;
    private static String baseUrl;

    /** Lo hacemos privado para evitar que se puedan crear instancias de esta forma */
    private RestClient() {

    }

    /**
     * Metodo para obtener una instancia de la clase
     * @return instancia de la clase
     */
    public static synchronized IAPIService getInstance() {
        baseUrl = "https://apiclient.besoccerapps.com/scripts/";
        if(instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(IAPIService.class);
        }
        return instance;
    }

    /**
     * Pone la Instancia a null
     */
    public static void reset(){
        instance=null;
    }
}
