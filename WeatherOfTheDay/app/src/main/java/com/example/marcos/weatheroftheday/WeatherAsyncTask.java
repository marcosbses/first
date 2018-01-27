package com.example.marcos.weatheroftheday;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.marcos.weatheroftheday.utiles.CurrentWeather;
import com.example.marcos.weatheroftheday.utiles.HttpResponseGetter;
import com.example.marcos.weatheroftheday.utiles.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by marcos on 11/24/17.
 */

public class WeatherAsyncTask extends AsyncTask<String,Void,String> {
    private Context context;

    public WeatherAsyncTask(Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String apixuURL=strings[0];
        String httpResponse;
        try {
            URL url=new URL(apixuURL);
            httpResponse=new HttpResponseGetter().getHttpResponse(url);
            return httpResponse;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String httpResponse){
        try {
            JSONObject jsonObject=new JSONObject(httpResponse);
            CurrentWeather weatherForecast=new CurrentWeather(new Weather(jsonObject));
            String locationName=weatherForecast.getLocationName();
            Toast.makeText(context,locationName,Toast.LENGTH_LONG).show();
            Toast.makeText(context,weatherForecast.getCurrentTemp()+" C",Toast.LENGTH_LONG).show();
            Toast.makeText(context,"RH: "+weatherForecast.getCurrentRelativeHumidity(),Toast.LENGTH_LONG).show();
            Toast.makeText(context,"WS: "+weatherForecast.getCurrentWindSpeed(),Toast.LENGTH_LONG).show();
            Toast.makeText(context,"AT: "+weatherForecast.getCurrentApparentTemperature(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
