package com.nextapp.service;

import com.nextapp.dto.MLModelResponse;
import com.nextapp.dto.Pest;
import com.nextapp.dto.AuthCallResponse;
import com.nextapp.dto.UpdateAuthResponse;
import com.nextapp.dto.UpdateAuthStatus;

import java.util.List;
import java.util.Map;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    final String BASE_URL = "http://192.168.1.4:8000/api/";

    @GET("pestlist/")
    Call<List<Pest>> getPestList();


    @Multipart
    @POST("pestdetect/")
    Call<MLModelResponse> processImage(@Part() MultipartBody.Part uploadImage,
                                       @Part("Usertype") RequestBody userType,
                                       @Part("Requestby") RequestBody requestBy,
                                       @Part("UserPhone") RequestBody contactNo,
                                       @Part("Location") RequestBody location,
                                        @Part("temperature") RequestBody temp);

    @GET("authviews/?status=")
    Call<List<AuthCallResponse>> getAuthViews();

    @POST("authviews/")
    Call<UpdateAuthResponse> updateAuthStatus(@Body UpdateAuthStatus updateAuthStatus);
}