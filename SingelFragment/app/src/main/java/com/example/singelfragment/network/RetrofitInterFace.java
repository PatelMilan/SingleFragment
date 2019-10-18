package com.example.singelfragment.network;

import com.example.singelfragment.data.Example;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterFace {

    @GET("/previous.json")
    Call<ArrayList<Example>> getPreviousData();

    @GET("/upcoming.json")
    Call<ArrayList<Example>> getUpComingData();

    @GET("/current.json")
    Call<ArrayList<Example>> getCurrentData();
}