package com.zuhriyansauqi.rajaongkirsdk.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zuhriyansauqi.rajaongkirsdk.AccountType;
import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.enums.Couriers;
import com.zuhriyansauqi.rajaongkirsdk.enums.PlaceTypes;
import com.zuhriyansauqi.rajaongkirsdk.enums.ResponseTypes;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.objects.CityObject;
import com.zuhriyansauqi.rajaongkirsdk.requests.CityRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.CostRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.ProvinceRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.SubdistrictRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.WaybillRequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.city.CitiesResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.waybill.WaybillResponse;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetCityTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetCostTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetProvinceTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetSubdistrictTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetWaybillTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.ROTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.ROTaskListener;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public class ExampleActivity extends AppCompatActivity implements ROTaskListener {

    private RORequest request;
    private ROTask task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RajaOngkir rajaOngkir = new RajaOngkir.Init(this)
                .withAccountType(AccountType.PRO)
                .andApiKey("-- Put your API key here --")
                .create();

        try {
            request = new ProvinceRequest(null);
            task = new GetProvinceTask(rajaOngkir, request, this);
            task.execute();

            request = new CityRequest(null, null);
            task = new GetCityTask(rajaOngkir, request, this);
            task.execute();

            request = new SubdistrictRequest("1", null);
            task = new GetSubdistrictTask(rajaOngkir, request, this);
            task.execute();

            request = new WaybillRequest("SOCAG00183235715", Couriers.JALUR_NUGRAHA_EKAKURIR);
            task = new GetWaybillTask(rajaOngkir, request, this);
            task.execute();

            request = new CostRequest("501", PlaceTypes.CITY, "574", PlaceTypes.SUBDISTRICT, 1700,
                    new ArrayList<>(Arrays.asList(Couriers.JALUR_NUGRAHA_EKAKURIR, Couriers.POS_INDONESIA)),
                    null, null, null, null);
            task = new GetCostTask(rajaOngkir, request, this);
            task.execute();
        } catch (RONullRequestException | ROInvalidRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void didExecuted(ROTask task) {
        if (task.getResponseType() == ResponseTypes.SUCCESS
                && task instanceof GetCityTask
                && task.getResponse() instanceof CitiesResponse) {
            final CitiesResponse response = (CitiesResponse) task.getResponse();
            Log.d("Log", "Total cities: " + response.getCities().size());
            for (CityObject city : response.getCities()) {
                Log.d("Log", city.toString());
            }
        } else if (task.getResponseType() == ResponseTypes.SUCCESS
                && task instanceof GetWaybillTask
                && task.getResponse() instanceof WaybillResponse) {
            final WaybillResponse response = (WaybillResponse) task.getResponse();
            Log.d("Log", "Delived: " + response.getDelivered());
        }
    }

    @Override
    public void onError(ROTask task) {
        switch (task.getResponseType()) {
            case PARSE_ERROR:
            case INTERNET_ERROR:
            case INVALID_API:
            case UNKNOWN_ERROR:
                Log.d("Log", "error");
                Log.d("Log", "error" + task.getResponseType());
                break;
        }
    }
}
