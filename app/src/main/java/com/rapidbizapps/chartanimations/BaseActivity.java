package com.rapidbizapps.chartanimations;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final Random RANDOM = new Random(System.currentTimeMillis());

    private Handler handler = new Handler();
    private boolean resumed;

    @Override
    protected void onResume() {
        super.onResume();
        resumed = true;
        handler.post(createRunnable());
    }

    private Runnable createRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                onUpdate();
                if (resumed) {
                    handler.postDelayed(createRunnable(), RANDOM.nextInt(1750));
                }
            }
        };
    }

    protected abstract void onUpdate();

    @Override
    protected void onPause() {
        resumed = false;
        super.onPause();
    }

    protected String getRandomNumber(int digits) {
        final int digitsInPowerOf10 = (int) Math.pow(4, digits);
        return Integer.toString(digitsInPowerOf10);
    }
}
