package com.zuhriyansauqi.rajaongkirsdk.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.RajaOngkirBase;
import com.zuhriyansauqi.rajaongkirsdk.enums.ResponseTypes;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.objects.ProvinceObject;
import com.zuhriyansauqi.rajaongkirsdk.requests.ProvinceRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.province.ProvinceResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.province.ProvincesResponse;

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
 * Created by zuhriyansauqi on 3/11/17.
 */

public class GetProvinceTask extends ROTaskBase {

    public GetProvinceTask(RajaOngkir rajaOngkir, RORequest request, ROTaskListener listener) {
        super(rajaOngkir, request, listener);
        this.url = rajaOngkir.getBaseUrl() + "/province";
    }

    @Override
    public void execute() throws RONullRequestException, ROInvalidRequestException {
        if (request == null) throw new RONullRequestException("Raja Ongkir request is null!");

        if (!(request instanceof ProvinceRequest))
            throw new ROInvalidRequestException("Raja Ongkir request is invalid!");

        final ProvinceRequest request = (ProvinceRequest) this.request;

        if (request.getId() != null) {
            HttpUrl url = HttpUrl.parse(this.url).newBuilder()
                    .addQueryParameter(ProvinceRequest.ID, request.getId())
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
                        GetProvinceTask.this.responseType = ResponseTypes.PARSE_ERROR;
                        if (listener != null) {
                            ((Activity) rajaOngkir.getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onError(GetProvinceTask.this);
                                }
                            });
                        }
                    }

                    if (listener != null) {
                        ((Activity) rajaOngkir.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.didExecuted(GetProvinceTask.this);
                            }
                        });
                    }

                } catch (IOException e) {
                    GetProvinceTask.this.responseType = ResponseTypes.INTERNET_ERROR;
                    if (listener != null) {
                        ((Activity) rajaOngkir.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(GetProvinceTask.this);
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

        final ProvinceRequest request = (ProvinceRequest) this.request;

        if (request.getId() == null) {
            JSONArray provinces = root.getJSONArray(JSON_RESULTS);
            List<ProvinceObject> provinceList = new ArrayList<>();

            for (int i = 0; i < provinces.length(); i++) {
                JSONObject object = provinces.getJSONObject(i);
                provinceList.add(
                        new ProvinceObject(
                                object.getString(JSON_PROVINCE_ID),
                                object.getString(JSON_PROVINCE_NAME)));
            }

            response = new ProvincesResponse(code, description, provinceList);
        } else {
            JSONObject provinceObject = root.getJSONObject(JSON_RESULTS);

            String id = provinceObject.getString(JSON_PROVINCE_ID);
            String name = provinceObject.getString(JSON_PROVINCE_NAME);
            ProvinceObject province = new ProvinceObject(id, name);

            response = new ProvinceResponse(code, description, province);
        }
    }
}
