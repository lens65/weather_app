package application;

import data.country.City;
import data.country.Country;
import data.dao.DAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CountryMap extends JPanel implements Runnable{
    private int width;
    private int height;
    private JButton addCityButton;
    private Thread thread;
    private final int FPS;
    private Country country;
    private Image map;
    private Image resizedMap;
    private MouseHandler mouseHandler;
    private int x;
    private int y;
    private Font regularFont;
    private Font focusFont;

    private boolean addCity;
    private boolean showMap;
    private boolean showWeather;

    public CountryMap(Country country){

        showMap = true;
        showWeather = false;
        width = 1300;
        height = 950;
        regularFont = new Font("Serif", Font.PLAIN, 15);
        focusFont = new Font("Serif", Font.BOLD, 20);
        this.country = country;
        setMap();
        mouseHandler = new MouseHandler();
        setPanel();
        FPS = 30;
        addCity = false;
    }

    public void startShowingInfo(){
        thread = new Thread(this);
        thread.start();
    }

    private void setPanel(){
        this.setFocusable(true);
        this.setLayout(null);
        this.setBounds(0,0,width,height);
        this.setBackground(new Color(32, 33, 36));
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        setAddCityButton();
        this.add(addCityButton);
    }
    private void setMap(){
        try {
            map = ImageIO.read(new URL(country.getMap()));
//            if(map.getWidth(null) > width || map.getHeight(null) > height) {
//                if (map.getWidth(null) > map.getHeight(null)) {
//                    resizedMap = map.getScaledInstance(width, map.getHeight(null) * width / map.getWidth(null), Image.SCALE_DEFAULT);
//                } else  resizedMap = map.getScaledInstance(map.getWidth(null) * height / map.getHeight(null), height, Image.SCALE_DEFAULT);
//            } else resizedMap = map.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            resizedMap = map.getScaledInstance(width, height, Image.SCALE_DEFAULT);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAddCityButton(){
        addCityButton = Components.getJButton(1100,0,200,20,"Добавить город");
        addCityButton.addActionListener(l ->{
            addCity = true;
            System.out.println("Button was pressed");
            addCityButton.setVisible(false);
        });
    }



    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
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
        if (x > resizedMap.getWidth(null)) x = resizedMap.getWidth(null);
        if (y > resizedMap.getHeight(null)) y = resizedMap.getHeight(null);
        if (addCity) {
            if (mouseHandler.mousePressed) {
//                if(map.getWidth(null) > width || map.getHeight(null) > height) {
//                    if (map.getWidth(null) > map.getHeight(null)) {
//                        new AddCityFrame(x, y * map.getWidth(null) / width, country);
//                    } else  new AddCityFrame(x * map.getHeight(null) / height, y , country);
//                } else new AddCityFrame(x, y, country);
                new AddCityFrame(x, y, country);
                addCity = false;
                country = DAO.getCountry(country.getId());
                addCityButton.setVisible(true);
            }
        }
        if(!addCity){
            for(City city : country.getCities()){

                if(Math.sqrt((x - city.getX()) * (x - city.getX()) + (y - city.getY()) * (y - city.getY())) < 20) {
                    if(mouseHandler.mousePressed){
                        System.out.println(city.getName());
                        new CityWeatherFrame(city);
                    }
                }

            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(resizedMap, 0 ,0, this);
        for(City city : country.getCities()){
            g2.setColor(Color.GREEN);
            g2.fillOval(city.getX() - 5, city.getY() - 5, 10,10);
            if(Math.sqrt((x - city.getX()) * (x - city.getX()) + (y - city.getY()) * (y - city.getY())) < 20) g2.setFont(focusFont);
            else g2.setFont(regularFont);
            g2.drawString(city.getName(), city.getX() - 5, city.getY() - 5);
        }
        if(addCity){
            g2.setColor(Color.ORANGE);
            g2.fillOval(x-3,y-3,6,6);
        }
    }
}
