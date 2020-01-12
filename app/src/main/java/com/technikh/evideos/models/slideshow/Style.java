package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.graphics.Color;

import java.util.List;

public class Style
{
    private String color;
    private String bgcolor;
    private int opacity;
    private int size;
    private List<String> colors;
    private List<String> bgcolors;

    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public void setBgcolor(String bgcolor){
        this.bgcolor = bgcolor;
    }
    public String getBgcolor(){
        return this.bgcolor;
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
            intColors[0] = Color.parseColor("#000000");
            return intColors;
        }
        int[] intColors = new int[this.colors.size()];
        for (int i=0; i<this.colors.size(); i++) {
            intColors[i] = Color.parseColor(this.colors.get(i));
        }
        return intColors;
    }
    public void setBgcolors(List<String> bgcolors){
        this.bgcolors = bgcolors;
    }
    public List<String> getBgcolors(){
        return this.bgcolors;
    }
    public int[] getBgIntColors(){
        if(this.bgcolors == null || this.bgcolors.isEmpty()){
            int[] intColors = new int[1];
            intColors[0] = Color.parseColor("#FFFFFF");
            return intColors;
        }
        int[] intColors = new int[this.bgcolors.size()];
        for (int i=0; i<this.bgcolors.size(); i++) {
            intColors[i] = getColorWithAlpha(Color.parseColor(this.bgcolors.get(i)),
                    this.opacity);
        }
        return intColors;
    }

    public void setOpacity(int opacity){
        this.opacity = opacity;
    }
    public int getOpacity(){
        return this.opacity;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
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
}