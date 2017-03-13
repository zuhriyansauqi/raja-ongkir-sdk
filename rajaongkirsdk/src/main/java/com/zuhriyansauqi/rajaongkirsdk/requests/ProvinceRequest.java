package com.zuhriyansauqi.rajaongkirsdk.requests;

import android.support.annotation.Nullable;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public class ProvinceRequest implements RORequest {

    public static final String ID = "id";

    private String id;

    public ProvinceRequest(@Nullable String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}