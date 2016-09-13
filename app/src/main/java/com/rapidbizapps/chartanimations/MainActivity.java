package com.rapidbizapps.chartanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button barChart, barLineChart, count, circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = (Button) findViewById(R.id.btn_bar_chart);
        barLineChart = (Button) findViewById(R.id.btn_bar_line_chart);
        count = (Button) findViewById(R.id.btn_ticker_count);
        circle = (Button) findViewById(R.id.btn_circle_progress);

        barChart.setOnClickListener(this);
        barLineChart.setOnClickListener(this);
        count.setOnClickListener(this);
        circle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_bar_chart:
                Intent intent1 = new Intent(MainActivity.this, BarChartActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_bar_line_chart:
                Intent intent2 = new Intent(MainActivity.this, LineChartActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_ticker_count:
                Intent intent3 = new Intent(MainActivity.this, TickerCountActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_circle_progress:
                Intent intent4 = new Intent(MainActivity.this, CircleProgressActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
