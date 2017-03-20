package com.zuhriyansauqi.rajaongkirsdk;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public interface RajaOngkirBase {

    // MARK: - Constants
    static final String TYPE_STARTER = "http://api.rajaongkir.com/starter";
    static final String TYPE_BASIC = "http://api.rajaongkir.com/basic";
    static final String TYPE_PRO = "http://pro.rajaongkir.com/api";

    static final String JSON_RAJA_ONGKIR = "rajaongkir";
    static final String JSON_STATUS = "status";
    static final String JSON_STATUS_CODE = "code";
    static final String JSON_STATUS_DESCRIPTION = "description";

    static final String JSON_RESULT = "result";
    static final String JSON_RESULTS = "results";

    static final String JSON_PROVINCE_ID = "province_id";
    static final String JSON_PROVINCE_NAME = "province";

    static final String JSON_CITY_ID = "city_id";
    static final String JSON_CITY_TYPE = "type";
    static final String JSON_CITY_NAME = "city_name";
    static final String JSON_CITY_NAME_2 = "city";
    static final String JSON_CITY_POSTAL_CODE = "postal_code";

    static final String JSON_SUBDISTRICT_ID = "subdistrict_id";
    static final String JSON_SUBDISTRICT_NAME = "subdistrict_name";

    static final String JSON_COST_RESULT_CODE = "code";
    static final String JSON_COST_RESULT_NAME = "name";
    static final String JSON_COST_RESULT_COST_DETAIL = "costs";

    static final String JSON_COST_DETAIL_SERVICE = "service";
    static final String JSON_COST_DETAIL_DESCRIPTION = "description";
    static final String JSON_COST_DETAIL_COST = "cost";

    static final String JSON_COST_VALUE = "value";
    static final String JSON_COST_ETD = "etd";
    static final String JSON_COST_NOTE = "note";

    static final String JSON_WAYBILL_DELIVERED = "delivered";

    static final String JSON_WAYBILL_SUMMARY = "summary";
    static final String JSON_WAYBILL_SUMMARY_COURIER_CODE = "courier_code";
    static final String JSON_WAYBILL_SUMMARY_COURIER_NAME = "courier_name";
    static final String JSON_WAYBILL_SUMMARY_WAYBILL_NUMBER = "waybill_number";
    static final String JSON_WAYBILL_SUMMARY_SERVICE_CODE = "service_code";
    static final String JSON_WAYBILL_SUMMARY_WAYBILL_DATE = "waybill_date";
    static final String JSON_WAYBILL_SUMMARY_SHIPPER_NAME = "shipper_name";
    static final String JSON_WAYBILL_SUMMARY_RECEIVER_NAME = "receiver_name";
    static final String JSON_WAYBILL_SUMMARY_ORIGIN = "origin";
    static final String JSON_WAYBILL_SUMMARY_DESTINATION = "destination";
    static final String JSON_WAYBILL_SUMMARY_STATUS = "status";

    static final String JSON_WAYBILL_DETAILS = "details";
    static final String JSON_WAYBILL_DETAILS_WAYBILL_NUMBER = "waybill_number";
    static final String JSON_WAYBILL_DETAILS_WAYBILL_DATE = "waybill_date";
    static final String JSON_WAYBILL_DETAILS_WAYBILL_TIME = "waybill_time";
    static final String JSON_WAYBILL_DETAILS_WEIGHT = "weight";
    static final String JSON_WAYBILL_DETAILS_ORIGIN = "origin";
    static final String JSON_WAYBILL_DETAILS_DESTINATION = "destination";
    static final String JSON_WAYBILL_DETAILS_SHIPPER_NAME = "shippper_name";
    static final String JSON_WAYBILL_DETAILS_SHIPPER_ADDRESS1 = "shipper_address1";
    static final String JSON_WAYBILL_DETAILS_SHIPPER_ADDRESS2 = "shipper_address2";
    static final String JSON_WAYBILL_DETAILS_SHIPPER_ADDRESS3 = "shipper_address3";
    static final String JSON_WAYBILL_DETAILS_SHIPPER_CITY = "shipper_city";
    static final String JSON_WAYBILL_DETAILS_RECEIVER_NAME = "receiver_name";
    static final String JSON_WAYBILL_DETAILS_RECEIVER_ADDRESS1 = "receiver_address1";
    static final String JSON_WAYBILL_DETAILS_RECEIVER_ADDRESS2 = "receiver_address2";
    static final String JSON_WAYBILL_DETAILS_RECEIVER_ADDRESS3 = "receiver_address3";
    static final String JSON_WAYBILL_DETAILS_RECEIVER_CITY = "receiver_city";

    static final String JSON_WAYBILL_DELIVERY_STATUS = "delivery_status";
    static final String JSON_WAYBILL_DELIVERY_STATUS_STATUS = "status";
    static final String JSON_WAYBILL_DELIVERY_STATUS_POD_RECEIVER = "pod_receiver";
    static final String JSON_WAYBILL_DELIVERY_STATUS_POD_DATE = "pod_date";
    static final String JSON_WAYBILL_DELIVERY_STATUS_POD_TIME = "pod_time";

    static final String JSON_WAYBILL_MANIFEST = "manifest";
    static final String JSON_WAYBILL_MANIFEST_MANIFEST_CODE = "manifest_code";
    static final String JSON_WAYBILL_MANIFEST_MANIFEST_DESCRIPTION = "manifest_description";
    static final String JSON_WAYBILL_MANIFEST_MANIFEST_DATE = "manifest_date";
    static final String JSON_WAYBILL_MANIFEST_MANIFEST_TIME = "manifest_time";
    static final String JSON_WAYBILL_MANIFEST_CITY_NAME = "city_name";
}
