package com.pan.info.api;

/**
 * Author : Pan
 * Date : 15/03/2017
 */

public class ApiFactory {

    public static HealthKnowledgeApi createHealthKnowledgeApi() {
        return new HealthKnowledgeApi();
    }
}
