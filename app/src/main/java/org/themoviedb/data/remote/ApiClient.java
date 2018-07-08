package org.themoviedb.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ApiClient {

    private static ApiClient instance;
    private MoviesApi service;

    private ApiClient() {

    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
            instance.init();
        }
        return instance;
    }

    public MoviesApi getService() {
        return service;
    }

    private void init() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient().create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    HttpUrl newHttpUrl = chain.request().url().newBuilder()
                            //.addQueryParameter("api_key", "a07e22bc18f5cb106bfe4cc1f83ad8ed")
                            .build();
                    Request newReq = chain.request().newBuilder()
                            .url(newHttpUrl).build();

                    return chain.proceed(newReq);
                })
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.service = retrofit.create(MoviesApi.class);
    }
}
