package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public class SubdistrictObject {

    private String id;
    private CityObject city;
    private String name;

    public SubdistrictObject(String id, CityObject city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CityObject getCity() {
        return city;
    }

    public void setCity(CityObject city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kecamatan " + name;
    }
}
