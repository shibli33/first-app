package net.home.standardapp.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {
    //private static final String BASE_URL = "http://192.168.0.106:8088/schedule/"; //Local Access
    private static final String BASE_URL = "http://192.168.0.106:8081/country/"; //Local Access

    public static Retrofit getClient(/*String user, String password*/) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS) //retrofit default 10 seconds
                .writeTimeout(20, TimeUnit.SECONDS) //retrofit default 10 seconds
                .readTimeout(30, TimeUnit.SECONDS) //retrofit default 10 seconds
                .addInterceptor(interceptor)
                //.addInterceptor(new BasicAuthInterceptor(user, password))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}
