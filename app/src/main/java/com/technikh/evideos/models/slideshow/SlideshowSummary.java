package com.technikh.evideos.models.slideshow;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import java.util.List;

public class SlideshowSummary
{
    private List<ShQuestions> questions;
    public List<ShQuestions> getShQuestions(){
        return this.questions;
    }

    public String[] getShQuestionsAndAnswers(){
        String[] sOptions = new String[this.questions.size()];
        for (int i = 0; i<this.questions.size(); i++){
            String q = this.questions.get(i).getQuestion();
            String ans = "";
            for (int j = 0; j<this.questions.size(); j++){
                if(this.questions.get(i).getShQuestionOptions().get(j).getAnswer()){
                    ans = this.questions.get(i).getShQuestionOptions().get(j).getMessage();
                    break;
                }
            }
            sOptions[i] = "Q: "+q+"\r\nA: "+ans;
        }
        return sOptions;
    }
}