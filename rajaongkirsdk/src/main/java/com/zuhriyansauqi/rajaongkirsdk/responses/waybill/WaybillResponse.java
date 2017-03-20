package com.zuhriyansauqi.rajaongkirsdk.responses.waybill;

import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillDeliveryStatusObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillManifestObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillDetailObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.WaybillSummaryObject;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;

import java.util.List;

/**
 * Created by zuhriyansauqi on 3/21/17.
 */

public class WaybillResponse extends GeneralResponse {

    private boolean delivered;
    private WaybillSummaryObject summary;
    private WaybillDetailObject detail;
    private WaybillDeliveryStatusObject deliveryStatus;
    private List<WaybillManifestObject> manifests;

    public WaybillResponse(int statusCode, String statusDescription, boolean delivered, WaybillSummaryObject summary, WaybillDetailObject detail, WaybillDeliveryStatusObject deliveryStatus, List<WaybillManifestObject> manifests) {
        super(statusCode, statusDescription);
        this.delivered = delivered;
        this.summary = summary;
        this.detail = detail;
        this.deliveryStatus = deliveryStatus;
        this.manifests = manifests;
    }

    public boolean getDelivered() {
        return delivered;
    }

    public WaybillSummaryObject getSummary() {
        return summary;
    }

    public WaybillDetailObject getDetail() {
        return detail;
    }

    public List<WaybillManifestObject> getManifests() {
        return manifests;
    }
}
