package com.rapidbizapps.chartanimations;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by svuppala on 08-09-2016.
 */
public class CircleProgressActivity extends Activity {

    CircleProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.circle_progress_layout);

        circleProgressBar = (CircleProgressBar) findViewById(R.id.custom_progressBar);


        circleProgressBar.setColor(getResources().getColor(R.color.colorAccent));
        circleProgressBar.setProgressWithAnimation(40);

    }
}
