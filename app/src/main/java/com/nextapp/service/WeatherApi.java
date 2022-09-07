package com.nextapp.service;

import com.nextapp.dto.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {

    String BASE_URL_TEMP = "http://api.weatherapi.com/";

    @GET("v1/current.json?key=4c700ec20bcb48ef8d7191408220209&q=colombo&aqi=no")
    Call<Weather> getWeatherInfo();
}
