package application_v2;

import data.country.City;
import data.dao.DAO;
import data.weather.Weather;
import data.weather.WeatherConverter;

import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private final Dimension buttonSize = new Dimension(200,30);
    private final Color panelBackground = new Color(32, 33, 36);
    private final Font font = new Font("Serif", Font.BOLD, 20);
    private MouseHandler mouseHandler;
    private int x;
    private int y;
    private BufferedImage[] weekGraphic;

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
        mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    private void setDefaultValues(){
        setWeather();
        FPS = 30;
        setBackToMapButton();
        weekGraphic = new BufferedImage[3];
        setWeatherImages((int)(frame.getWidth() * 0.8), (int)(frame.getHeight() * 0.9));
    }
    private void setWeather(){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(city.getTime() == null || !city.getTime().equals(time)) {
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
    private void setWeatherImages(int width, int height){
        weekGraphic = Graph.getWeathersImages(city,weather, width,height);
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
        x = mouseHandler.x;
        y = mouseHandler.y;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(weekGraphic[0],(int)(this.width * 0.1), (int)(this.height * 0.05), this);
        g2.drawImage(weekGraphic[1],(int)(this.width * 0.1), (int)(this.height * 0.05) + weekGraphic[0].getHeight(), this);
        g2.drawImage(weekGraphic[2],(int)(this.width * 0.1), (int)(this.height * 0.05) + weekGraphic[0].getHeight() + weekGraphic[1].getHeight(), this);

        if(x >= (this.width * 0.1 + weekGraphic[2].getWidth() * 0.04) && x <= (this.width * 0.9 - weekGraphic[2].getWidth() * 0.04)){
            if(y >= (this.height * 0.05) + weekGraphic[0].getHeight() + weekGraphic[1].getHeight() && y <= (this.height * 0.95)){
//                System.out.println((int) ( (x - this.width * 0.1 - weekGraphic[2].getWidth() * 0.04) * 168 / (weekGraphic[2].getWidth() * 0.92) ));
                for(int i = 0; i < weekGraphic[2].getHeight(); i++){
                    if(weekGraphic[2].getRGB((int)(x - this.width * 0.1), i) == new Color(3, 236, 252).getRGB()){
                        g2.setColor(new Color(3, 236, 252));
                        g2.fillOval(x - 5 ,i - 5 + (int)(this.height * 0.05) + weekGraphic[0].getHeight() + weekGraphic[1].getHeight(),10,10);
                        g2.setColor(new Color(3, 236, 252,100));
                        g2.fillRoundRect(
                                x,
                                i + (int)(this.height * 0.05) + weekGraphic[0].getHeight() + weekGraphic[1].getHeight(),
                                (int)(weekGraphic[2].getWidth()*0.12),
                                (int)(weekGraphic[2].getWidth()*0.06),
                                5,
                                5);
                        g2.setColor(Color.white);
                        g2.setFont(new Font("Serif", Font.BOLD, (int)(weekGraphic[2].getWidth() * 0.02)));
                        g2.drawString(
                                weather.getHourly().getTime()[(int) ( (x - this.width * 0.1 - weekGraphic[2].getWidth() * 0.04) * 168 / (weekGraphic[2].getWidth() * 0.92) )].replaceAll("(\\d{4})(-)(\\d{2})(-)(\\d{2})(T)(\\d{2}:\\d{2})","$5.$3 $7"),
                                x,
                                i + (int)(this.height * 0.05) + weekGraphic[0].getHeight() + weekGraphic[1].getHeight()+ (int)(weekGraphic[2].getWidth()*0.02)
                                );
                        g2.drawString(
                                String.valueOf(weather.getHourly().getTemperature_2m()[(int) ( (x - this.width * 0.1 - weekGraphic[2].getWidth() * 0.04) * 168 / (weekGraphic[2].getWidth() * 0.92) )]),
                                x,
                                i + (int)(this.height * 0.05) + weekGraphic[0].getHeight() + weekGraphic[1].getHeight()+ (int)(weekGraphic[2].getWidth()*0.05)
                        );
                    }
                }
            }
        }
    }
    @Override
    void actionOnResizing(int width, int height) {
        this.width = width;
        this.height = height;
        resizeWeekGraphic();
        this.setSize(width, height);
    }
    private void resizeWeekGraphic(){
        setWeatherImages((int)(this.width * 0.8), (int)(this.height * 0.9));
    }
}
