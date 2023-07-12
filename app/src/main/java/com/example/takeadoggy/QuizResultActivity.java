package com.example.takeadoggy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class QuizResultActivity extends AppCompatActivity {
    public static final String KEY_RESULT = "KEY_RESULT";
    public static final String KEY_NAME = "KEY_NAME";

    private MaterialTextView result_LBL_message;
    private MaterialTextView result_LBL_dog;
    
    private MaterialButton result_BTN_toMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizresult);
        
        findViews();
        
        result_BTN_toMenu.setOnClickListener(view -> goToMenu());
        
        Intent prevIntent = getIntent();
        String result = prevIntent.getStringExtra(KEY_RESULT);
        String name = prevIntent.getStringExtra(KEY_NAME);
        result_LBL_message.setText("Congratulations! It seems that you need " + result + " dog!");

        if (name.isEmpty())
            result_LBL_dog.setText("Unfortunately, currently we don't have a dog like that... Please check again later!");
        else
            result_LBL_dog.setText("We have a match! " + name + " can be your perfect dog! Please have a look in a catalog to check it out");
    }

    private void goToMenu() {
        Intent menuIntent = new Intent(QuizResultActivity.this, MainActivity.class);
        startActivity(menuIntent);
        finish();
    }

    private void findViews() {
        result_LBL_message = findViewById(R.id.result_LBL_message);
        result_LBL_dog = findViewById(R.id.result_LBL_foundDog);
        result_BTN_toMenu = findViewById(R.id.result_BTN_back1);
    }
}