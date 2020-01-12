package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.graphics.Color;

import java.util.List;

public class Backgrounds
{
    //private int duration;
    private int opacity;
    private String transition;
    private String credits;
    private String type;
    private String color = null;
    private String url = null;
    private List<String> colors = null;

    public String getcredits(){
        return this.credits;
    }
    public int getDuration(int speedMilliSecondsOffset){
        return 10000;
    }
    /*public void setDuration(int duration){
        this.duration = duration;
    }
    public int getDuration(int speedMilliSecondsOffset){
        int sh_min_duration_millisec = MyApplication.getAppContext().getResources().getInteger(R.integer.sh_min_duration_millisec);
        if((this.duration + speedMilliSecondsOffset) < sh_min_duration_millisec){
            return sh_min_duration_millisec;
        }
        return this.duration + speedMilliSecondsOffset;
    }*/
    public void setOpacity(int opacity){
        this.opacity = opacity;
    }
    public int getOpacity(){
        return this.opacity;
    }
    public void setTransition(String transition){
        this.transition = transition;
    }
    public String getTransition(){
        return this.transition;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public void setColors(List<String> colors){
        this.colors = colors;
    }
    public List<String> getColors(){
        return this.colors;
    }
    public int[] getIntColors(){
        if(this.colors == null || this.colors.isEmpty()){
            int[] intColors = new int[1];
            intColors[0] = Color.parseColor("#FFFFFF");
            return intColors;
        }
        int[] intColors = new int[this.colors.size()];
        for (int i=0; i<this.colors.size(); i++) {
            intColors[i] = getColorWithAlpha(Color.parseColor(this.colors.get(i)),
                    this.opacity);
        }
        return intColors;
    }

    private int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

    public String getUrl() {
        return url;
    }
    public Boolean getImageTileMode() {
        if(this.type != null && this.type.equals("tile")) {
            return true;
        }
        return false;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}