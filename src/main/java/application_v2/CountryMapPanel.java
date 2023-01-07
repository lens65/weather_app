package application_v2;

import data.country.Country;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CountryMapPanel extends ContentPanel implements Runnable{
    private final MainFrame frame;
    private int width;
    private int height;
    private JButton addCityButton;
    private Thread thread;
    private int FPS;
    private Country country;
    private Image map;
    private Image resizedMap;
    private MouseHandler mouseHandler;
    private int x;
    private int y;
    private Font regularFont;
    private Font focusFont;
    private boolean addCity;
    private final Dimension buttonSize = new Dimension(200,30);
    private final Color panelBackground = new Color(32, 33, 36);

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
//        setAddCityButton();
//        this.add(addCityButton);
        regularFont = new Font("Serif", Font.PLAIN, 15);
        focusFont = new Font("Serif", Font.BOLD, 20);
        FPS = 30;
//        addCity = false;
    }
    public void startShowingInfo(){
        thread = new Thread(this);
        thread.start();
    }
    private void setMap(){
        try {
            map = ImageIO.read(new URL(country.getMap()));
            System.out.println("Карта загрузилась");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAddCityButton(){
        addCityButton = Components.getButton(buttonSize,"Добавить город");
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
        System.out.println(x + "  " + y);
//        if (addCity) {
//            if (mouseHandler.mousePressed) {
////                if(map.getWidth(null) > width || map.getHeight(null) > height) {
////                    if (map.getWidth(null) > map.getHeight(null)) {
////                        new AddCityFrame(x, y * map.getWidth(null) / width, country);
////                    } else  new AddCityFrame(x * map.getHeight(null) / height, y , country);
////                } else new AddCityFrame(x, y, country);
////                new AddCityFrame(x, y, country);
////                addCity = false;
////                country = DAO.getCountry(country.getId());
////                addCityButton.setVisible(true);
//            }
//        }
//        if(!addCity){
//            for(City city : country.getCities()){
//                if(Math.sqrt((x - city.getX()) * (x - city.getX()) + (y - city.getY()) * (y - city.getY())) < 20) {
//                    if(mouseHandler.mousePressed){
//                        System.out.println(city.getName());
//                    }
//                }
//            }
//        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(resizedMap, 0 ,0, this);
//        for(City city : country.getCities()){
//            g2.setColor(Color.GREEN);
//            g2.fillOval(city.getX() - 5, city.getY() - 5, 10,10);
//            if(Math.sqrt((x - city.getX()) * (x - city.getX()) + (y - city.getY()) * (y - city.getY())) < 20) g2.setFont(focusFont);
//            else g2.setFont(regularFont);
//            g2.drawString(city.getName(), city.getX() - 5, city.getY() - 5);
//        }
//        if(addCity){
//            g2.setColor(Color.ORANGE);
//            g2.fillOval(x-3,y-3,6,6);
//        }
    }

    @Override
    void actionOnResizing(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        resizeMap();
    }

    private void resizeMap(){
        if(map.getWidth(null) > width || map.getHeight(null) > height){
            if (map.getWidth(null) > map.getHeight(null)){
                resizedMap = map.getScaledInstance(width, map.getHeight(null) * width / map.getWidth(null), Image.SCALE_DEFAULT);
                } else  resizedMap = map.getScaledInstance(map.getWidth(null) * height / map.getHeight(null), height, Image.SCALE_DEFAULT);
        } else if (map.getWidth(null) > map.getHeight(null)) {
            resizedMap = map.getScaledInstance(map.getWidth(null) * height / map.getHeight(null), height, Image.SCALE_DEFAULT);
        } else resizedMap = map.getScaledInstance(width, map.getHeight(null) * width / map.getWidth(null), Image.SCALE_DEFAULT);
    }
}
