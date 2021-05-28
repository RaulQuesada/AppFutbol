package com.raulquesada.appfutbol.rest;

import com.raulquesada.appfutbol.api.IAPIService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    @Test
    void getInstance_Exists_Ok(){
        IAPIService iapiService = RestClient.getInstance();
        RestClient.reset();
        assertNotNull(iapiService);
    }
}