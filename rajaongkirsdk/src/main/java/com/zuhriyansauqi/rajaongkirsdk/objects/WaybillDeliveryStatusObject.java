package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/21/17.
 */

public class WaybillDeliveryStatusObject {

    private String status;
    private String podReceiver;
    private String podDate;
    private String podTime;

    public WaybillDeliveryStatusObject(String status, String podReceiver, String podDate, String podTime) {
        this.status = status;
        this.podReceiver = podReceiver;
        this.podDate = podDate;
        this.podTime = podTime;
    }

    public String getStatus() {
        return status;
    }

    public String getPodReceiver() {
        return podReceiver;
    }

    public String getPodDate() {
        return podDate;
    }

    public String getPodTime() {
        return podTime;
    }
}
