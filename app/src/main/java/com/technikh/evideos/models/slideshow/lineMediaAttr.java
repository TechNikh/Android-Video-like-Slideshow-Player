package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

public class lineMediaAttr
{
   // private String type;
    private String alignment;
    private String width = "";
    private String height = "";
    private Effects effects;
    private ImageAudio audio = null;
    //private String url;

    /*public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }*/
    public String getWidth(){
        return this.width;
    }
    public String getHeight(){
        return this.height;
    }
    public void setAlignment(String alignment){
        this.alignment = alignment;
    }
    public String getAlignment(){
        return this.alignment;
    }
    public void setEffects(Effects effects){
        this.effects = effects;
    }
    public Effects getEffects(){
        return this.effects;
    }
    public ImageAudio getImageAudio(){
        return this.audio;
    }/*
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }*/
}