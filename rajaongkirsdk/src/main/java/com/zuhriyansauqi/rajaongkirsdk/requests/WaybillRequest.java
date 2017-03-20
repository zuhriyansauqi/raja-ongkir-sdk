package com.zuhriyansauqi.rajaongkirsdk.requests;

import android.support.annotation.NonNull;

import com.zuhriyansauqi.rajaongkirsdk.enums.Couriers;

/**
 * Created by zuhriyansauqi on 3/20/17.
 */

public class WaybillRequest implements RORequest {

    public static final String WAYBILL = "waybill";
    public static final String COURIER = "courier";

    private String waybill;
    private Couriers courier;

    public WaybillRequest(@NonNull String waybill, @NonNull Couriers courier) {
        this.waybill = waybill;
        this.courier = courier;
    }

    public String getWaybill() {
        return waybill;
    }

    public Couriers getCourier() {
        return courier;
    }
}
