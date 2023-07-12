package com.example.takeadoggy.Logic;

import java.util.ArrayList;

public class AdoptionManager {

    private String size = "";
    private String breed = "";
    private ArrayList<String> attributes = new ArrayList<>();
    private ArrayList<String> restrictions = new ArrayList<>();

    public AdoptionManager(String breed, String size, String attr1, String attr2, String restr1, String restr2) {
        this.size = size;
        this.breed = breed;
        this.attributes.add(attr1);
        this.attributes.add(attr2);
        this.restrictions.add(restr1);
        this.restrictions.add(restr2);
    }

    public int getResult(boolean[] answers) {
        if(!answers[0] || !answers[1]) //if you don't want to invest in your dog, you can't adopt
            return 0;

        boolean isTrained = true;
        if(this.restrictions.get(0).equals("not trained") || this.restrictions.get(1).equals("not trained"))
            isTrained = false;


        if(!answers[2] && !isTrained)
            return 0; // if dog is not trained and you don't have experience, you can't adopt
        if(!answers[3] && this.breed.equalsIgnoreCase("Pit Bull"))
            return 0; //you need experience for this kind of dog
        if(!answers[4] && this.size.equalsIgnoreCase("Big"))
            return 0; //big dogs need garden
        if(answers[5] && !this.size.equalsIgnoreCase("Small"))
            return 0; //small appartments for small dogs only
        if(answers[7] && (this.restrictions.get(0).equalsIgnoreCase("no kids") || this.restrictions.get(1).equalsIgnoreCase("no kids")))
            return 0; // if you have kids and you chose dog with the restriction, you can't adopt
        if(answers[8] && (this.restrictions.get(0).equalsIgnoreCase("no cats") || this.restrictions.get(1).equalsIgnoreCase("no cats")))
            return 0; //same about cats
        if(answers[9] && (this.restrictions.get(0).equalsIgnoreCase("no dogs") || this.restrictions.get(1).equalsIgnoreCase("no dogs")))
            return 0; //same about dogs


        boolean isCalm = false;
        if (this.attributes.get(0).equalsIgnoreCase("calm") || this.attributes.get(1).equalsIgnoreCase("calm"))
            isCalm = true;

        boolean isFriendly = false;
        if (this.attributes.get(0).equalsIgnoreCase("friendly") || this.attributes.get(1).equalsIgnoreCase("friendly"))
            isFriendly = true;

        if(answers[10] && isFriendly)
            return 0; //if the dog loves human attention and there is no one in the house most of the time, you can't adopt
        if(answers[11] && isCalm)
            return 0; //if dog is not active and you need one, you can't adopt
        if(answers[12] && !isCalm)
            return 0;

        return 1; //you can adopt
    }
}
