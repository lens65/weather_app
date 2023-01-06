package application;

import data.country.City;
import data.dao.DAO;
import data.weather.Weather;
import data.weather.WeatherConverter;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CityWeatherFrame {
    private JFrame frame;
    private CityWeather cityWeather;
    private Weather weather;
    private String time;

    public CityWeatherFrame(City city){
        setTime();
        setWeather(city);
        setFrame(city);
        setCityWeather(weather);
        frame.add(cityWeather);
        frame.setVisible(true);
        cityWeather.startShowingInfo();

    }
    private void setFrame(City city){
        frame = Components.getJFrame(1300, 950, city.getName());
    }
    private void setCityWeather(Weather weather){
        cityWeather = new CityWeather(weather);
    }
    private void setWeather(City city){
        if(city.getTime() == null || !city.getTime().equals(time)) {
            System.out.println("обновление погоды");
            city.setTime(time);
            WeatherConverter.requestNewWeatherInfo(city);
            DAO.setWeather(city);
        }
        weather = WeatherConverter.getWeatherFromDB(city.getJsonWeather());
    }
    private void setTime(){
        time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
