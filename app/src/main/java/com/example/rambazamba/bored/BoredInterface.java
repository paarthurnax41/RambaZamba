package com.example.rambazamba.bored;

import com.example.rambazamba.bored.Bored;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BoredInterface {

    @GET("/api/activity/")
    Call<Bored> getRandomActivity();


}
