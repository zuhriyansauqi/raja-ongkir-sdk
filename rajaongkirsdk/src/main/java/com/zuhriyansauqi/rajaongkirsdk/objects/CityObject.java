package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class CityObject {

    private String id;
    private ProvinceObject province;
    private String type;
    private String name;
    private String postalCode;

    public CityObject(String id, ProvinceObject province, String type, String name) {
        this.id = id;
        this.province = province;
        this.type = type;
        this.name = name;
    }

    public CityObject(String id, ProvinceObject province, String type, String name, String postalCode) {
        this.id = id;
        this.province = province;
        this.type = type;
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProvinceObject getProvince() {
        return province;
    }

    public void setProvince(ProvinceObject province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return name;
    }
}
