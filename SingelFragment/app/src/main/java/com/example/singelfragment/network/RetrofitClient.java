package com.example.singelfragment.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    /* Set the base url for your web service ex.https://www.google.com/ */
    private static final String BASE_URL = "https://csiw.000webhostapp.com/";

    public static RetrofitInterFace getApi() {
        return getRetrofit().create(RetrofitInterFace.class);
    }

    private static Retrofit getRetrofit() {
        /* Create OkHttpClintBuilders Object
         * * * * * * * * * * * * * * * * * * */
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        /* Create HttpLoggingInterceptor Object
         * to Printing Log information for
         * sending parameters or response values
         * * * * * * * * * * * * * * * * * * * */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        /* set your desired log level
         * * * * * * * * * * * * * * */
        logging.level(HttpLoggingInterceptor.Level.BODY);

        /* OkHttpClint Adding Interceptor
         * * * * * * * * * * * * * * * * */
        httpClient.addInterceptor(logging);
        httpClient.readTimeout(240, TimeUnit.SECONDS);
        httpClient.connectTimeout(240, TimeUnit.SECONDS);

        /* Return The Retrofit Objects
         * * * * * * * * * * * * * * */
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}