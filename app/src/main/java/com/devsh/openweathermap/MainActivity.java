package com.devsh.openweathermap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.devsh.openweathermap.library.OpenWeatherMap;
import com.devsh.openweathermap.library.response.OpenWeatherResponse;
import com.devsh.openweathermap.library.response.OpenWeatherResponseCallback;

import java.io.IOException;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private String API_KEY = "API-KEY";
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenWeatherMap.initialize(API_KEY);

//        OpenWeatherMap.initialize(API_KEY, true); // Enable HttpLog
//        OpenWeatherMap.enableDebugLog(true); // Enable Debug Log

        OpenWeatherMap.getWeatherGeoLocation("37.566535", "126.977969", new OpenWeatherResponseCallback() {
            @Override
            public void onResponseSuccess(OpenWeatherResponse response) {
                OpenWeatherMap.printResponse(response);
            }

            @Override
            public void onResponseFail(int statusCode, String error) {

            }

            @Override
            public void onFailure(Call<OpenWeatherResponse> call, Throwable t) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OpenWeatherResponse response = OpenWeatherMap.getWeatherGeoLocation("37.566535", "126.977969");
                    Log.i(TAG, response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();


    }
}
