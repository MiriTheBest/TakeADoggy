package com.example.takeadoggy;

import android.app.Application;

import com.example.takeadoggy.Utilities.ImageLoader;
import com.example.takeadoggy.Utilities.SignalGenerator;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SignalGenerator.init(this);
        ImageLoader.initImageLoader(this);
    }
}
