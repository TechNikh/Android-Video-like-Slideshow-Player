package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import java.util.List;

public class ShQuestions
{
    private String question;
    private List<ShQuestionOption> options;
    public List<ShQuestionOption> getShQuestionOptions(){
        return this.options;
    }
    public String getQuestion(){
        return this.question;
    }

    public String[] getShQuestionOptionsArray(){
        String[] sOptions = new String[this.options.size()];
        for (int i = 0; i<this.options.size(); i++){
            sOptions[i] = this.options.get(i).getMessage();
        }
        return sOptions;
    }
}