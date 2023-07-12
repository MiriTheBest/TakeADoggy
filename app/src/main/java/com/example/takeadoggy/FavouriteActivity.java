package com.example.takeadoggy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.takeadoggy.Fragments.ListFragment;
import com.example.takeadoggy.Interfaces.SendClickCallback;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.Fragments.MapsFragment;

public class FavouriteActivity extends AppCompatActivity {
    private ListFragment listFragment;
    private MapsFragment mapFragment;

    SendClickCallback callBack_SendClick = new SendClickCallback() {
        @Override
        public void dogChosen(Dog dog) {
            mapFragment.zoomOnUserLocation(dog.getLatitude(), dog.getLongitude());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        initFragments();

        beginTransactions();
    }


    private void initFragments() {
        mapFragment = new MapsFragment();
        listFragment = new ListFragment();
        listFragment.setCallBack(callBack_SendClick);
    }
    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_list, listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mapFragment).commit();
    }
}
