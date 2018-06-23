package com.example.sidkathuria14.dishtv.api;

import com.example.sidkathuria14.dishtv.models.main;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sidkathuria14 on 23/6/18.
 */

public interface MYApi {
    @GET("/main")
    Call<main> getTweets(@Query("keyword") String word);
}
