package com.example.takeadoggy.Utilities;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.VibrationEffect;
import android.widget.Toast;

import com.example.takeadoggy.R;

public class SignalGenerator {

    private static SignalGenerator instance;
    private Context context;
    private static SoundPool soundPool;
    private static int barkSoundID;
    private SignalGenerator(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SignalGenerator(context);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
                soundPool = new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttributes).build();
            }
            else {
                soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            }
            barkSoundID = soundPool.load(context, R.raw.dog_bark, 1);
        }
    }

    public static SignalGenerator getInstance() {
        return instance;
    }

    public void toast(String text,int length){
        Toast
                .makeText(context,text,length)
                .show();
    }


    public void playBarkSound() {
        soundPool.play(barkSoundID, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}
