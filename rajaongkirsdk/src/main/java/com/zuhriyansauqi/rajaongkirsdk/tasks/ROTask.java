package com.zuhriyansauqi.rajaongkirsdk.tasks;

import android.content.Context;

import com.zuhriyansauqi.rajaongkirsdk.exceptions.ROInvalidRequestException;
import com.zuhriyansauqi.rajaongkirsdk.exceptions.RONullRequestException;
import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public interface ROTask {

    final String KEY = "key";

    void execute() throws RONullRequestException, ROInvalidRequestException;
    void generateResult(JSONObject json) throws JSONException;
}
