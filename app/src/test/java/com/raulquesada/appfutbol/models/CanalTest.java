package com.raulquesada.appfutbol.models;

import org.junit.Assert;
import org.junit.Test;

class CanalTest {

    @Test
    void testCanal(){
        final String SET_ID = "2";
        final String SET_NAME = "SETNAME";
        final String SET_URL = "SETURL";

        Canal canalTest = new Canal("1","canal","urlImage");
        canalTest.setId(SET_ID);
        canalTest.setName(SET_NAME);
        canalTest.setUrlImage(SET_URL);

        Assert.assertEquals(SET_ID, canalTest.getId());
        Assert.assertEquals(SET_NAME, canalTest.getName());
        Assert.assertEquals(SET_URL, canalTest.getUrlImage());
    }
}
