package application;

import data.country.Country;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CountryInformation extends JPanel implements Runnable{
    private Thread thread;
    private final int FPS;
    private Country country;
    private BufferedImage map;
    private MouseHandler mouseHandler;

    public CountryInformation(Country country){
        this.country = country;
        setMap();
        mouseHandler = new MouseHandler();
        FPS = 30;
        setPanel();
    }

    public void startShowingInfo(){
        thread = new Thread(this);
        thread.start();
    }

    private void setPanel(){
        this.setFocusable(true);
        this.setBounds(0,0,1300,950);
        this.setBackground(new Color(32, 33, 36));
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);
    }
    private void setMap(){
        try {
//            map = ImageIO.read(new File("src\\main\\resources\\ukraine.png"));
            map = ImageIO.read(new URL(country.getMap()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(map, 0 ,0, this);
    }
}
