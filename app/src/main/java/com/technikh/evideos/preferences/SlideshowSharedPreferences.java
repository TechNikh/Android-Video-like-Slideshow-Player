package com.technikh.evideos.preferences;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

//import com.example.dynamictextanimations.Models.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.technikh.evideos.models.slideshow.SlideshowJsonModel;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class SlideshowSharedPreferences {
    private static android.content.SharedPreferences mPrefs;
    private static String IMAGES_SAVE = "IMAGES_SAVE";
    private static String TAG = "SharedPreferences";

    private static String encodeTobase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
    private static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static void SaveBitmap(Context context, Bitmap bitmap, String Key) {
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(Key, encodeTobase64(bitmap));
        editor.apply();
    }

    public static Bitmap getBitmapFromStringCache(Context context,String Key) {
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        String base64 = mPrefs.getString(Key,"");
        return decodeBase64(base64);
    }

    public static void IMAGES_SAVED(Context context,boolean isSaved){
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(IMAGES_SAVE, isSaved);
        editor.apply();
    }

    public static Boolean GET_SAVE_IMAGES(Context context){
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
       return mPrefs.getBoolean(IMAGES_SAVE,false);
    }

    static public void setPreferenceObject(Context context, String json_string, String key) {
        /**** storing object in preferences  ****/
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = mPrefs.edit();

        //Gson gson = new Gson();
        //String jsonObject = gson.toJson(modal);
        editor.putString(key, json_string);
        editor.commit();

    }

    static public void setPreferenceObject(Context context, Object modal,String key) {
        /**** storing object in preferences  ****/
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = mPrefs.edit();

        Gson gson = new Gson();
        String jsonObject = gson.toJson(modal);
        editor.putString(key, jsonObject);
        editor.commit();

    }

    static public Object getPreferenceObjectJson(Context context,String key) {

        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        /**** get user data    *****/
        String json = mPrefs.getString(key, "");
        Log.d(TAG, "getPreferenceObjectJson: "+json);
        try {
            Gson gson = new Gson();
            SlideshowJsonModel selectedUser = gson.fromJson(json, SlideshowJsonModel.class);
            return selectedUser;
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getPreferenceObjectJson: "+json);
            return null;
        }
    }

    static public HashMap<String, Boolean> getPreferenceObjectDownloadStatus(Context context) {
        HashMap<String, Boolean> downloadStatus=new HashMap<String, Boolean>();
        java.lang.reflect.Type type = new TypeToken<HashMap<String, Boolean>>(){}.getType();
        mPrefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        /**** get user data    *****/
        String json = mPrefs.getString("downloadStatus", "");
        Gson gson=new Gson();
        downloadStatus=gson.fromJson(json, type);
        return  downloadStatus;
    }

}
