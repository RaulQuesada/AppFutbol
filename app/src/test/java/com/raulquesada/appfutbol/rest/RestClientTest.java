package com.raulquesada.appfutbol.rest;

import com.raulquesada.appfutbol.api.IAPIService;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Rest client test.
 */
public class RestClientTest {

    /**
     * Get instance exists ok.
     */
    @Test
    public void getInstance_Exists_Ok(){
        IAPIService iapiService = RestClient.getInstance();
        RestClient.reset();
        Assert.assertNotNull(iapiService);
    }
}