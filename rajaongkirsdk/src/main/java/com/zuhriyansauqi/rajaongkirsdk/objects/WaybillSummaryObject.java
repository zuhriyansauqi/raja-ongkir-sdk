package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/21/17.
 */

public class WaybillSummaryObject {

    private String courierCode;
    private String courierName;
    private String waybillNumber;
    private String serviceCode;
    private String waybillDate;
    private String shipperName;
    private String receiverName;
    private String origin;
    private String destination;
    private String status;

    public WaybillSummaryObject(String courierCode, String courierName, String waybillNumber, String serviceCode, String waybillDate, String shipperName, String receiverName, String origin, String destination, String status) {
        this.courierCode = courierCode;
        this.courierName = courierName;
        this.waybillNumber = waybillNumber;
        this.serviceCode = serviceCode;
        this.waybillDate = waybillDate;
        this.shipperName = shipperName;
        this.receiverName = receiverName;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public String getCourierCode() {
        return courierCode;
    }

    public String getCourierName() {
        return courierName;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getWaybillDate() {
        return waybillDate;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }
}
