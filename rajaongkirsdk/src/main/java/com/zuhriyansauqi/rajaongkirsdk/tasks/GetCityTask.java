package com.zuhriyansauqi.rajaongkirsdk.tasks;

import android.os.AsyncTask;

import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.enums.ResponseTypes;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.objects.CityObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.ProvinceObject;
import com.zuhriyansauqi.rajaongkirsdk.requests.CityRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.city.CitiesResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.city.CityResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zuhriyansauqi on 3/12/17.
 */

public class GetCityTask extends ROTaskBase {

    public GetCityTask(RajaOngkir rajaOngkir, RORequest request, ROTaskListener listener) {
        super(rajaOngkir, request, listener);
        this.url = rajaOngkir.getBaseUrl() + "/city";
    }

    @Override
    public void execute() throws RONullRequestException, ROInvalidRequestException {
        if (request == null) throw new RONullRequestException("Raja Ongkir request is null!");

        if (!(request instanceof CityRequest))
            throw new ROInvalidRequestException("Raja Ongkir request is invalid!");

        final CityRequest request = (CityRequest) this.request;

        if (request.getId() != null) {
            HttpUrl url = HttpUrl.parse(this.url).newBuilder()
                    .addQueryParameter(CityRequest.ID, request.getId())
                    .build();

            this.url = url.toString();
        }

        if (request.getProvince() != null) {
            HttpUrl url = HttpUrl.parse(this.url).newBuilder()
                    .addQueryParameter(CityRequest.PROVINCE, request.getProvince())
                    .build();

            this.url = url.toString();
        }

        new AsyncTask<Object, Object, Void>() {

            @Override
            protected Void doInBackground(Object... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request req = new Request.Builder()
                            .url(url)
                            .get()
                            .addHeader(KEY, rajaOngkir.getApiKey())
                            .build();

                    Response res = client.newCall(req).execute();

                    try {
                        generateResult(new JSONObject(res.body().string()));

                    } catch (JSONException e) {
                        GetCityTask.this.responseType = ResponseTypes.PARSE_ERROR;
                        listener.didExecuted(GetCityTask.this);
                    }

                    if (listener != null) {
                        listener.didExecuted(GetCityTask.this);
                    }

                } catch (IOException e) {
                    if (listener != null)
                        GetCityTask.this.responseType = ResponseTypes.INTERNET_ERROR;
                        listener.onError(GetCityTask.this);
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

        final CityRequest request = (CityRequest) this.request;

        if (request.getId() == null) {
            JSONArray cities = root.getJSONArray(JSON_RESULTS);
            List<CityObject> cityList = new ArrayList<>();

            for (int i = 0; i < cities.length(); i++) {
                JSONObject object = cities.getJSONObject(i);
                cityList.add(new CityObject(
                        object.getString(JSON_CITY_ID),
                        new ProvinceObject(
                                object.getString(JSON_PROVINCE_ID),
                                object.getString(JSON_PROVINCE_NAME)
                        ),
                        object.getString(JSON_CITY_TYPE),
                        object.getString(JSON_CITY_NAME),
                        object.getString(JSON_CITY_POSTAL_CODE)
                ));
            }

            response = new CitiesResponse(code, description, cityList);

        } else {
            JSONObject cityObject = root.getJSONObject(JSON_RESULTS);

            String id = cityObject.getString(JSON_CITY_ID);
            String provinceId = cityObject.getString(JSON_PROVINCE_ID);
            String provinceName = cityObject.getString(JSON_PROVINCE_NAME);
            String type = cityObject.getString(JSON_CITY_TYPE);
            String name = cityObject.getString(JSON_CITY_NAME);
            String postalCode = cityObject.getString(JSON_CITY_POSTAL_CODE);

            ProvinceObject province = new ProvinceObject(provinceId, provinceName);
            CityObject city = new CityObject(id, province, type, name, postalCode);

            response = new CityResponse(code, description, city);
        }
    }
}
