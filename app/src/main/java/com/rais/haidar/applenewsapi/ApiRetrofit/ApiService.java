package com.rais.haidar.applenewsapi.ApiRetrofit;

import com.rais.haidar.applenewsapi.Model.ResponseNews;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hoidar on 8/28/18.
 */

public interface ApiService {
    @GET("everything?q=apple&apiKey=742f9c39ed6c4d5c8874aadb10b34b79")
    Call<ResponseNews> readNews();
}
