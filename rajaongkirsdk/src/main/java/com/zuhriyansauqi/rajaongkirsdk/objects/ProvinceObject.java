package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class ProvinceObject {

    private String id;
    private String province;

    public ProvinceObject(String id, String province) {
        this.id = id;
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return province;
    }
}
