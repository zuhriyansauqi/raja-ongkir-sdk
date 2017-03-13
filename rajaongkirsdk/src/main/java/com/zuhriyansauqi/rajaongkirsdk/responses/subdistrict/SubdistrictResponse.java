package com.zuhriyansauqi.rajaongkirsdk.responses.subdistrict;

import com.zuhriyansauqi.rajaongkirsdk.objects.SubdistrictObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public class SubdistrictResponse extends GeneralResponse {

    private SubdistrictObject subdistrict;

    public SubdistrictResponse(int statusCode, String statusDescription, SubdistrictObject subdistrict) {
        super(statusCode, statusDescription);
        this.subdistrict = subdistrict;
    }

    public SubdistrictObject getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(SubdistrictObject subdistrict) {
        this.subdistrict = subdistrict;
    }
}
