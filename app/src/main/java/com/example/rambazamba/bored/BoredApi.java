package com.example.rambazamba.bored;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoredApi {
    private static BoredInterface api;

    public static BoredInterface getApi() {
        if (api == null) {
            //generate api
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.boredapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api =  retrofit.create(BoredInterface.class);
        }
        return api;
    }

}
