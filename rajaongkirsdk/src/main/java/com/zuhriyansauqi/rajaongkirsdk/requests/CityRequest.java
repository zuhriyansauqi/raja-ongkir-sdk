package com.zuhriyansauqi.rajaongkirsdk.requests;

import android.support.annotation.Nullable;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class CityRequest implements RORequest {

    public static final String ID = "id";
    public static final String PROVINCE = "province";

    private String id;
    private String province;

    public CityRequest(@Nullable String id, @Nullable String province) {
        this.id = id;
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }
}
