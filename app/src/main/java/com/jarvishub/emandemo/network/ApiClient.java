package com.jarvishub.emandemo.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jarvishub.emandemo.utils.APIUrl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mrrobot on 10/3/17.
 */

public class ApiClient {

    private Retrofit retrofit = null;

    public Retrofit getClient(String token) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100000, TimeUnit.SECONDS)
//                .readTimeout(100000, TimeUnit.SECONDS).build();
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        //todo : have to remove logging interpreter in production build

        Log.d("temp", "getClient: .................... " + token);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        // .connectTimeout(100000, TimeUnit.SECONDS)
        //.readTimeout(100000, TimeUnit.SECONDS).build();

        client.addInterceptor(chain -> {

            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer" + token)
                    .build();
            return chain.proceed(request);
        }).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        client.addNetworkInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrl.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }
}
