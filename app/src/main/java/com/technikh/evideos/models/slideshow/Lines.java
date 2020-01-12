package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import java.util.List;

public class Lines
{
    private String text;
    private String uuid;
    private Style style;
    private Effects effects;
    private List<lineMedia> media;

    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    public String getUUID(){
        return this.uuid;
    }
    public void setStyle(Style style){
        this.style = style;
    }
    public Style getStyle(){
        return this.style;
    }
    public void setEffects(Effects effects){
        this.effects = effects;
    }
    public Effects getEffects(){
        return this.effects;
    }
    public void setImages(List<lineMedia> images){
        this.media = images;
    }
    public List<lineMedia> getImages(){
        return this.media;
    }
}