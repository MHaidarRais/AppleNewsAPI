package com.rais.haidar.applenewsapi.ApiRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hoidar on 8/28/18.
 */

public class InstanceRetrofit {

    public static final String WebUrl = "https://newsapi.org/v2/";

    public static Retrofit setInit() {
        return new Retrofit.Builder()
                .baseUrl(WebUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }
}
