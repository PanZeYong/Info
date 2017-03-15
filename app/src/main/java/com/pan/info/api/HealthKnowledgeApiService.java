package com.pan.info.api;

import com.pan.info.bean.HealthKnowledgeCategoryBean;
import com.pan.info.bean.HealthKnowledgeDetailBean;
import com.pan.info.bean.HealthKnowledgeListBean;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author : Pan
 * Date : 15/03/2017
 */

public interface HealthKnowledgeApiService {

    @GET("classify")
    Observable<HealthKnowledgeCategoryBean> getCategories();

    @POST("list")
    Observable<HealthKnowledgeListBean> getList(
            @Field("id") int id,
            @Field("page") int page,
            @Field("rows") int rows);

    @POST("show")
    Observable<HealthKnowledgeDetailBean> getDetail(@Field("id") int id);
}
