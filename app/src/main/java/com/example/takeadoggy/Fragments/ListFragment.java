package com.example.takeadoggy.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeadoggy.Adapters.FavDogAdapter;
import com.example.takeadoggy.Interfaces.OnItemClickListener;
import com.example.takeadoggy.Interfaces.SendClickCallback;
import com.example.takeadoggy.MainActivity;
import com.example.takeadoggy.Models.Dog;
import com.example.takeadoggy.R;

import java.util.ArrayList;

public class ListFragment extends Fragment implements OnItemClickListener {

    public ListFragment() {
        // Required empty public constructor
    }
    private SendClickCallback callBack_SendClick;
    private RecyclerView fav_LST_dogs;
    public static ArrayList<Dog> favDogsList= new ArrayList<>();
    private String userName = "";
    private FavDogAdapter favDogsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        initView();

        return view;
    }

    private void initView() {
            favDogsList = MainActivity.favDogsFromDB;
            favDogsAdapter = new FavDogAdapter(getContext(), favDogsList, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            fav_LST_dogs.setLayoutManager(linearLayoutManager);
            fav_LST_dogs.setAdapter(favDogsAdapter);
    }

    private void findViews(View view) {
        fav_LST_dogs = view.findViewById(R.id.main_LST_fav_dogs);
    }


    public void setCallBack(SendClickCallback callBack_sendClick) {
        this.callBack_SendClick = callBack_sendClick;
    }

    @Override
    public void itemClicked(int position) {
        if(callBack_SendClick != null){
            callBack_SendClick.dogChosen(favDogsList.get(position));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Fetch the latest array of favorite dogs from the database
        initView();
    }
}