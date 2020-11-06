package com.mycompany.unicafe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUpPaate() {
        paate = new Kassapaate();
    }

    @Before
    public void setUpKortti() {
        kortti = new Maksukortti(400);
    }

    @Test
    public void luotuPaateOlemassa() {
        assertTrue(paate != null);
    }

    @Test
    public void paateAlussaOikein() {
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void kateisostoEdullisestiToimii() {
        assertTrue(paate.syoEdullisesti(100) == 100);
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);

        assertTrue(paate.syoEdullisesti(500) == 260);
        assertTrue(paate.kassassaRahaa() == 100240);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void kateisostoMaukkaastiToimii() {
        assertTrue(paate.syoMaukkaasti(100) == 100);
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);

        assertTrue(paate.syoMaukkaasti(500) == 100);
        assertTrue(paate.kassassaRahaa() == 100400);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void korttiostoEdullisestiToimii() {
        assertTrue(paate.syoEdullisesti(kortti));
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);

        assertFalse(paate.syoEdullisesti(kortti));
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
        
        assertTrue(kortti.saldo()==160);
        assertTrue(paate.kassassaRahaa() == 100000);
    }

    @Test
    public void korttiostoMaukkaastiToimii() {
        assertTrue(paate.syoMaukkaasti(kortti));
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);

        assertFalse(paate.syoMaukkaasti(kortti));
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
        
        assertTrue(kortti.saldo()==0);
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kortinLataaminenToimii(){
        paate.lataaRahaaKortille(kortti, -1000);
        assertTrue(kortti.saldo()==400);
        assertTrue(paate.kassassaRahaa() == 100000);
        
        paate.lataaRahaaKortille(kortti, 1000);
        assertTrue(kortti.saldo()==1400);
        assertTrue(paate.kassassaRahaa() == 101000);
    }
    
}
