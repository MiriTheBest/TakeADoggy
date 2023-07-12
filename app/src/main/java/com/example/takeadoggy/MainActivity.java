package com.example.takeadoggy;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.Utilities.FirebaseHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView lottie_ANIM_lottie;
    private MaterialTextView menu_welcome;
    private MaterialButton menu_catalog;
    private MaterialButton menu_favourite;
    private MaterialButton menu_quiz;
    private MaterialButton menu_logout;

    public static ArrayList<Dog> dogsFromDB = new ArrayList<>();
    public static ArrayList<Dog> favDogsFromDB = new ArrayList<>();
    private Intent intent;
    private String name = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            name = currentUser.getDisplayName();
            menu_welcome.setText("Welcome to TakeADoggy, " + name);
        }

        lottie_ANIM_lottie.resumeAnimation();
        lottie_ANIM_lottie.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

        getDogsFromDB();
        getFavDogsFromDB();

        intent = new Intent(this, MainActivity.class);
        menu_catalog.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, DogListActivity.class);
            startActivity(intent);
        });

        menu_favourite.setOnClickListener(v -> {
            if(favDogsFromDB != null) {
                intent = new Intent(MainActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });

        menu_quiz.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        menu_logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();});
    }

    private void getFavDogsFromDB() {
        FirebaseHelper.loadUserFavDogsFromDB(this.name, new FirebaseHelper.Callback_FavoriteDogs() {
            @Override
            public void favDataReady(ArrayList<Dog> favDogs) {
                setFavDogListFromFirebase(favDogs);
            }
        });
    }

    private void setFavDogListFromFirebase(ArrayList<Dog> favDogs) {
        favDogsFromDB = favDogs;
    }

    private void getDogsFromDB() {
        FirebaseHelper.loadDogListFromDb(new FirebaseHelper.Callback_Doglist() {
            @Override
            public void dataReady(ArrayList<Dog> dogs) {
                setDogListFromFirebase(dogs);
            }
        });
    }

    private void setDogListFromFirebase(ArrayList<Dog> dogs) {
        dogsFromDB = dogs;
    }


    private void findViews() {
        lottie_ANIM_lottie = findViewById(R.id.lottie_ANIM_lottie);
        menu_welcome = findViewById(R.id.menu_welcome);
        menu_catalog = findViewById(R.id.menu_button1);
        menu_favourite = findViewById(R.id.menu_button2);
        menu_quiz = findViewById(R.id.menu_button3);
        menu_logout = findViewById(R.id.menu_BTN_logout);
    }
}
