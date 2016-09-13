package com.rapidbizapps.chartanimations;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by svuppala on 07-09-2016.
 */
public class TickerCountActivity extends Activity {

    TickerView tickerView;
    ListView lvList;
    TextView tvCount;

    private static final char[] NUMBER_LIST = TickerUtils.getDefaultListForUSCurrency();
    protected static final Random RANDOM = new Random();
    private ArrayList<DataList> dataList;
    private ListExampleAdapter adapter;
    private Context context;
    private int counter = 0;
    private LinearLayout llLayout;
    private int total = 30; // the total number


    private String[] mStrings = {"CoderzHeaven", "Android", "Google", "iPhone",
            "Windows Phone", "Samsung", "Sony"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ticker_count_layout);
        context = this;

       // Log.e("TicketCount", NUMBER_LIST + "");
        tickerView = (TickerView) findViewById(R.id.tickerView);
        lvList = (ListView) findViewById(R.id.lv_ticker_list);
        tvCount = (TextView) findViewById(R.id.tv_count_view);
        llLayout = (LinearLayout) findViewById(R.id.ll_count_layout);

        dataList = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            DataList data = new DataList();

            data.setData("Data " + i);
            data.setDataPos(i);
            dataList.add(data);
        }

        Collections.reverse(dataList);
        adapter = new ListExampleAdapter(context, dataList);
        lvList.setAdapter(adapter);


        tickerView.setCharacterList(NUMBER_LIST);

        tickerView.setText("$00." + "02");
        tickerView.setAnimationDuration(1500);
        tickerView.setAnimationInterpolator(new AccelerateInterpolator());
        tickerView.setText("$32." + "54");


       /* new Thread(new Runnable() {

            public void run() {
                while (counter < total) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    tvCount.post(new Runnable() {

                        public void run() {
                            tvCount.setText("" + counter);

                        }

                    });
                    counter++;
                }

            }

        }).start();*/

       // animateTextView(0.0f, 88.88f, tvCount);
      //  animateTextView(0, 1000, tvCount);
       // increaseCount("78", tvCount);
       // dataRotation("28", tvCount);

        tickerView("$789.55", llLayout);
    }

    private static int ANIMATION_DURATION = 250;
    private static final float ROTATE_FROM = 0.0f;
    private static final float ROTATE_TO = -10.0f * 360.0f;

    public void tickerView(final String value, final LinearLayout layout)
    {
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.sample_animation);

        final Animation rotate = new RotateAnimation(ROTATE_FROM, ROTATE_TO, Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.2f);

       final char[] val = value.toCharArray();

        for(int i = 0 ; i < val.length; i++)
        {
            TextView text = new TextView(this);
            String str = val[i]+"";
            if(!str.equals("$") && !str.equals("."))
            {
                text.setText("0");
            }else
            {
                text.setText(str);
            }
            text.setTag(i);
            text.setId(i);
            text.setTextSize(30);

            layout.addView(text);
        }

        final AccelerateInterpolator decelerateInterpolator = new AccelerateInterpolator(10.0f);
        final Handler handler = new Handler();
        final int lastVar = layout.getChildCount();

        new Thread(new Runnable() {

            public void run() {

                for(int l = 1 ; l <= layout.getChildCount() ; l++)
                {
                    final TextView text = (TextView) layout.getChildAt(lastVar-l);
                    final String data = val[lastVar-l]+"";
                    if(!data.equals("$") && !data.equals("."))
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        tvCount.post(new Runnable() {

                            public void run() {
                                Log.e("&&&&&", counter+"");

                                for(int k = 0 ; k <= Integer.parseInt(data); k++)
                                {
                                    final int j = k;

                                    int time = Math.round(decelerateInterpolator.getInterpolation((((float) j) / 10)) * 100) * j;

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.e("Valueeee", j+"  "+counter);
                                            text.startAnimation(animation);
                                            text.setText(j+"");
                                        }
                                    }, time);
                                }
                            }
                        });
                    }

                }


            }

        }).start();

    }


    public void animateTextView(float initialValue, float finalValue, final TextView  textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(initialValue, finalValue);
        valueAnimator.setDuration(1500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                textview.setText(valueAnimator.getAnimatedValue().toString());

            }
        });
        valueAnimator.start();

    }


    public void animateTextView(int initialValue, int finalValue, final TextView textview) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start = Math.min(initialValue, finalValue);
        int end = Math.max(initialValue, finalValue);
        int difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        Log.e("start & end value", start +"  "+end+"  "+difference);
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 100) * count;

            final int finalCount = ((initialValue > finalValue) ? initialValue - count : count);

            Log.e("time & final value", time +"  "+finalCount);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textview.setText(finalCount + "");
                }
            }, time);
        }
    }


}
