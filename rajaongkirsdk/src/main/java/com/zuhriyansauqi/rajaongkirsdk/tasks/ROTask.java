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
    final String CONTENT_TYPE = "content-type";
    final String FORM_ENCODED = "application/x-www-form-urlencoded";

    void execute() throws RONullRequestException, ROInvalidRequestException;
    void generateResult(JSONObject json) throws JSONException;
}
