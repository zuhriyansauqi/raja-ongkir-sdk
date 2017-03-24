package com.zuhriyansauqi.rajaongkirsdk.responses.costresult;

import com.zuhriyansauqi.rajaongkirsdk.objects.CostResultObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/14/17.
 */

public class CostResultResponse extends GeneralResponse {

    private List<CostResultObject> costResults;

    public CostResultResponse(int statusCode, String statusDescription, List<CostResultObject> costResults) {
        super(statusCode, statusDescription);
        this.costResults = costResults;
    }

    public List<CostResultObject> getCostResults() {
        return costResults;
    }
}
