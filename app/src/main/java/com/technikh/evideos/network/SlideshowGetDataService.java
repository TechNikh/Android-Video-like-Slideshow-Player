package com.technikh.evideos.network;

import com.technikh.evideos.models.slideshow.SlideshowJsonModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SlideshowGetDataService {
  @GET("raw/EEH7viWc")
//  @GET("bins/t9e8x")
  Call<SlideshowJsonModel> getAllJson();
}