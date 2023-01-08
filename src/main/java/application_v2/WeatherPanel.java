package application_v2;

import data.country.City;
import data.country.Country;
import data.dao.DAO;
import data.weather.Weather;
import data.weather.WeatherConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class WeatherPanel extends ContentPanel implements Runnable{
    private MainFrame frame;
    private int width;
    private int height;
    private JButton backToMapButton;
    private Thread thread;
    private int FPS;
    private City city;
    private Weather weather;
    private MouseHandler mouseHandler;
    private int x;
    private int y;
    private final Dimension buttonSize = new Dimension(200,30);
    private final Color panelBackground = new Color(32, 33, 36);
    private final Font font = new Font("Serif", Font.BOLD, 20);
    private float maxT;
    private float minT;
    private BufferedImage weekGraphic;
    private Image reWeekGraphic;
    private int reX;
    private int reY;
    private double reCoefficient;



    public WeatherPanel(MainFrame frame, City city) {
        super(frame);
        this.frame = frame;
        this.city = city;
        setThisPanel();
        setDefaultValues();
        startShowingInfo();
    }
    private void setThisPanel(){
        this.setFocusable(true);
        this.setLayout(null);
        this.setLocation(0,0);
        this.setBackground(panelBackground);
        this.setSize(new Dimension(800,600));
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    private void setDefaultValues(){
        setWeather();
        FPS = 30;
        mouseHandler = new MouseHandler();
        setBackToMapButton();
        maxT = -50.0f;
        minT = 50.0f;
        for (float f : weather.getHourly().getTemperature_2m()){
            if(f >= maxT) maxT = f;
            if(f <= minT) minT = f;
        }
        setWeekGraphic();
    }
    private void setWeather(){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(city.getTime() == null || !city.getTime().equals(time)) {
            System.out.println("обновление погоды");
            city.setTime(time);
            WeatherConverter.requestNewWeatherInfo(city);
            DAO.setWeather(city);
        }
        weather = WeatherConverter.getWeatherFromDB(city.getJsonWeather());
    }
    private void setBackToMapButton(){
        backToMapButton = Components.getButton(buttonSize, "Назад к карте");
        backToMapButton.setLocation(0,0);
        backToMapButton.addActionListener(l -> {
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new CountryMapPanel(frame, city.getCountry()));
        });
        this.add(backToMapButton);
    }
    private void setWeekGraphic(){
        try {
            weekGraphic = ImageIO.read(new File("src\\main\\resources\\transparent.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics g = weekGraphic.createGraphics();
        g.setFont(new Font("Serif", Font.PLAIN, 40));
        g.setColor(Color.white);
        g.drawString(city.getName() + ",",weekGraphic.getWidth() / 8,weekGraphic.getHeight() / 12 + 40);
        g.drawString(city.getCountry().getName(), weekGraphic.getWidth() / 8,weekGraphic.getHeight() / 12 + 80);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        for(int i = 0; i < 7; i++){
            g.drawString(
                    weather.getHourly().getTime()[i * 24].replaceAll("(\\d{4})(-)(\\d{2})(-)(\\d{2})(T00:00)","$5.$3"),
                    (weekGraphic.getWidth() / 8) + (i * 205) + 65,
                    (weekGraphic.getHeight() / 3) + 30);
            g.drawLine((weekGraphic.getWidth() / 8) + (i * 205),(weekGraphic.getHeight() / 3),(weekGraphic.getWidth() / 8) + (i * 205),weekGraphic.getHeight());
            g.drawOval(
                    (weekGraphic.getWidth()) / 8 + (i * 205) + 85,
                    (weekGraphic.getHeight() / 3) + 35,
                    30,
                    30);
        }
        for(int i = 0; i <= 5; i++){
            g.drawString(
                    String.valueOf((float)(Math.round((maxT - Math.abs(maxT - minT) * i / 6) * 10)) / 10),
                    (weekGraphic.getWidth() / 8),
                    (weekGraphic.getHeight() / 2) + (i * weekGraphic.getHeight() / 12) + 10 );
            g.drawLine(
                    (weekGraphic.getWidth() / 8),
                    (weekGraphic.getHeight() / 2) + (i * weekGraphic.getHeight() / 12) + 10,
                    (weekGraphic.getWidth() / 8) + 1435,
                    (weekGraphic.getHeight() / 2) + (i * weekGraphic.getHeight() / 12) + 10
            );
        }
        for(int i = 0; i < 167; i++){
            g.drawLine(
                    (weekGraphic.getWidth() / 8) + i * 1435 / 167,
                    (int)((weekGraphic.getHeight() / 2) + 10 + weekGraphic.getHeight() * 5 / 12 * (Math.abs(weather.getHourly().getTemperature_2m()[i] - maxT) / Math.abs(minT - maxT))),
                    (weekGraphic.getWidth() / 8) + (i + 1) * 1435 / 167,
                    (int)((weekGraphic.getHeight() / 2) + 10 + weekGraphic.getHeight() * 5 / 12 * (Math.abs(weather.getHourly().getTemperature_2m()[i + 1] - maxT) / Math.abs(minT - maxT)))
            );
        }
    }
    private void startShowingInfo(){
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / (double)FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(thread != null){
            update();
            repaint();
            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
                if(remainingTime < 0 ) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(reWeekGraphic,0, 0, this);
    }
    @Override
    void actionOnResizing(int width, int height) {
        this.width = width;
        this.height = height;
        resizeWeekGraphic();
        this.setSize(width, height);
    }
    private void resizeWeekGraphic(){
        double frameRatio = (double) width / (double) height;
        int mapW = weekGraphic.getWidth(null);
        int mapH = weekGraphic.getHeight(null);
        if(frameRatio >= ((double) mapW / (double) mapH)) reCoefficient = (double)height / (double)mapH;
        else reCoefficient = (double)width / (double)mapW;
        reWeekGraphic = weekGraphic.getScaledInstance((int) (mapW * reCoefficient), (int)(mapH * reCoefficient), Image.SCALE_DEFAULT);
    }
}
