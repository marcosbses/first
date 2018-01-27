package com.example.marcos.weatheroftheday.utiles;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marcos on 12/2/17.
 */

public class Weather {
    //metric units:Celsius,km/h
    private String units;
    private String locationName;
    private int temp;
    private int relativeHumidity;
    private int windSpeed;
    private int apparentTemperature;
    public Weather(JSONObject weatherForecastJson){

        try {
            locationName=weatherForecastJson.getJSONObject("location").getString("name");
            JSONObject currentObject=weatherForecastJson.getJSONObject("current");
            temp=currentObject.getInt("temp_c");
            relativeHumidity=currentObject.getInt("humidity");

            windSpeed=(int)Math.round(currentObject.getDouble("wind_kph"));

            double wsMpS=(windSpeed*1000)/(60*60);

            double e=(relativeHumidity/100.0)*6.105*Math.pow(Math.E,(17.27*temp)/(237.7+temp));
            apparentTemperature=(int)Math.round(temp+0.348*e-0.7*wsMpS-4.25);

            units="metric";
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String getUnits(){
        return units;
    }
    public String getLocationName(){
        return locationName;
    }
    public int getTemp() {return temp;}
    public int getRelativeHumidity(){return relativeHumidity;}
    public int getWindSpeed(){return windSpeed;}
    public int getApparentTemperature(){return apparentTemperature;}

}
