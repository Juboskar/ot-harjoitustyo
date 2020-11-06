package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }

    @Test
    public void lataaminenToimii() {
        kortti.lataaRahaa(10);
        assertTrue(kortti.saldo() == 20);
    }

    @Test
    public void otaRahaaToimii() {
        assertTrue(kortti.otaRahaa(10));
        assertFalse(kortti.otaRahaa(11));
    }
    
    @Test
    public void toStringToimii() {
        assertEquals(kortti.toString(), "saldo: 0.10");
    }
}
