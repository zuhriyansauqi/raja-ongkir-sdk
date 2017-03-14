package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/14/17.
 */

public class CostObject {

    private double value;
    private String etd;
    private String note;

    public CostObject(double value, String etd, String note) {
        this.value = value;
        this.etd = etd;
        this.note = note;
    }

    public double getValue() {
        return value;
    }

    public String getEtd() {
        return etd;
    }

    public String getNote() {
        return note;
    }
}
