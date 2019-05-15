package com.example.gendx_android.data.repo;

import com.example.gendx_android.data.model.ResponseMany;
import com.example.gendx_android.data.model.ResponseOne;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.ByteArrayOutputStream;

public interface GendXApiService {
    @GET("/api/recognize_with_opencv")
    Call<ResponseMany> getGenderMany(@Query("image") ByteArrayOutputStream image);

    @GET("/api/recognize")
    Call<ResponseOne> getGenderOne(@Query("image") String image);
}
