package com.nextapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {
    private static WeatherClient instance = null;
    private WeatherApi weatherApi;

    private WeatherClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeatherApi.BASE_URL_TEMP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    public static synchronized WeatherClient getInstance() {
        if (instance == null) {
            instance = new WeatherClient();
        }
        return instance;
    }

    public WeatherApi getMyApi() {
        return weatherApi;
    }
}
