package com.kien.lib;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String base_Url = "http://10.0.2.2:8087";

    public static String getBase_Url() {
        return base_Url;
    }

    public static void setBase_Url(String base_Url) {
        RetrofitClient.base_Url = base_Url;
    }
    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
