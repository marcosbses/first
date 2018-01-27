package com.example.marcos.weatheroftheday.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by marcos on 11/24/17.
 */

public class HttpResponseGetter {
    public String getHttpResponse(URL url){
        String inputLine;
        StringBuffer stringBuffer=new StringBuffer();


        try {
            InputStream inputStream = url.openConnection().getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            while ((inputLine = bufferedReader.readLine()) != null)
                stringBuffer.append(inputLine);
            bufferedReader.close();
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
