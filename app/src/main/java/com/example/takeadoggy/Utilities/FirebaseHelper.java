package com.example.takeadoggy.Utilities;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.takeadoggy.Models.Dog;;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {

    public interface Callback_Doglist {
        void dataReady(ArrayList<Dog> dogs);
    }

    public interface Callback_FavoriteDogs {
        void favDataReady(ArrayList<Dog> favDogs);
    }

    private static ArrayList<Dog> dogs;
    private static ArrayList<Dog> favDogs;



    public static void addToFavArray(String userName, Dog dog) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference fav = db.getReference(userName);

        fav.child(dog.getName()).setValue(dog);
    }

    public static void deleteFromFavArray(String userName, Dog dog) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference fav = db.getReference(userName);

        fav.child(dog.getName()).removeValue();
    }

    public static void loadDogListFromDb(Callback_Doglist callback_doglist) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dogsRef = db.getReference("dogs");
        dogsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                dogs = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    try{
                        Dog d = childSnapshot.getValue(Dog.class);
                        dogs.add(d);
                    } catch (Exception e) {
                    }
                }
                if (callback_doglist != null)
                    callback_doglist.dataReady(dogs);

                Log.d("pttt", "Value added" + dogs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("pttt", "Failed to read the value", error.toException());
            }
        });
    }

    public static void loadUserFavDogsFromDB(String userName, Callback_FavoriteDogs callback_favoriteDogs) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference favDogsRef = db.getReference(userName);
        favDogsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                favDogs = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    try{
                        Dog d = childSnapshot.getValue(Dog.class);
                        favDogs.add(d);
                    } catch (Exception e) {
                    }
                }
                if (callback_favoriteDogs != null)
                    callback_favoriteDogs.favDataReady(favDogs);

                Log.d("pttt", "Value added" + dogs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("pttt", "Failed to read the value", error.toException());
            }
        });
    }

}
