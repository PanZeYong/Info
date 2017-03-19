package com.pan.info.api;

import android.content.Context;

/**
 * Author : Pan
 * Date : 15/03/2017
 */

public class ApiFactory {

    public static HealthKnowledgeApi createHealthKnowledgeApi(Context context) {
        return new HealthKnowledgeApi(context);
    }
}
