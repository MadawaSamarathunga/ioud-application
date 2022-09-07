package com.nextapp.service;

import com.nextapp.dto.Pest;
import com.nextapp.dto.AuthCallResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.1.4:8000/api/";


    @GET("pestlist/")
    Call<List<Pest>> getPestList();

    @GET("authviews/")
    Call<List<AuthCallResponse>> getAuthViews();
}