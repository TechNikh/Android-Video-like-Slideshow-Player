package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import java.util.List;

public class SlideshowJsonModel
{
    private List<Slides> slides;
    private String lang = "en";
    private SlideshowSummary summary = null;

    public void setSlides(List<Slides> slides){
        this.slides = slides;
    }
    public List<Slides> getSlides(){
        return this.slides;
    }
    public String getLanguage(){
        return this.lang;
    }
    public SlideshowSummary getShSummary(){
        return this.summary;
    }
}