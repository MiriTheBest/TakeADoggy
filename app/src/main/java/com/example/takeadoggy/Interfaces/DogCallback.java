package com.example.takeadoggy.Interfaces;

import com.example.takeadoggy.Models.Dog;

public interface DogCallback {
    void favoriteClicked(Dog dog, int position);

    void adoptClicked(int position);

}
