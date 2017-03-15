package com.zuhriyansauqi.rajaongkirsdk.tasks;

import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.RajaOngkirBase;
import com.zuhriyansauqi.rajaongkirsdk.enums.ResponseTypes;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zuhriyansauqi on 3/15/17.
 */

public class ROTaskBase implements ROTask, RajaOngkirBase {

    protected RajaOngkir rajaOngkir;
    protected RORequest request;
    protected ROResponse response;
    protected ResponseTypes responseType;
    protected ROTaskListener listener;
    protected String url;

    public ROTaskBase(RajaOngkir rajaOngkir, RORequest request, ROTaskListener listener) {
        this.rajaOngkir = rajaOngkir;
        this.request = request;
        this.listener = listener;
    }

    @Override
    public ResponseTypes getResponseType() {
        return responseType;
    }

    @Override
    public ROResponse getResponse() {
        return response;
    }

    @Override
    public void execute() throws RONullRequestException, ROInvalidRequestException {

    }

    protected void generateResult(JSONObject json) throws JSONException {

    }
}
