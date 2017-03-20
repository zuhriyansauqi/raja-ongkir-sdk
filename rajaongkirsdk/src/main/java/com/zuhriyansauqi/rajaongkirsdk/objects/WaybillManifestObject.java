package com.zuhriyansauqi.rajaongkirsdk.objects;

/**
 * Created by zuhriyansauqi on 3/21/17.
 */

public class WaybillManifestObject {

    private String code;
    private String description;
    private String date;
    private String time;
    private String cityName;

    public WaybillManifestObject(String code, String description, String date, String time, String cityName) {
        this.code = code;
        this.description = description;
        this.date = date;
        this.time = time;
        this.cityName = cityName;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCityName() {
        return cityName;
    }
}
