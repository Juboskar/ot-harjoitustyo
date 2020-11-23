package com.example.paastopaivakirja;

import com.example.paastopaivakirja.domain.House;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Oskari
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseTest {

    @Test
    public void testHouseEmissions() {
        House apartment = House.APARTMENT;
        House house = House.HOUSE;
        House rowhouse = House.ROWHOUSE;
        assertEquals(apartment.getEmission(), 8000);
        assertEquals(house.getEmission(), 6900);
        assertEquals(rowhouse.getEmission(), 6900);
    }
}
