package com.zuhriyansauqi.rajaongkirsdk.responses.city;

import com.zuhriyansauqi.rajaongkirsdk.objects.CityObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class CitiesResponse extends GeneralResponse {

    private List<CityObject> cities;

    public CitiesResponse(int statusCode, String statusDescription, List<CityObject> cities) {
        super(statusCode, statusDescription);
        this.cities = cities;
    }

    public List<CityObject> getCities() {
        return cities;
    }

    public void setCities(List<CityObject> cities) {
        this.cities = cities;
    }
}
