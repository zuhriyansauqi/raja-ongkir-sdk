package com.zuhriyansauqi.rajaongkirsdk.enums;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public enum PlaceTypes {

    CITY ("city"),
    SUBDISTRICT ("subdistrict");

    private String value;

    private PlaceTypes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
