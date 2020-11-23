package com.example.paastopaivakirja.domain;

/**
 *
 * @author Oskari
 */
public enum House {
    APARTMENT,
    ROWHOUSE,
    HOUSE;

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
