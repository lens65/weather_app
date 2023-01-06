package data.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.country.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherConverter {
    public static Weather getWeatherFromDB(String json){
        Weather weather = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            weather = objectMapper.readValue(json, Weather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return weather;
    }
    public static void requestNewWeatherInfo(City city){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=" + city.getLatitude() + "&longitude=" + city.getLongitude() + "&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            connection.disconnect();
            city.setJsonWeather(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
