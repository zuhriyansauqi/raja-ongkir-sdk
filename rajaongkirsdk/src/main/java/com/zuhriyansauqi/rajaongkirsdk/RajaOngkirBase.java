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


}
