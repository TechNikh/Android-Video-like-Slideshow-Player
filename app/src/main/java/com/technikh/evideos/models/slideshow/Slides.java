package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import java.util.List;

public class Slides
{
    private String text_allignment;
    private String music_mood;
    private List<Backgrounds> backgrounds;
    private List<Lines> lines;

    public void setText_allignment(String text_allignment){
        this.text_allignment = text_allignment;
    }
    public void setMusic_Mood(String music_mood){
        this.music_mood = music_mood;
    }
    public String getText_allignment(){
        return this.text_allignment;
    }
    public String getMusic_Mood(){
        return this.music_mood;
    }
    public void setBackgrounds(List<Backgrounds> backgrounds){
        this.backgrounds = backgrounds;
    }
    public List<Backgrounds> getBackgrounds(){
        return this.backgrounds;
    }
    public void setLines(List<Lines> lines){
        this.lines = lines;
    }
    public List<Lines> getLines(){
        return this.lines;
    }
}