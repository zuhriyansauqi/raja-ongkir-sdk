package com.zuhriyansauqi.rajaongkirsdk.responses;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class GeneralResponse implements ROResponse {

    private Integer statusCode;
    private String statusDescription;

    public GeneralResponse(int statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
