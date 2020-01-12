package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

public class ImageAudio
{
    private String url;
    //private int duration;

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    /*public void setDuration(int duration){
        this.duration = duration;
    }
    public int getDuration(int speedMilliSecondsOffset){
        int sh_min_duration_millisec = MyApplication.getAppContext().getResources().getInteger(R.integer.sh_min_duration_millisec);
        if((this.duration + speedMilliSecondsOffset) < sh_min_duration_millisec){
            return sh_min_duration_millisec;
        }
        return this.duration+speedMilliSecondsOffset;
    }*/
}