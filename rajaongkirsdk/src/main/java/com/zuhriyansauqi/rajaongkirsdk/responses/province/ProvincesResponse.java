package com.zuhriyansauqi.rajaongkirsdk.responses.province;

import com.zuhriyansauqi.rajaongkirsdk.objects.ProvinceObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public class ProvincesResponse extends GeneralResponse {

    private List<ProvinceObject> provinces;

    public ProvincesResponse(int statusCode, String statusDescription, List<ProvinceObject> provinces) {
        super(statusCode, statusDescription);
        this.provinces = provinces;
    }

    public List<ProvinceObject> getProvinces() {
        return provinces;
    }
}
