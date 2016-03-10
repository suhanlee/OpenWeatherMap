/*
 * Copyright (C) 2015 Suhan Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devsh.openweathermap.library;

import android.util.Log;

import com.devsh.openweathermap.library.response.OpenWeatherResponse;
import com.devsh.openweathermap.library.response.OpenWeatherResponseCallback;
import com.devsh.openweathermap.library.response.data.Weather;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherMap {
    private static String TAG = "OpenWeatherMap";
    private static String BASE_URL = "http://api.openweathermap.org";

    private static boolean DEBUG = false;

    private static Retrofit sRetrofit;
    private static String sAppId;

    public static void enableDebugLog(boolean debug) {
        DEBUG = debug;
    }

    public static void initialize(String appId) {
        initialize(appId, false);
    }

    public static void initialize(String _appId, boolean httpLog) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder();
        if (httpLog) {
            builder = builder.client(client);

        }

        sRetrofit = builder.baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sAppId = _appId;
    }

    public static OpenWeatherResponse getWeatherGeoLocation(String lat, String lon) throws IOException {
        OpenWeatherMapService service = sRetrofit.create(OpenWeatherMapService.class);
        Call<OpenWeatherResponse> response = service.getWeatherFromGeoLocation(sAppId, lat, lon);
        return response.execute().body();
    }

    public static void getWeatherGeoLocation(String lat, String lon, final OpenWeatherResponseCallback callback) {
        OpenWeatherMapService service = sRetrofit.create(OpenWeatherMapService.class);
        Call<OpenWeatherResponse> response = service.getWeatherFromGeoLocation(sAppId, lat, lon);
        response.enqueue(new Callback<OpenWeatherResponse>() {
            @Override
            public void onResponse(Call<OpenWeatherResponse> call, Response<OpenWeatherResponse> response) {
                if (DEBUG) {
                    Log.d(TAG, "onResponse");
                }

                if (response.isSuccess()) {
                    OpenWeatherResponse result = response.body();

                    if (DEBUG) {
                        Log.d(TAG, "response.isSuccess() == true");
                        printResponse(result);
                    }

                    callback.onResponseSuccess(result);
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();

                    if (DEBUG) {
                        Log.e(TAG, "statusCode:" + statusCode);
                        Log.e(TAG, errorBody.toString());
                    }
                    callback.onResponseFail(statusCode, errorBody.toString());
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherResponse> call, Throwable t) {
                if (DEBUG) {
                    Log.d(TAG, "onFailure");
                }

                callback.onFailure(call, t);
            }
        });
    }

    public static void printResponse(OpenWeatherResponse result) {
        Log.i(TAG, "base:" + result.getBase());
        Log.i(TAG, "clouds.all:" + result.getClouds().getAll());
        Log.i(TAG, "cod:" + result.getCod());
        Log.i(TAG, "coord.lat:" + result.getCoord().getLat());
        Log.i(TAG, "coord.lon:" + result.getCoord().getLon());
        Log.i(TAG, "dt:" + result.getDt());
        Log.i(TAG, "id:" + result.getId());
        Log.i(TAG, "main.humidity:" + result.getMain().getHumidity());
        Log.i(TAG, "main.pressure:" + result.getMain().getPressure());
        Log.i(TAG, "main.temp:" + result.getMain().getTemp());
        Log.i(TAG, "main.temp_max:" + result.getMain().getTemp_max());
        Log.i(TAG, "main.temp_min:" + result.getMain().getTemp_min());
        Log.i(TAG, "name:" + result.getName());
        Log.i(TAG, "sys.country:" + result.getSys().getCountry());
        Log.i(TAG, "sys.id:" + result.getSys().getId());
        Log.i(TAG, "sys.message:" + result.getSys().getMessage());
        Log.i(TAG, "sys.sunrise:" + result.getSys().getSunrise());
        Log.i(TAG, "sys.sunset:" + result.getSys().getSunset());
        Log.i(TAG, "sys.type:" + result.getSys().getType());
        Log.i(TAG, "visibility:" + result.getVisibility());
        Log.i(TAG, "weather.size:" + result.getWeather().size());
        for(Weather weather : result.getWeather()) {
            Log.i(TAG, "weather.description:" + weather.getDescription());
            Log.i(TAG, "weather.icon:" + weather.getIcon());
            Log.i(TAG, "weather.id:" + weather.getId());
            Log.i(TAG, "weather.main:" + weather.getMain());
        }

        Log.i(TAG, "wind.deg:" + result.getWind().getDeg());
        Log.i(TAG, "wind.speed:" + result.getWind().getSpeed());
    }
}
