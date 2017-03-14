package com.zuhriyansauqi.rajaongkirsdk.responses.costresult;

import com.zuhriyansauqi.rajaongkirsdk.objects.CostResultObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

/**
 * Created by zuhriyansauqi on 3/14/17.
 */

public class CostResultResponse extends GeneralResponse {

    private CostResultObject costResult;

    public CostResultResponse(int statusCode, String statusDescription, CostResultObject costResult) {
        super(statusCode, statusDescription);
        this.costResult = costResult;
    }

    public CostResultObject getCostResult() {
        return costResult;
    }
}
