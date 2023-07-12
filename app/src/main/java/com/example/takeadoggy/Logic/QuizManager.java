package com.example.takeadoggy.Logic;


import com.example.takeadoggy.MainActivity;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.Models.Question;
import com.example.takeadoggy.Utilities.FirebaseHelper;


import java.util.ArrayList;

public class QuizManager {

    private final int answerSize = 4;
    private String bestMatch = "";
    private String bestName = "";
    private int currentQuestion;
    private int currentAnswer;

    private ArrayList<Dog> dogs;

    private ArrayList<Question> questions = new ArrayList<>();
    private int[] points;
    //here points will be checked as per index:
    //0 - calm, 1 - trained, 2 - friendly, 3 - active
    public QuizManager() {
        questions = QuizDataManager.getQuestions();
        this.currentQuestion = 0;
        this.currentAnswer = 0;
        points = new int[answerSize];
    }


    public Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }


    public boolean isQuizEnded() {
        return currentQuestion == questions.size();
    }

    public void checkAnswer(int chosenNum) {

        points[chosenNum]++;
        currentQuestion++;
    }


    public void checkName() {
        this.dogs = MainActivity.dogsFromDB;
        for (int i = 0; i < dogs.size(); i++) {
                    Dog dog = this.dogs.get(i);
                    String attr1 = dog.getAttribute1();
                    String attr2 = dog.getAttribute2();

                    setBestName(attr1, attr2, dog);
                }
    }

    private void setBestName(String attr1, String attr2, Dog dog) {
        boolean isEqual1 = attr1.equalsIgnoreCase(this.bestMatch);
        boolean isEqual2 = attr2.equalsIgnoreCase(this.bestMatch);
        if (isEqual1 || isEqual2)
            this.bestName = dog.getName();
    }

    public String getBestMatch() {
        int indexMatch = findMax();

        if (indexMatch == 0)
            this.bestMatch = "calm";
        else if (indexMatch == 1)
            this.bestMatch = "trained";
        else if (indexMatch == 2)
            this.bestMatch = "friendly";
        else
            this.bestMatch = "active";

        return this.bestMatch;
    }

    private int findMax() {
        int max = 0;
        int index = 0;
        for (int i = 0; i < answerSize; i++) {
            if(points[i] > max) {
                max = points[i];
                index = i;
            }
        }
        return index;
    }

    public String getBestName() {
        return this.bestName;
    }
}
