package com.zuhriyansauqi.rajaongkirsdk.responses.province;

import com.zuhriyansauqi.rajaongkirsdk.objects.ProvinceObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class ProvinceResponse extends GeneralResponse {

    private ProvinceObject province;

    public ProvinceResponse(int statusCode, String statusDescription, ProvinceObject province) {
        super(statusCode, statusDescription);
        this.province = province;
    }

    public ProvinceObject getProvince() {
        return province;
    }

    public void setProvince(ProvinceObject province) {
        this.province = province;
    }
}
