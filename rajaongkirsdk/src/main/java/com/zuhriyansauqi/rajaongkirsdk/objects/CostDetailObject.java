package com.zuhriyansauqi.rajaongkirsdk.objects;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/14/17.
 */

public class CostDetailObject {

    private String service;
    private String description;
    private List<CostObject> cost;

    public CostDetailObject(String service, String description, List<CostObject> cost) {
        this.service = service;
        this.description = description;
        this.cost = cost;
    }

    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }

    public List<CostObject> getCost() {
        return cost;
    }
}
