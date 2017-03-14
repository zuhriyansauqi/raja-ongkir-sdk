package com.zuhriyansauqi.rajaongkirsdk.objects;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/14/17.
 */

public class CostResultObject {

    private String code;
    private String name;
    private List<CostDetailObject> details;

    public CostResultObject(String code, String name, List<CostDetailObject> details) {
        this.code = code;
        this.name = name;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<CostDetailObject> getDetails() {
        return details;
    }
}
