package com.example.paastopaivakirja.domain;

/**
 * House type
 * 
 * @author Oskari
 */
public enum House {
    APARTMENT,
    ROWHOUSE,
    HOUSE;

    /**
     * return emissions depending on house type
     * 
     * @return emission co2 g eq
     */
    public int getEmission() {
        switch (this) {
            case HOUSE:
                return 6900;
            case ROWHOUSE:
                return 6900;
            default: //APARTMENT
                return 8000;
        }
    }
}
