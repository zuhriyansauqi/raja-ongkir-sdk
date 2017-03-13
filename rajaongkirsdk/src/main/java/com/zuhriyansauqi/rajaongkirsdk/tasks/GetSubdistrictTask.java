package com.zuhriyansauqi.rajaongkirsdk.tasks;

import android.os.AsyncTask;

import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.RajaOngkirBase;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.objects.CityObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.ProvinceObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.SubdistrictObject;
import com.zuhriyansauqi.rajaongkirsdk.requests.CityRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.SubdistrictRequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.subdistrict.SubdistrictResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.subdistrict.SubdistrictsResponse;

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
 * Created by zuhriyansauqi on 3/13/17.
 */

public class GetSubdistrictTask implements ROTask, RajaOngkirBase {

    private RajaOngkir rajaOngkir;
    private RORequest request;
    private ROResponse response;
    private ROTaskListener listener;
    private String url;

    public GetSubdistrictTask(RajaOngkir rajaOngkir, RORequest request, ROTaskListener listener) {
        this.rajaOngkir = rajaOngkir;
        this.request = request;
        this.listener = listener;
        this.url = rajaOngkir.getBaseUrl() + "/subdistrict";
    }

    @Override
    public void execute() throws RONullRequestException, ROInvalidRequestException {
        if (request == null) throw new RONullRequestException("Raja Ongkir request is null!");

        if (!(request instanceof SubdistrictRequest))
            throw new ROInvalidRequestException("Raja Ongkir request is invalid!");

        final SubdistrictRequest request = (SubdistrictRequest) this.request;

        if (request.getCity() != null) {
            HttpUrl url = HttpUrl.parse(this.url).newBuilder()
                    .addQueryParameter(SubdistrictRequest.CITY, request.getCity())
                    .build();

            this.url = url.toString();
        }

        if (request.getId() != null) {
            HttpUrl url = HttpUrl.parse(this.url).newBuilder()
                    .addQueryParameter(CityRequest.ID, request.getId())
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
                        listener.didExecuted(response);
                    }

                    if (listener != null) {
                        listener.didExecuted(response);
                    }

                } catch (IOException e) {
                    if (listener != null)
                        listener.onError(null);
                }
                return null;
            }
        }.execute();
    }

    @Override
    public void generateResult(JSONObject json) throws JSONException {
        JSONObject root = json.getJSONObject(JSON_RAJA_ONGKIR);
        JSONObject status = root.getJSONObject(JSON_STATUS);

        int code = status.getInt(JSON_STATUS_CODE);
        String description = status.getString(JSON_STATUS_DESCRIPTION);

        response = new GeneralResponse(code, description);

        if (code != 200) return;

        final SubdistrictRequest request = (SubdistrictRequest) this.request;

        if (request.getId() == null) {
            JSONArray subdistricts = root.getJSONArray(JSON_RESULTS);
            List<SubdistrictObject> subdistrictList = new ArrayList<>();

            for (int i =0; i < subdistricts.length(); i++) {
                JSONObject object = subdistricts.getJSONObject(i);

                subdistrictList.add(new SubdistrictObject(
                        object.getString(JSON_SUBDISTRICT_ID),
                        new CityObject(
                                object.getString(JSON_CITY_ID),
                                new ProvinceObject(
                                        object.getString(JSON_PROVINCE_ID),
                                        object.getString(JSON_PROVINCE_NAME)
                                ),
                                object.getString(JSON_CITY_TYPE),
                                object.getString(JSON_CITY_NAME_2)
                        ),
                        object.getString(JSON_SUBDISTRICT_NAME)
                ));

                response = new SubdistrictsResponse(code, description, subdistrictList);
            }
        } else {
            JSONObject subdistrictObject = root.getJSONObject(JSON_RESULTS);

            String id = subdistrictObject.getString(JSON_SUBDISTRICT_ID);
            String provinceId = subdistrictObject.getString(JSON_PROVINCE_ID);
            String provinceName = subdistrictObject.getString(JSON_PROVINCE_NAME);
            String cityId = subdistrictObject.getString(JSON_CITY_ID);
            String cityName = subdistrictObject.getString(JSON_CITY_NAME_2);
            String cityType = subdistrictObject.getString(JSON_CITY_TYPE);
            String name = subdistrictObject.getString(JSON_SUBDISTRICT_NAME);

            ProvinceObject province = new ProvinceObject(provinceId, provinceName);
            CityObject city = new CityObject(cityId, province, cityType, cityName);
            SubdistrictObject subdistrict = new SubdistrictObject(id, city, name);

            response = new SubdistrictResponse(code, description, subdistrict);
        }
    }
}
