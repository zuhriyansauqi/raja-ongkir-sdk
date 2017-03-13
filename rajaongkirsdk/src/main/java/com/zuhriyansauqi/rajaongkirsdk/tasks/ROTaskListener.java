package com.zuhriyansauqi.rajaongkirsdk.tasks;

import com.zuhriyansauqi.rajaongkirsdk.responses.ROResponse;

/**
 * Created by zuhriyansauqi on 3/11/17.
 */

public interface ROTaskListener {

    void didExecuted(ROResponse response);
    void onError(ROResponse response);
}
