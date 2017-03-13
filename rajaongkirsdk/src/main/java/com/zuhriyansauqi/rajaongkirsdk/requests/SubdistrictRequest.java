package com.zuhriyansauqi.rajaongkirsdk.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public class SubdistrictRequest implements RORequest {

    public static final String CITY = "city";
    public static final String ID = "id";

    private String city;
    private String id;

    public SubdistrictRequest(@NonNull String city, @Nullable String id) {
        this.city = city;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }
}
