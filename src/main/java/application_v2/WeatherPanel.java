package application_v2;

import data.country.City;
import data.country.Country;
import data.dao.DAO;
import data.weather.Weather;
import data.weather.WeatherConverter;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
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

    public WeatherPanel(MainFrame frame, City city) {
        super(frame);
        this.frame = frame;
        this.city = city;
        setWeather();
        setThisPanel();
        startShowingInfo();
    }
    private void setThisPanel(){
        this.setFocusable(true);
        this.setLayout(null);
        this.setLocation(0,0);
        this.setBackground(panelBackground);
        this.setSize(new Dimension(800,600));
        this.setDoubleBuffered(true);
        mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        FPS = 30;
        setBackToMapButton();
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
        for(int i = 0; i < 7; i++){

        }
    }
    @Override
    void actionOnResizing(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
    }
}
