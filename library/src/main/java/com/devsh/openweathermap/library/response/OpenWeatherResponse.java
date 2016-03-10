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

package com.devsh.openweathermap.library.response;

import com.devsh.openweathermap.library.response.data.Clouds;
import com.devsh.openweathermap.library.response.data.Coord;
import com.devsh.openweathermap.library.response.data.Main;
import com.devsh.openweathermap.library.response.data.Sys;
import com.devsh.openweathermap.library.response.data.Weather;
import com.devsh.openweathermap.library.response.data.Wind;

import java.util.List;

public class OpenWeatherResponse {
    private String base;
    private Clouds clouds;
    private String cod;
    private Coord coord;
    private String dt;
    private String id;
    private Main main;
    private String name;
    private Sys sys;
    private String visibility;
    private List<Weather> weather;
    private Wind wind;

    public String getBase() {
        return base;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getCod() {
        return cod;
    }

    public Coord getCoord() {
        return coord;
    }


    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Sys getSys() {
        return sys;
    }

    public String getVisibility() {
        return visibility;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }
}
