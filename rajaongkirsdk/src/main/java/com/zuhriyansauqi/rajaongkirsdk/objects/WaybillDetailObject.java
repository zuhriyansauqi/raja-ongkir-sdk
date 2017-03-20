package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/21/17.
 */

public class WaybillDetailObject {

    private String number;
    private String date;
    private String time;
    private String weight;
    private String origin;
    private String destination;
    private String shipperName;
    private String shipperAddress1;
    private String shipperAddress2;
    private String shipperAddress3;
    private String shipperCity;
    private String receiverName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverAddress3;
    private String receiverCity;

    public WaybillDetailObject(String number, String date, String time, String weight, String origin, String destination, String shipperName, String shipperAddress1, String shipperAddress2, String shipperAddress3, String shipperCity, String receiverName, String receiverAddress1, String receiverAddress2, String receiverAddress3, String receiverCity) {
        this.number = number;
        this.date = date;
        this.time = time;
        this.weight = weight;
        this.origin = origin;
        this.destination = destination;
        this.shipperName = shipperName;
        this.shipperAddress1 = shipperAddress1;
        this.shipperAddress2 = shipperAddress2;
        this.shipperAddress3 = shipperAddress3;
        this.shipperCity = shipperCity;
        this.receiverName = receiverName;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.receiverAddress3 = receiverAddress3;
        this.receiverCity = receiverCity;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getWeight() {
        return weight;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getShipperAddress1() {
        return shipperAddress1;
    }

    public String getShipperAddress2() {
        return shipperAddress2;
    }

    public String getShipperAddress3() {
        return shipperAddress3;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    public String getReceiverAddress2() {
        return receiverAddress2;
    }

    public String getReceiverAddress3() {
        return receiverAddress3;
    }

    public String getReceiverCity() {
        return receiverCity;
    }
}
