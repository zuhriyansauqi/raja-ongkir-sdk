package com.zuhriyansauqi.rajaongkirsdk.responses;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class GeneralResponse implements ROResponse {

    private int statusCode;
    private String statusDescription;

    public GeneralResponse(int statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
