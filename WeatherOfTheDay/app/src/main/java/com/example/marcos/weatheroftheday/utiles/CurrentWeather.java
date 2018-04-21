package com.example.marcos.weatheroftheday.utiles;

import org.json.JSONObject;

/**
 * Created by marcos on 11/28/17.
 */

public class CurrentWeather implements AntropocentricWeather{
    //metric units:Celsius,km/h
    private Weather weather;
    //constructor dependency injection
    public CurrentWeather(Weather weather){
        this.weather=weather;
    }

    public String getUnits(){
        return weather.getUnits();
    }
    public String getLocationName(){
        return weather.getLocationName();
    }
    public int getCurrentTemp(){
        return weather.getTemp();
    }
    public int getCurrentRelativeHumidity(){
        return weather.getRelativeHumidity();
    }
    public int getCurrentWindSpeed(){
        return weather.getWindSpeed();
    }
    public int getCurrentApparentTemperature(){
        return weather.getApparentTemperature();
    }
    public int getApparentTemperature(){
        return getCurrentApparentTemperature();
    }
}
