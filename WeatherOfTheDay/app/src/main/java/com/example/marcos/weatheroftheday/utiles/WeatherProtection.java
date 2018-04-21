package com.example.marcos.weatheroftheday.utiles;

/**
 * Created by marcos on 12/3/17.
 */

public class WeatherProtection {
    private int thermicCoverLevel;
    WeatherProtection(AntropocentricWeather antropocentricWeather){
        int at=antropocentricWeather.getApparentTemperature();
        if(at<=10){
            thermicCoverLevel=4;//buzo
        }else if(at<=26){
            thermicCoverLevel=3;//bucito
        }else{
            thermicCoverLevel=2;//pantalon y remera
        }

    }

    public int getThermicCoverLevel(){
        return thermicCoverLevel;
    }
    public String getThermicCoverLevelText(){
        if (thermicCoverLevel==4) return "Buzo";
        if (thermicCoverLevel==3) return "Bucito";
        if (thermicCoverLevel==2) return "Remera";
        return null;
    }
}
