package com.zuhriyansauqi.rajaongkirsdk.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zuhriyansauqi.rajaongkirsdk.enums.Couriers;
import com.zuhriyansauqi.rajaongkirsdk.enums.PlaceTypes;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/13/17.
 */

public class CostRequest implements RORequest {

    public static final String ORIGIN = "origin";
    public static final String ORIGIN_TYPE = "originType";
    public static final String DESTINATION = "destination";
    public static final String DESTINATION_TYPE = "destinationType";
    public static final String WEIGHT = "weight";
    public static final String COURIER = "courier";
    public static final String LENGTH = "length";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String DIAMETER = "diameter";

    private String origin;
    private PlaceTypes originType;
    private String destination;
    private PlaceTypes destionationType;
    private Integer weight;
    private List<Couriers> couriers;
    private Double length;
    private Double width;
    private Double height;
    private Double diameter;

    public CostRequest(@NonNull String origin, @NonNull PlaceTypes originType,
                       @NonNull String destination, @NonNull PlaceTypes destionationType,
                       @NonNull Integer weight, @NonNull List<Couriers> couriers,
                       @Nullable Double length, @Nullable Double width,
                       @Nullable Double height, @Nullable Double diameter) {
        this.origin = origin;
        this.originType = originType;
        this.destination = destination;
        this.destionationType = destionationType;
        this.weight = weight;
        this.couriers = couriers;
        this.length = length;
        this.width = width;
        this.height = height;
        this.diameter = diameter;
    }

    public String getOrigin() {
        return origin;
    }

    public PlaceTypes getOriginType() {
        return originType;
    }

    public String getDestination() {
        return destination;
    }

    public PlaceTypes getDestionationType() {
        return destionationType;
    }

    public Integer getWeight() {
        return weight;
    }

    public List<Couriers> getCouriers() {
        return couriers;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getDiameter() {
        return diameter;
    }
}
