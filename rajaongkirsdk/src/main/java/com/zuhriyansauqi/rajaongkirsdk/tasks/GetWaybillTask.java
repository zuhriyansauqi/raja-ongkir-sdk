package com.zuhriyansauqi.rajaongkirsdk.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.enums.ResponseTypes;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillManifestObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillDeliveryStatusObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillDetailObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillSummaryObject;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.WaybillRequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.waybill.WaybillResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zuhriyansauqi on 3/21/17.
 */

public class GetWaybillTask extends ROTaskBase {

    public GetWaybillTask(RajaOngkir rajaOngkir, RORequest request, ROTaskListener listener) {
        super(rajaOngkir, request, listener);
        this.url = rajaOngkir.getBaseUrl() + "/waybill";
    }

    @Override
    public void execute() throws RONullRequestException, ROInvalidRequestException {
        if (request == null) throw new RONullRequestException("Raja Ongkir request is null!");

        if (!(request instanceof WaybillRequest))
            throw new ROInvalidRequestException("Raja Ongkir request is invalid!");

        final WaybillRequest request = (WaybillRequest) this.request;

        new AsyncTask<Object, Object, Void>() {

            @Override
            protected Void doInBackground(Object... params) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    FormBody.Builder builder = new FormBody.Builder()
                            .add(WaybillRequest.WAYBILL, request.getWaybill())
                            .add(WaybillRequest.COURIER, request.getCourier().toString());

                    RequestBody body = builder.build();

                    Request req = new Request.Builder()
                            .url(url)
                            .post(body)
                            .addHeader(KEY, rajaOngkir.getApiKey())
                            .addHeader(CONTENT_TYPE, FORM_ENCODED)
                            .build();

                    Response res = client.newCall(req).execute();

                    try {
                        generateResult(new JSONObject(res.body().string()));

                    } catch (JSONException e) {
                        GetWaybillTask.this.responseType = ResponseTypes.PARSE_ERROR;
                        if (listener != null) {
                            ((Activity) rajaOngkir.getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onError(GetWaybillTask.this);
                                }
                            });
                        }
                    }

                    if (listener != null) {
                        ((Activity) rajaOngkir.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.didExecuted(GetWaybillTask.this);
                            }
                        });
                    }

                } catch (IOException e) {
                    GetWaybillTask.this.responseType = ResponseTypes.INTERNET_ERROR;
                    if (listener != null) {
                        ((Activity) rajaOngkir.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(GetWaybillTask.this);
                            }
                        });
                    }
                }
                return null;
            }
        }.execute();
    }

    @Override
    protected void generateResult(JSONObject json) throws JSONException {
        JSONObject root = json.getJSONObject(JSON_RAJA_ONGKIR);
        JSONObject status = root.getJSONObject(JSON_STATUS);

        int code = status.getInt(JSON_STATUS_CODE);
        String description = status.getString(JSON_STATUS_DESCRIPTION);

        response = new GeneralResponse(code, description);

        if (code == 200) {
            this.responseType = ResponseTypes.SUCCESS;
        } else if (code == 400) {
            this.responseType = ResponseTypes.INVALID_API;
            return;
        } else {
            this.responseType = ResponseTypes.UNKNOWN_ERROR;
            return;
        }

        final WaybillRequest request = (WaybillRequest) this.request;

        JSONObject waybillResult = root.getJSONObject(JSON_RESULT);

        boolean delivered = waybillResult.optBoolean(JSON_WAYBILL_DELIVERED);

        JSONObject waybillSummary = waybillResult.optJSONObject(JSON_WAYBILL_SUMMARY);
        JSONObject waybillDetail = waybillResult.optJSONObject(JSON_WAYBILL_DETAILS);
        JSONObject waybillDeliveryStatus = waybillResult.optJSONObject(JSON_WAYBILL_DELIVERY_STATUS);
        JSONArray waybillManifests = waybillResult.optJSONArray(JSON_WAYBILL_MANIFEST);

        WaybillSummaryObject summary = new WaybillSummaryObject(
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_COURIER_CODE),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_COURIER_NAME),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_WAYBILL_NUMBER),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_SERVICE_CODE),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_WAYBILL_DATE),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_SHIPPER_NAME),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_RECEIVER_NAME),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_ORIGIN),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_DESTINATION),
                waybillSummary.optString(JSON_WAYBILL_SUMMARY_STATUS)
        );

        WaybillDetailObject detail = new WaybillDetailObject(
                waybillDetail.optString(JSON_WAYBILL_DETAILS_WAYBILL_NUMBER),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_WAYBILL_DATE),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_WAYBILL_TIME),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_WEIGHT),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_ORIGIN),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_DESTINATION),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_SHIPPER_NAME),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_SHIPPER_ADDRESS1),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_SHIPPER_ADDRESS2),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_SHIPPER_ADDRESS3),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_SHIPPER_CITY),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_RECEIVER_NAME),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_RECEIVER_ADDRESS1),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_RECEIVER_ADDRESS2),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_RECEIVER_ADDRESS3),
                waybillDetail.optString(JSON_WAYBILL_DETAILS_RECEIVER_CITY)
        );

        WaybillDeliveryStatusObject deliveryStatus = new WaybillDeliveryStatusObject(
                waybillDeliveryStatus.optString(JSON_WAYBILL_DELIVERY_STATUS_STATUS),
                waybillDeliveryStatus.optString(JSON_WAYBILL_DELIVERY_STATUS_POD_RECEIVER),
                waybillDeliveryStatus.optString(JSON_WAYBILL_DELIVERY_STATUS_POD_DATE),
                waybillDeliveryStatus.optString(JSON_WAYBILL_DELIVERY_STATUS_POD_TIME)
        );

        List<WaybillManifestObject> manifests = new ArrayList<>();

        for (int i = 0; i < waybillManifests.length(); i++) {
            JSONObject waybillManifest = waybillManifests.getJSONObject(i);
            WaybillManifestObject manifest = new WaybillManifestObject(
                    waybillManifest.optString(JSON_WAYBILL_MANIFEST_MANIFEST_CODE),
                    waybillManifest.optString(JSON_WAYBILL_MANIFEST_MANIFEST_DESCRIPTION),
                    waybillManifest.optString(JSON_WAYBILL_MANIFEST_MANIFEST_DATE),
                    waybillManifest.optString(JSON_WAYBILL_MANIFEST_MANIFEST_TIME),
                    waybillManifest.optString(JSON_WAYBILL_MANIFEST_CITY_NAME)
            );

            manifests.add(manifest);
        }

        response = new WaybillResponse(code, description, delivered, summary, detail, deliveryStatus, manifests);
    }
}