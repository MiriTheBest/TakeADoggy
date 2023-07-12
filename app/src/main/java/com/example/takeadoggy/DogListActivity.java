package com.example.takeadoggy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takeadoggy.Adapters.DogAdapter;
import com.example.takeadoggy.Interfaces.DogCallback;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.Utilities.FirebaseHelper;
import com.example.takeadoggy.Utilities.SignalGenerator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class DogListActivity extends AppCompatActivity {

    private String userName = "";

    private RecyclerView main_LST_dogs;

    private ArrayList<Dog> dogListFromFirebase = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        findViews();
        initViews();
        setUserName();

    }

    private void initViews() {
        dogListFromFirebase = MainActivity.dogsFromDB;
        DogAdapter dogAdapter = new DogAdapter(this, dogListFromFirebase);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_dogs.setLayoutManager(linearLayoutManager);
        main_LST_dogs.setAdapter(dogAdapter);
        dogAdapter.setDogCallback(new DogCallback() {
            @Override
            public void favoriteClicked(Dog dog, int position) {
                dog.setFavourite(!dog.isFavourite());
                if(dog.isFavourite()) {
                    SignalGenerator.getInstance().playBarkSound();
                    addDogToFavArray(dog);
                }
                else
                    deleteFromFavArray(dog);

                main_LST_dogs.getAdapter().notifyItemChanged(position);
            }

            @Override
            public void adoptClicked(int position) {
                Intent adoptIntent = new Intent(DogListActivity.this, AdoptionTestActivity.class);
                adoptIntent.putExtra(AdoptionTestActivity.ADOPT_KEY, position);
                startActivity(adoptIntent);
                finish();
            }
        });

    }

    private void deleteFromFavArray(Dog dog) {
        FirebaseHelper.deleteFromFavArray(this.userName, dog);

    }

    private void addDogToFavArray(Dog dog) {
        FirebaseHelper.addToFavArray(this.userName, dog);

    }

    private void findViews() {
        main_LST_dogs = findViewById(R.id.main_LST_dogs);
    }


    public void setUserName() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String name = currentUser.getDisplayName();;
            this.userName = name;
        }
    }


}
