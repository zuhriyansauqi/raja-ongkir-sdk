package com.zuhriyansauqi.rajaongkirsdk.responses.subdistrict;

import com.zuhriyansauqi.rajaongkirsdk.objects.SubdistrictObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public class SubdistrictsResponse extends GeneralResponse {

    private List<SubdistrictObject> subdistricts;

    public SubdistrictsResponse(int statusCode, String statusDescription, List<SubdistrictObject> subdistricts) {
        super(statusCode, statusDescription);
        this.subdistricts = subdistricts;
    }

    public List<SubdistrictObject> getSubdistricts() {
        return subdistricts;
    }

    public void setSubdistricts(List<SubdistrictObject> subdistricts) {
        this.subdistricts = subdistricts;
    }
}
