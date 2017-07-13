package com.example.bond.staywoke;

/**
 * Created by praiyon on 13/07/17.
 */

public class Question{
    String image, answer,type;
    String[] options;

    public Question(String image, String answer, String[] options, String type){
        this.image = image;
        this.answer = answer;
        this.options = options;
        this.type = type;
    }
    public String getImage(){
        return this.image;
    }
    public String getAnswer(){
        return this.answer;
    }
    public String getType(){
        return  this.type;
    }
    public String[] getOptions(){
        return this.options;
    }
    /*
    public static void main (String [] args){
        Question test = new Question("test","test", new String[] {"test","test"});
        System.out.println(test.getOptions()[0]);
    }
    */
}

