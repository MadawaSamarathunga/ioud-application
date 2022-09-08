package com.nextapp.service;

import com.nextapp.dto.Pest;
import com.nextapp.dto.AuthCallResponse;

import java.util.List;



import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    String BASE_URL = "http://192.168.1.4:8000/api/";


    @GET("pestlist/")
    Call<List<Pest>> getPestList();


    @Multipart
    @POST("pestdetect/")
    Call<Void> processImage(@Part("Uploadimage") MultipartBody.Part uploadImage,
                            @Part("Usertype") RequestBody userType,
                            @Part("Requestby") RequestBody requestBy,
                            @Part("UserPhone") RequestBody contactNo,
                            @Part("Location") RequestBody location);

    @GET("authviews/")
    Call<List<AuthCallResponse>> getAuthViews();
}