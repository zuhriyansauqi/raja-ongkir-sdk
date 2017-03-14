package com.zuhriyansauqi.rajaongkirsdk.tasks;

import android.os.AsyncTask;

import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.RajaOngkirBase;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.objects.CostDetailObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.CostObject;
import com.zuhriyansauqi.rajaongkirsdk.objects.CostResultObject;
import com.zuhriyansauqi.rajaongkirsdk.requests.CostRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.costresult.CostResultResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.costresult.CostResultsResponse;

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
 * Created by zuhriyansauqi on 3/14/17.
 */

public class GetCostTask implements ROTask, RajaOngkirBase {

    private RajaOngkir rajaOngkir;
    private RORequest request;
    private ROResponse response;
    private ROTaskListener listener;
    private String url;

    public GetCostTask(RajaOngkir rajaOngkir, RORequest request, ROTaskListener listener) {
        this.rajaOngkir = rajaOngkir;
        this.request = request;
        this.listener = listener;
        this.url = rajaOngkir.getBaseUrl() + "/cost";
    }

    private String getCouriersString(final CostRequest request) {
        String result = "";

        for (int i = 0; i < request.getCouriers().size(); i++) {
            result = result + request.getCouriers().get(i).toString();

            if (i != request.getCouriers().size() - 1) {
                result = result + ":";
            }
        }

        return result;
    }

    private CostResultObject getSingleCostResult(JSONObject json) throws JSONException {
        JSONArray detailsObject = json.getJSONArray(JSON_COST_RESULT_COST_DETAIL);
        List<CostDetailObject> detailsList = new ArrayList<>();

        for (int i = 0; i < detailsObject.length(); i++) {
            JSONObject detailObject = detailsObject.getJSONObject(i);
            JSONArray costsObject = detailObject.getJSONArray(JSON_COST_DETAIL_COST);

            List<CostObject> costsList = new ArrayList<>();

            for (int j = 0; j < costsObject.length(); j++) {
                JSONObject costObject = costsObject.getJSONObject(j);

                costsList.add(new CostObject(
                        costObject.getDouble(JSON_COST_VALUE),
                        costObject.getString(JSON_COST_ETD),
                        costObject.getString(JSON_COST_NOTE)
                ));
            }

            detailsList.add(new CostDetailObject(
                    detailObject.getString(JSON_COST_DETAIL_SERVICE),
                    detailObject.getString(JSON_COST_DETAIL_DESCRIPTION),
                    costsList
            ));
        }

        return new CostResultObject(
                json.getString(JSON_COST_RESULT_CODE),
                json.getString(JSON_COST_RESULT_NAME),
                detailsList
        );
    }

    @Override
    public void execute() throws RONullRequestException, ROInvalidRequestException {
        if (request == null) throw new RONullRequestException("Raja Ongkir request is null!");

        if (!(request instanceof CostRequest))
            throw new ROInvalidRequestException("Raja Ongkir request is invalid!");

        final CostRequest request = (CostRequest) this.request;

        new AsyncTask<Object, Object, Void>() {

            @Override
            protected Void doInBackground(Object... params) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    FormBody.Builder builder = new FormBody.Builder()
                            .add(CostRequest.ORIGIN, request.getOrigin())
                            .add(CostRequest.ORIGIN_TYPE, request.getOriginType().toString())
                            .add(CostRequest.DESTINATION, request.getDestination())
                            .add(CostRequest.DESTINATION_TYPE, request.getDestionationType().toString())
                            .add(CostRequest.WEIGHT, String.valueOf(request.getWeight()))
                            .add(CostRequest.COURIER, getCouriersString(request));

                    if (request.getLength() != null) {
                        builder.add(CostRequest.LENGTH, String.valueOf(request.getLength()));
                    }

                    if (request.getWidth() != null) {
                        builder.add(CostRequest.WIDTH, String.valueOf(request.getWidth()));
                    }

                    if (request.getHeight() != null) {
                        builder.add(CostRequest.HEIGHT, String.valueOf(request.getHeight()));
                    }

                    if (request.getDiameter() != null) {
                        builder.add(CostRequest.DIAMETER, String.valueOf(request.getDiameter()));
                    }

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

        final CostRequest request = (CostRequest) this.request;

        if (request.getCouriers().size() > 1) {
            JSONArray costResults = root.getJSONArray(JSON_RESULTS);
            List<CostResultObject> costResultList = new ArrayList<>();

            for (int i = 0; i < costResults.length(); i++) {
                JSONObject object = costResults.getJSONObject(i);

                costResultList.add(getSingleCostResult(object));
            }

            response = new CostResultsResponse(code, description, costResultList);
        } else {
            JSONObject costResult = root.getJSONObject(JSON_RESULTS);

            response = new CostResultResponse(code, description, getSingleCostResult(costResult));
        }
    }
}
