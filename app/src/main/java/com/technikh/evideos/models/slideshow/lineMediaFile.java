package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

public class lineMediaFile
{
    private lineMediaVideo video = null;
    private lineMediaAudio audio = null;
    private lineMediaImage image = null;

    public lineMediaVideo getVideo(){
        return this.video;
    }
    public lineMediaAudio getAudio(){
        return this.audio;
    }
    public lineMediaImage getImage(){
        return this.image;
    }
}