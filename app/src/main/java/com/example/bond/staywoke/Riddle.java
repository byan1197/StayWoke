package com.example.bond.staywoke;

import java.util.HashMap;

/**
 * Created by praiyon on 13/07/17.
 */

public class Riddle {
    public HashMap questions;
    public HashMap answers;
    public HashMap options;
    String[] one ;
    String[] two ;
    String[] three ;
    String[] four ;
    String[] five ;
    String[] six ;
    String[] seven ;
    public Riddle(){


        one = new String[4];
        two = new String[4];
        three = new String[4];
        four = new String[4];
        five = new String[4];
        six = new String[4];
        seven = new String[4];
        one[0] = "William and Elizabeth";
        one[1] = "Joseph and Catherine";
        one[2] = "John and Mary";
        one[3] = "George and Anne";
        two[0] = "Charlie Brown";
        two[1] = "Bugs Bunny";
        two[2] = "Mickey Mouse";
        two[3] = "Fred Flintstone";
        three[0] = "cocker spaniel";
        three[1] = "German shepherd";
        three[2] = "Labrador retriever";
        three[3] = "poodle";
        four[0] = "8";
        four[1] = "18";
        four[2] = "38";
        four[3] = "58";
        five[0] = "home computer";
        five[1] = "compact disk player";
        five[2] = "cordless phone";
        five[3] = "dishwasher";
        six[0] = "10";
        six[1] = "15";
        six[2] = "31";
        six[3] = "51";
        seven[0] = "Doom";
        seven[1] = "Day";
        seven[2] = "Dwight (Eisenhower)";
        seven[3] = "Dunkirk";
        questions = new HashMap();
        answers = new HashMap();
        options= new HashMap();
        questions.put("0","In the year 1900 in the U.S. what were the most popular first names given to boy and girl babies?");
        questions.put("1","Which of these characters turned 40 years old in 1990?");
        questions.put("2","During the 1980s for six consecutive years what breed of dog was the most popular in the U.S.?");
        questions.put("3","In 1990, in what percentage of U.S. married couples did the wife earn more money than the husband?");
        questions.put("4","Which of the following items was owned by the fewest U.S. homes in 1990?");
        questions.put("5","In 1985, five percent of U.S. households had telephone answering machines. By 1990 what percentage of homes had answering machines?");
        questions.put("6","What did the \"D\" in \"D-Day\" stand for?");
        answers.put("0","John and Mary");
        answers.put("1","Charlie Brown");
        answers.put("2","Cocker Spaniel");
        answers.put("3","18");
        answers.put("4","compact Disk Player");
        answers.put("5","31");
        answers.put("6","Day");
        options.put("0",one);
        options.put("1",two);
        options.put("2",three);
        options.put("3",four);
        options.put("4",five);
        options.put("5",six);
        options.put("6",seven);
    }
    /*
    public static void main (String [] args){
        com.example.bond.staywoke.Riddle riddle = new com.example.bond.staywoke.Riddle();
        String[] test = (String[]) riddle.options.get("1");
        System.out.println(riddle.questions.get("1"));
        System.out.println((String[]) riddle.options.get("1"));
        //System.out.println(test[0]);
    }
    */
}

