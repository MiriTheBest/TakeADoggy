package com.example.takeadoggy.Logic;

import com.example.takeadoggy.Models.Question;
import com.example.takeadoggy.R;

import java.util.ArrayList;

public class QuizDataManager {

    private static String [] all_questions = {"1) Where will you take your dog for a walk?", "2) Are you ready to train your dog?",
            "3)Do you live alone? If not, with who?", "4) Would you like to travel with your dog?", "5) Do you like playing fetch?",
            "6) Are you looking for a lap dog?", "7) How important is snuggling dog?"};

    private static String [] descriptions = {"Some dogs need to burn a lot of energy so they need to be taken to parks/forest. Some are more relaxed and prefer back garden. Social dogs love to be around other dogs.",
    "If your schedule is already busy it is better to take already trained dog. Although some dogs have rebellious personalities and have to be trained!",
    "Social dogs will be lonely if you live alone. Calm dogs will take it easily. If a dog is active or social, it is better if you have a big family",
    "Some dogs are wonderful companions for hiking/travelling. But don't forget that others are more of 'wallflowers'",
    "Playing is important, some dogs need to burn a lot of energy. Some dogs prefer stuffed toys though",
    "This is about personality. Do you want a dog that loves people? Or being around people? Or a dog that's like a baby?",
    "This goes without saying..."};

    private static String [][] answers = {new String[]{"1)My garden", "2)The city", "3)Dog parks","4)Nature"},
            new String[]{"1)Lightly", "2)I want trained dog", "3)Only if misbehaves", "4)Of course!"},
            new String[]{"1)Alone", "2)I have little children", "3)With friends", "4)I have a big family"},
            new String[]{"1)No", "2)Mostly yes", "3)Only if necessary", "4)For sure!"},
            new String[]{"1)No", "2)I prefer agility", "3)I prefer dog parks", "4)Yes!"},
            new String[]{"1)I want independent dog", "2)Partially", "3)Sure!", "4)No need"},
            new String[]{"1)Nope", "2)Occasionally", "3)Very important!", "4)I prefer playing"}};

    private static int [] lotties = {R.raw.astronaut_dog, R.raw.cute_dog, R.raw.bull_dog_doing_yoga, R.raw.dog_and_man,
            R.raw.dog_begging, R.raw.dog_reading, R.raw.dog_train, R.raw.my_dog_and_me};


    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < all_questions.length; i++) {
            Question q = new Question().setQuestion(all_questions[i]).setAnswers(answers[i]).setDescription(descriptions[i]);
            questions.add(q);
        }
        return questions;
    }

    public static int[] getLotties(){
        return lotties;
    }
}

