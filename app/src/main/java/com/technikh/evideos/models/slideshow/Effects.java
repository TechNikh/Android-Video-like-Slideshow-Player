package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import com.technikh.evideos.R;
import com.technikh.evideos.app.MyApplication;

public class Effects
{
    private String enter;
    private String exit;
    private String animate ="";
    private int duration;

    public void setEnter(String enter){
        this.enter = enter;
    }
    public String getEnter(){
        return this.enter;
    }
    public String getAnimate(){
        return this.animate;
    }
    public void setExit(String exit){
        this.exit = exit;
    }
    public String getExit(){
        return this.exit;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public int getDuration(int speedMilliSecondsOffset){
        int calculated_milli_sec = this.duration+speedMilliSecondsOffset;
        int sh_min_duration_millisec = MyApplication.getAppContext().getResources().getInteger(R.integer.sh_min_duration_millisec);
        if(calculated_milli_sec < sh_min_duration_millisec){
            return sh_min_duration_millisec;
        }
        return calculated_milli_sec;
    }
}