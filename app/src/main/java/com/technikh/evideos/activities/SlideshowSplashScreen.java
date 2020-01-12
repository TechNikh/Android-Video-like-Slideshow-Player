package com.technikh.evideos.activities;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.danikula.videocache.HttpProxyCacheServer;
import com.technikh.evideos.R;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.gson.Gson;
import com.technikh.evideos.app.MyApplication;
import com.technikh.evideos.models.slideshow.lineMedia;
import com.technikh.evideos.models.slideshow.SlideshowJsonModel;
import com.technikh.evideos.network.SlideshowGetDataService;
import com.technikh.evideos.network.SlideshowRetrofitInstance;
import com.technikh.evideos.preferences.SlideshowSharedPreferences;
import com.technikh.evideos.util.ImagesCache;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SlideshowSplashScreen extends AppCompatActivity {
    HashMap<String, Boolean> downloadStatus=new HashMap<String, Boolean>();
    private SlideshowJsonModel data;
    private ImageView imageView;
    private ImagesCache cache;
    public static int CountValue = 0;
    public static int audioCountValue = 0;
    HttpProxyCacheServer cacheProxy;
    private SlideshowRetrofitInstance retrofitInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        cacheProxy = MyApplication.getProxy(SlideshowSplashScreen.this);
        //getSupportActionBar().hide();
        imageView = findViewById(R.id.Image);
        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        CountValue = 0;
        //loadData();
        Boolean showSplashScreen = false;
        downloadStatus = (HashMap)SlideshowSharedPreferences.getPreferenceObjectDownloadStatus(SlideshowSplashScreen.this);
        if(downloadStatus == null){
            downloadStatus=new HashMap<>();
        }
        Log.d("spl", "onCreate: downloadStatus "+downloadStatus.size());
        if(downloadStatus.isEmpty()){
            showSplashScreen = true;
        }
        Iterator myVeryOwnIterator = downloadStatus.keySet().iterator();
        while(myVeryOwnIterator.hasNext()) {
            String key=(String)myVeryOwnIterator.next();
            Boolean value=(Boolean)downloadStatus.get(key);
            Log.d("showSplashScreen", "showSplashScreen: key"+key+" value "+value);
            if(!value){
                showSplashScreen = true;
                break;
            }
        }
        // if (!SlideshowSharedPreferences.GET_SAVE_IMAGES(SlideshowSplashScreen.this)) {
        if (showSplashScreen) {
            Log.d("TAG", "1qa1 onCreate: GET_SAVE_IMAGES");
//            loadJSONFromAsset(this);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new LoadJSON().doInBackground("");
                }
            });
        } else {
            Log.d("TAG", "1qa1 onCreate: GET_SAVE_IMAGES else");
            finish();
            String json_string = getIntent().getStringExtra("json_string");
            startActivity(new Intent(SlideshowSplashScreen.this, SlideShowActivity.class)
                    .putExtra("json_string", json_string)
            );
        }
    }

    public void loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Local.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            data = new Gson().fromJson(json, SlideshowJsonModel.class);
            LoadImages();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    private void LoadImages() {
        cache = ImagesCache.getInstance();//Singleton instance handled in ImagesCache class.
        cache.initializeCache();
        boolean foundImages = false;

        for (int slides = 0; slides < data.getSlides().size(); slides++) {
            Log.d("TAG", "Slides: " + slides);
            if (data.getSlides().get(slides).getBackgrounds() != null) {
                for (int back = 0; back < data.getSlides().get(slides).getBackgrounds().size(); back++) {
                    if (data.getSlides().get(slides).getBackgrounds().size() != 0 && data.getSlides().get(slides).getBackgrounds().get(back).getUrl() != null) {
                        addImageInData(data.getSlides().get(slides).getBackgrounds().get(back).getUrl());
                        foundImages = true;
                    }
                }
            }
            if (data.getSlides().get(slides).getLines() != null) {
                for (int lines = 0; lines < data.getSlides().get(slides).getLines().size(); lines++) {
                    Log.d("TAG", "Lines: " + lines);
                    List<lineMedia> dImages = data.getSlides().get(slides).getLines().get(lines).getImages();
                    if (dImages != null) {
                        for (int images = 0; images < dImages.size(); images++) {
                            Log.d("TAG", "Images: " + images);
                            if (dImages.get(images).getMediaAttr() != null) {
                                addImageInData(dImages.get(images).getMediaFile().getImage().getUrl());
                                foundImages = true;
                                if ((dImages.get(images).getMediaAttr().getImageAudio() != null) && (!dImages.get(images).getMediaAttr().getImageAudio().getUrl().isEmpty())) {
                                    addAudioInCache(dImages.get(images).getMediaAttr().getImageAudio().getUrl());
                                }
                            }
                        }
                    }
                }
            }
        }
        if(!foundImages){
            Log.d("TAG", "xdfre foundImages false IMAGES_SAVED: ");
            SlideshowSharedPreferences.IMAGES_SAVED(SlideshowSplashScreen.this, true);
            finish();
            String json_string = getIntent().getStringExtra("json_string");
            startActivity(new Intent(SlideshowSplashScreen.this, SlideShowActivity.class)
                    .putExtra("json_string", json_string)
            );
        }

    }

    private void addAudioInCache(String url) {
        Log.d("TAG", "addAudioInCache: "+url);
        //downloadStatus.put(url, false);
        //String proxyUrl = cacheProxy.getProxyUrl(url);
        audioCountValue++;
    }

    private void addImageInData(String image) {
        Log.d("TAG", "xdfre addImageInData: ");
        if(downloadStatus.get(image) == null) {
            downloadStatus.put(image, false);
        }
        //Bitmap bm = cache.getImageFromWarehouse(image);
        CountValue++;
        imageView.setImageBitmap(null);
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //imageView.setImageBitmap(resource);
                        downloadStatus.put(image, true);
                    }
                });
        //DownloadImageTask imgTask = new DownloadImageTask(SlideshowSplashScreen.this, cache, imageView, 600, 600, SlideshowSplashScreen.this);//Since you are using it from `Activity` call second Constructor.
        //imgTask.execute(image);
    }

    private class LoadJSON extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            loadData();
            return "";
        }
    }

    public void loadData() {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = SlideshowRetrofitInstance.getRetrofitInstance();

        SlideshowGetDataService json = retrofit.create(SlideshowGetDataService.class);
        json.getAllJson().enqueue(new Callback<SlideshowJsonModel>() {
            @Override
            public void onResponse(Call<SlideshowJsonModel> call, Response<SlideshowJsonModel> response) {
                if (response.isSuccessful()) {
                    data = response.body();
                    SlideshowSharedPreferences.setPreferenceObject(SlideshowSplashScreen.this, data, "data");
                    LoadImages();
                    // Before recursively calling splashscreen send downloadStatus to preferences
                    SlideshowSharedPreferences.setPreferenceObject(SlideshowSplashScreen.this, downloadStatus, "downloadStatus");
                    startActivity(new Intent(SlideshowSplashScreen.this, SlideshowSplashScreen.class));
                }
            }

            @Override
            public void onFailure(Call<SlideshowJsonModel> call, Throwable t) {

            }
        });
    }

}
