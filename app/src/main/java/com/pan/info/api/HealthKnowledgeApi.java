package com.pan.info.api;

import android.content.Context;

import com.pan.info.BuildConfig;
import com.pan.info.Constant;
import com.pan.info.bean.HealthKnowledgeCategoryBean;
import com.pan.info.bean.HealthKnowledgeDetailBean;
import com.pan.info.bean.HealthKnowledgeListBean;
import com.pan.info.util.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Author : Pan
 * Date : 15/03/2017
 */

public class HealthKnowledgeApi {

    private final static String BASE_URL = "http://www.tngou.net/api/lore/";

    private HealthKnowledgeApiService mService;

    public HealthKnowledgeApi(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor())
                .connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(Utils.createCacheDir(context), Utils.calculateMemorySize()));

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mService = retrofit.create(HealthKnowledgeApiService.class);
    }

    public Observable<HealthKnowledgeCategoryBean> getCategories() {
        return mService.getCategories();
    }

    public Observable<HealthKnowledgeListBean> getList(int id, int page, int rows) {
        return mService.getList(id, page, rows);
    }

    public Observable<HealthKnowledgeDetailBean> getDetail(int id) {
        return mService.getDetail(id);
    }

    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    }
}
