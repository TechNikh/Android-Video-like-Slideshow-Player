package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

public class lineMedia
{
    private String caption;
    private String credits;

    private lineMediaAttr attr;
    private lineMediaFile file = null;

    public void setCaption(String caption){
        this.caption = caption;
    }
    public String getCaption(){
        return this.caption;
    }
    public String getCredits(){
        return this.credits;
    }
    public void setImage(lineMediaAttr image){
        this.attr = image;
    }
    public lineMediaAttr getMediaAttr(){
        return this.attr;
    }
    public lineMediaFile getMediaFile(){
        return this.file;
    }
}