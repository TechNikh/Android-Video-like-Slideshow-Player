package com.technikh.evideos.network;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.technikh.evideos.activities.SlideShowActivity;
import com.technikh.evideos.activities.SlideshowSplashScreen;
import com.technikh.evideos.preferences.SlideshowSharedPreferences;
import com.technikh.evideos.util.ImagesCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private int inSampleSize = 0;
    private String imageUrl;
    private BaseAdapter adapter;
    private ImagesCache cache;
    private int desiredWidth, desiredHeight;
    private Bitmap image = null;
    private ImageView ivImageView;
    private static SlideshowSplashScreen _splashScreen;
    private static int i = 0;
    SharedPreferences mPrefs;
    private Context context;
    private RequestBuilder<PictureDrawable> requestBuilder;

    public DownloadImageTask(BaseAdapter adapter, int desiredWidth, int desiredHeight) {
        this.adapter = adapter;
        this.cache = ImagesCache.getInstance();
        this.desiredWidth = desiredWidth;
        this.desiredHeight = desiredHeight;
    }

    public DownloadImageTask(Context context, ImagesCache cache, ImageView ivImageView, int desireWidth, int desireHeight, SlideshowSplashScreen splashScreen) {
        this.context = context;
        this.cache = cache;
        this.ivImageView = ivImageView;
        this.desiredHeight = desireHeight;
        this.desiredWidth = desireWidth;
        _splashScreen = splashScreen;
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        imageUrl = params[0];
        i++;
        Log.d(TAG, "doInBackground: imageUrl"+imageUrl);
        return getImage(imageUrl);
    }


    @Override
    protected void onPostExecute(Bitmap results) {
        super.onPostExecute(results);

        if (results != null) {
            //Bitmap result = Bitmap.createScaledBitmap(results, 200, 200, true);
            Bitmap result = results;
            if (result.getConfig() != null) {
                cache.addImageToWarehouse(imageUrl, result);
                SlideshowSharedPreferences.SaveBitmap(context, result, imageUrl);
                i++;
                if (ivImageView != null) {
                    ivImageView.setImageBitmap(result);
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            } else {
                SlideshowSplashScreen.CountValue--;
            }


        }else {
            SlideshowSplashScreen.CountValue--;
        }
        Log.d(TAG, "onPostExecute: " + ImagesCache.imagesWarehouse.putCount() + "==" + (SlideshowSplashScreen.CountValue));
        if ((ImagesCache.imagesWarehouse.putCount() == SlideshowSplashScreen.CountValue)) {
            SlideshowSharedPreferences.IMAGES_SAVED(context, true);
            Intent i = new Intent(_splashScreen, SlideShowActivity.class);
            Log.d(TAG, "onPostExecute: _splashScreen.startActivity");
            _splashScreen.startActivity(i);
            _splashScreen.finish();
        }
    }


    private Bitmap getImage(String imageUrl) {
            int imageWidth = 0;
            int imageHeight = 0;
            if (cache.getImageFromWarehouse(imageUrl) == null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inJustDecodeBounds = true;
                options.inSampleSize = inSampleSize;
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream stream = connection.getInputStream();
                    image = BitmapFactory.decodeStream(stream, null, options);
                    imageWidth = options.outWidth;
                    imageHeight = options.outHeight;
                    if (imageWidth > desiredWidth || imageHeight > desiredHeight) {
                        System.out.println("imageWidth:" + imageWidth + ", imageHeight:" + imageHeight);
                        inSampleSize = inSampleSize + 2;
                        getImage(imageUrl);
                    } else {
                        options.inJustDecodeBounds = false;
                        connection = (HttpURLConnection) url.openConnection();
                        stream = connection.getInputStream();
                        image = BitmapFactory.decodeStream(stream, null, options);
                        return image;
                    }
                } catch (Exception e) {
                    Log.e("getImage", e.toString());
                    image = null;
                }
        }
        if (image != null) {
            return image;
        } else {
            return null;
        }
    }

}