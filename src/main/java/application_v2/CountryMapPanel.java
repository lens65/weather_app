package application_v2;

import data.country.City;
import data.country.Country;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CountryMapPanel extends ContentPanel implements Runnable{
    private final MainFrame frame;
    //высота и ширина панели
    private int width;
    private int height;
    private JButton addCityButton;
    private JButton backToMenuButton;
    private Thread thread;
    private int FPS;
    private final Country country;
    //оригианльное изображение
    private Image map;
    //изображение подогнаное под размер панели
    private Image resizedMap;
    private MouseHandler mouseHandler;
    //положение мышки на картте
    private int x;
    private int y;
    //пересчитаное положение города на подогнаной карте
    private int reX;
    private int reY;
    private final Font regularFont = new Font("Serif", Font.PLAIN, 15);
    private final Font focusFont = new Font("Serif", Font.BOLD, 20);
    private boolean addCity;
    private final Dimension buttonSize = new Dimension(200,30);
    private final Color panelBackground = new Color(32, 33, 36);
    //коэфиициент преобразования изображения и координат
    private double reCoefficient;

    public CountryMapPanel(MainFrame frame, Country country){
        super(frame);
        this.frame = frame;
        this.country = country;
        setPanel();
        setMap();
        actionOnResizing(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        startShowingInfo();
    }
    private void setPanel(){
        this.setFocusable(true);
        this.setLayout(null);
        this.setLocation(0,0);
        this.setBackground(panelBackground);
        this.setSize(new Dimension(800,600));
        this.setDoubleBuffered(true);
        mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        setAddCityButton();
        setBackToMenuButton();
        FPS = 30;
        addCity = false;
    }
    public void startShowingInfo(){
        thread = new Thread(this);
        thread.start();
    }
    private void setMap(){
        try {
            map = ImageIO.read(new URL(country.getMap()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAddCityButton(){
        addCityButton = Components.getButton(buttonSize,"Добавить город");
        addCityButton.addActionListener(l ->{
            addCity = true;
            System.out.println("Добавление нового города");
            addCityButton.setVisible(false);
        });
        this.add(addCityButton);
    }
    private void setBackToMenuButton(){
        backToMenuButton = Components.getButton(buttonSize, "В главное меню");
        backToMenuButton.setLocation(0,0);
        backToMenuButton.addActionListener(l -> {
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new MenuPanel(frame));
        });
        this.add(backToMenuButton);
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
        if (x > resizedMap.getWidth(null)) x = resizedMap.getWidth(null);
        if (y > resizedMap.getHeight(null)) y = resizedMap.getHeight(null);
        if (addCity) {
            if (mouseHandler.mousePressed) {
                this.removeAll();
                this.setEnabled(false);
                frame.setContentPanel(new AddCityPanel(frame, country, (int)(x / reCoefficient), (int)(y / reCoefficient)));
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!addCity){
            for(City city : country.getCities()){
                reX = (int)(city.getX() * reCoefficient);
                reY = (int)(city.getY() * reCoefficient);
                if(Math.sqrt((x - reX) * (x - reX) + (y - reY) * (y - reY)) < 20) {
                    if(mouseHandler.mousePressed){
                        this.removeAll();
                        this.setEnabled(false);
                        frame.setContentPanel(new WeatherPanel(frame,city));
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
            reX = (int)(city.getX() * reCoefficient);
            reY = (int)(city.getY() * reCoefficient);
            g2.setColor(new Color(85, 116, 130));
            g2.fillOval(reX - 5, reY - 5, 10,10);

            if(Math.sqrt((x - reX) * (x - reX) + (y - reY) * (y - reY)) < 20) g2.setFont(focusFont);
            else g2.setFont(regularFont);
            g2.drawString(city.getName(), reX - 5, reY - 5);
        }
        if(addCity){
            g2.setColor(Color.ORANGE);
            g2.fillOval(x-3,y-3,6,6);
        }
    }

    @Override
    void actionOnResizing(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        resizeMap();
        addCityButton.setLocation(width - buttonSize.width, 0);
    }
    private void resizeMap(){
        double frameRatio = (double) width / (double) height;
        int mapW = map.getWidth(null);
        int mapH = map.getHeight(null);
        if(frameRatio >= ((double) mapW / (double) mapH)) reCoefficient = (double)height / (double)mapH;
        else reCoefficient = (double)width / (double)mapW;
        resizedMap = map.getScaledInstance((int) (mapW * reCoefficient), (int)(mapH * reCoefficient), Image.SCALE_DEFAULT);
    }
}
