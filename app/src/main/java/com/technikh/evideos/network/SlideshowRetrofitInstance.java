package com.technikh.evideos.network;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.technikh.evideos.app.MyApplication.getAppContext;

public class SlideshowRetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://pastebin.com/";
//    private static final String BASE_URL = "https://api.myjson.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            File httpCacheDirectory = new File(getAppContext().getCacheDir(), "offlineCache");
            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .cache(cache)
                    .addNetworkInterceptor(new ProvideCacheInterceptor())
                    .addInterceptor(new ProvideOfflineCacheInterceptor())
                    //.addInterceptor(new ReceivedCookiesInterceptor())
                    .build();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
