package com.zuhriyansauqi.rajaongkirsdk.responses.city;

import com.zuhriyansauqi.rajaongkirsdk.objects.CityObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class CityResponse extends GeneralResponse {

    private CityObject city;

    public CityResponse(int statusCode, String statusDescription, CityObject city) {
        super(statusCode, statusDescription);
        this.city = city;
    }

    public CityObject getCity() {
        return city;
    }
}
