package com.example.takeadoggy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.takeadoggy.Logic.AdoptionManager;
import com.example.takeadoggy.Models.Dog;
import com.google.android.material.button.MaterialButton;


public class AdoptionTestActivity extends AppCompatActivity {

    public static final String ADOPT_KEY = "ADOPT_KEY";
    private int position;

    private ToggleButton[] answer_check;
    private MaterialButton test_check;
    private TextView[] questions;
    private boolean[] toggleButtonStates = new boolean[13];
    private AdoptionManager adoptionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);
        findViews();

        for (int i = 0; i < answer_check.length; i++) {
            final int index = i; // Capture the current index in a final variable

            answer_check[i].setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    answer_check[index].setText("Yes");
                    toggleButtonStates[index] = true;
                } else {
                    answer_check[index].setText("No");
                    toggleButtonStates[index] = false;
                }
            });
        }

        Intent intent = getIntent();
        if(intent != null) {
            position = intent.getIntExtra(AdoptionTestActivity.ADOPT_KEY, 0);
        }

        Dog dog = MainActivity.dogsFromDB.get(position);
        adoptionManager = new AdoptionManager(dog.getBreed(), dog.getSize(), dog.getAttribute1(), dog.getAttribute2(),
                dog.getRestriction1(), dog.getRestriction2());

        test_check.setOnClickListener(v -> getResult());
    }

    private void getResult() {
        int result = adoptionManager.getResult(toggleButtonStates);
        Intent resultIntent = new Intent(this, AdoptionResultActivity.class);
        resultIntent.putExtra(QuizResultActivity.KEY_RESULT, result);
        startActivity(resultIntent);
        finish();

    }

    private void findViews() {

        answer_check = new ToggleButton[] {findViewById(R.id.adopt_LBL_answer1), findViewById(R.id.adopt_LBL_answer2),
                findViewById(R.id.adopt_LBL_answer3), findViewById(R.id.adopt_LBL_answer4),findViewById(R.id.adopt_LBL_answer5),
                findViewById(R.id.adopt_LBL_answer6), findViewById(R.id.adopt_LBL_answer7), findViewById(R.id.adopt_LBL_answer8),
                findViewById(R.id.adopt_LBL_answer9), findViewById(R.id.adopt_LBL_answer10), findViewById(R.id.adopt_LBL_answer11),
                findViewById(R.id.adopt_LBL_answer12), findViewById(R.id.adopt_LBL_answer13)};

        questions = new TextView[] {findViewById(R.id.adopt_LBL_question1), findViewById(R.id.adopt_LBL_question2),
                findViewById(R.id.adopt_LBL_question3), findViewById(R.id.adopt_LBL_question4),findViewById(R.id.adopt_LBL_question5),
                findViewById(R.id.adopt_LBL_question6), findViewById(R.id.adopt_LBL_question7), findViewById(R.id.adopt_LBL_question8),
                findViewById(R.id.adopt_LBL_question9), findViewById(R.id.adopt_LBL_question10), findViewById(R.id.adopt_LBL_question11),
                findViewById(R.id.adopt_LBL_question12), findViewById(R.id.adopt_LBL_question13)};

        test_check = findViewById(R.id.adopt_BTN_check);
    }
}
