package com.example.takeadoggy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.takeadoggy.Logic.QuizDataManager;
import com.example.takeadoggy.Logic.QuizManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private MaterialTextView quiz_LBL_question;
    private MaterialTextView quiz_LBL_description;
    private MaterialButton[] quiz_BTN_options;
    private QuizManager quizManager;
    private LottieAnimationView lottie_ANIM_lottie;
    private Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        findViews();
        quizManager = new QuizManager();
        refreshUI();
        setAnswersClickListeners();
    }

    private void setAnswersClickListeners() {
        for (MaterialButton mb : quiz_BTN_options) {
            mb.setOnClickListener(v -> clicked(mb.getText().toString()));
        }
    }

    private void clicked(String chosenAnswer) {
        char ch = chosenAnswer.charAt(0);
        int numForCheck = Character.getNumericValue(ch);
        numForCheck--;
        quizManager.checkAnswer(numForCheck);
        refreshUI();
    }

    private void openScoreScreen(String bestMatch, String name) {
        Intent resultIntent = new Intent(this, QuizResultActivity.class);
        resultIntent.putExtra(QuizResultActivity.KEY_RESULT, bestMatch);
        resultIntent.putExtra(QuizResultActivity.KEY_NAME, name);
        startActivity(resultIntent);
        finish();
    }

    private void refreshUI() {
        if (quizManager.isQuizEnded()){
            String bestMatch = quizManager.getBestMatch();
            quizManager.checkName();
            String name = quizManager.getBestName();
            openScoreScreen(bestMatch, name);
        }else{
            String question = quizManager.getCurrentQuestion().getQuestion();
            quiz_LBL_question.setText(question);
            String description = quizManager.getCurrentQuestion().getDescription();
            quiz_LBL_description.setText(description);
            ArrayList<String> answers = new ArrayList<>(Arrays.asList(quizManager.getCurrentQuestion().getAnswers()));
            for (int i = 0; i < answers.size(); i++) {
                quiz_BTN_options[i].setText(answers.get(i));
            }
            int[] lotties = QuizDataManager.getLotties();
            int index = random.nextInt(8);
            lottie_ANIM_lottie.setAnimation(lotties[index]);
            lottie_ANIM_lottie.playAnimation();
        }
    }

    private void findViews() {
        quiz_BTN_options = new MaterialButton[]{
                findViewById(R.id.quiz_BTN_option1),
                findViewById(R.id.quiz_BTN_option2),
                findViewById(R.id.quiz_BTN_option3),
                findViewById(R.id.quiz_BTN_option4)};
        quiz_LBL_question = findViewById(R.id.quiz_LBL_question);
        quiz_LBL_description = findViewById((R.id.quiz_LBL_description));
        lottie_ANIM_lottie = findViewById(R.id.lottie_ANIM_lottie2);
    }
}
