package application;

import data.weather.Weather;

import javax.swing.*;
import java.awt.*;

public class CityWeather extends JPanel implements Runnable{
    private int width;
    private int height;
    private Thread thread;
    private Weather weather;
    private final int FPS;

    public CityWeather(Weather weather){
        this.weather = weather;
        width = 1300;
        height = 950;
        setPanel();
        FPS = 30;
    }

    public void startShowingInfo(){
        thread = new Thread(this);
        thread.start();
        System.out.println(weather);
    }

    private void setPanel(){
        this.setFocusable(true);
        this.setLayout(null);
        this.setBounds(0,0,width,height);
        this.setBackground(new Color(32, 33, 36));
        this.setDoubleBuffered(true);
    }


    @Override
    public void run() {

    }
}
