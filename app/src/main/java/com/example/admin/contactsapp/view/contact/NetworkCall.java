package com.example.admin.contactsapp.view.contact;

import com.example.admin.contactsapp.model.Randomuser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.example.admin.contactsapp.util.CONSTANTS.BASE_URL;
import static com.example.admin.contactsapp.util.CONSTANTS.PATH;

public class NetworkCall {

    public static Retrofit create(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Call<Randomuser> getRandomUser(){
        Retrofit retrofit =  create();
        RandomUserService weatherService = retrofit.create(RandomUserService.class);
        return weatherService.getRandomUserData();
    }
    public interface RandomUserService{
        @GET(PATH)
        Call<Randomuser> getRandomUserData();

    }
}
