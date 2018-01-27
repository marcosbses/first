package com.example.marcos.weatheroftheday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new WeatherAsyncTask(getApplicationContext()).execute("https://api.apixu.com/v1/forecast.json?key=7adc28cef4f347a0b1821747172211&q=Montevideo");
    }
}
