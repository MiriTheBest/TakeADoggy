package com.example.takeadoggy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdoptionResultActivity extends AppCompatActivity {

    public static final String KEY_RESULT = "KEY_RESULT";

    private MaterialTextView result_LBL_message;
    private MaterialTextView result_LBL_message2;
    private MaterialButton result_BTN_toMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoptionresult);

        findViews();

        result_BTN_toMenu.setOnClickListener(view -> goToMenu());

        Intent prevIntent = getIntent();
        int result = prevIntent.getIntExtra(KEY_RESULT, 0);

        if (result == 1) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();

            if (currentUser != null) {
                String email = currentUser.getEmail();
                String name = currentUser.getDisplayName();

                result_LBL_message.setText("Congratulations, " + name + "! You can adopt this dog!");
                result_LBL_message2.setText("We will send you a message here: " + email);
            } else {
                result_LBL_message.setText("You need to authorize!");
                Log.d("AdoptionResultActivity","No logged-in user");
            }



        }
        else
            result_LBL_message.setText("Unfortunately, you can not adopt this dog! But we have many more in need of adoption! please check our catalog");
    }

    private void goToMenu() {
        Intent menuIntent = new Intent(AdoptionResultActivity.this, MainActivity.class);
        startActivity(menuIntent);
        finish();
    }

    private void findViews() {
        result_LBL_message = findViewById(R.id.result_LBL_line);
        result_LBL_message2 = findViewById(R.id.result_LBL_line2);
        result_BTN_toMenu = findViewById(R.id.result_BTN_back2);
    }
}
