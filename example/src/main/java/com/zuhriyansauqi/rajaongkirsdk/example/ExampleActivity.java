package com.zuhriyansauqi.rajaongkirsdk.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zuhriyansauqi.rajaongkirsdk.AccountType;
import com.zuhriyansauqi.rajaongkirsdk.RajaOngkir;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.requests.CityRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.ProvinceRequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.RORequest;
import com.zuhriyansauqi.rajaongkirsdk.requests.SubdistrictRequest;
import com.zuhriyansauqi.rajaongkirsdk.responses.GeneralResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.city.CitiesResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.province.ProvincesResponse;
import com.zuhriyansauqi.rajaongkirsdk.responses.subdistrict.SubdistrictsResponse;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetCityTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetProvinceTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.GetSubdistrictTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.ROTask;
import com.zuhriyansauqi.rajaongkirsdk.tasks.ROTaskListener;

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
                .withAccountType(AccountType.STARTER)
                .andApiKey("Put your Raja Ongkir API here.")
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
        } catch (RONullRequestException | ROInvalidRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void didExecuted(ROResponse response) {
        if (response instanceof ProvincesResponse) {
            final ProvincesResponse res = (ProvincesResponse) response;
            Log.d("log", "provinces" + String.valueOf(res.getProvinces().size()));
        } else if (response instanceof CitiesResponse) {
            final CitiesResponse res = (CitiesResponse) response;
            Log.d("log", "cities" + String.valueOf(res.getCities().size()));
        } else if (response instanceof SubdistrictsResponse) {
            final SubdistrictsResponse res = (SubdistrictsResponse) response;
            Log.d("log", "subdistricts" + String.valueOf(res.getSubdistricts().size()));
        }
    }

    @Override
    public void onError(ROResponse response) {
        Log.d("log", ((GeneralResponse) response).getStatusDescription());
    }
}
