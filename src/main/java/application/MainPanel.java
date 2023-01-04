package application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class MainPanel extends JPanel implements Runnable {

    private final int WIDTH  = 1300;
    private final int HEIGHT = 950;
    private final int FPS = 30;
    private Thread thread;

    private boolean showMap = false;
    private boolean showWeatherInfo = false;

    private BufferedImage map = null;
    private BufferedImage mapSolid = null;
    private BufferedImage mapColored = null;
//    private BufferedImage selectedColor = null;
    private MouseHandler mouseHandler = new MouseHandler();
    private int x;
    private int y;
    private int color;

    private Font font = new Font("Serif", Font.PLAIN, 30);
    private Font font1 = new Font("Serif", Font.PLAIN, 15);


    public MainPanel(){
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseHandler);
        setDefaultValues();
    }

    private void setDefaultValues(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            map = ImageIO.read(new File("src\\main\\resources\\Map.png"));
            mapColored = ImageIO.read(new File("src\\main\\resources\\Map_colored.png"));
            mapSolid = ImageIO.read(new File("src\\main\\resources\\Map_solid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startDrawing(){
        thread = new Thread(this);
        thread.start();
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
        if(showMap) updateCursorPosition();



        color = mapColored.getRGB(x, y);

//        System.out.println(mapSolid.getRGB(x, y));

//        try {
//            selectedColor = ImageIO.read(new File("src\\main\\resources\\transparent.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for(int i = 0; i < mapColored.getHeight(); i+=10){
//            for(int j = 0; j < mapColored.getWidth(); j+=10){
//                if(oblastColor.keySet().contains(color)) if(mapColored.getRGB(j, i) == color) selectedColor.setRGB(j, i, Color.WHITE.getRGB());
//            }
//        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(showMap) drawMap(g2);
//        g2.drawImage(selectedColor, 0 ,0, this);

//        g2.setColor(new Color(217, 217, 217));
//        g2.fillRoundRect(0, HEIGHT - 350, 400, 300, 20,20);
//        g2.setColor(new Color(110, 110, 110, 200));
//        g2.fillRoundRect(5, HEIGHT - 345, 390, 290,20,20);


//        if(oblastColor.keySet().contains(color)) {
//            g2.setColor(Color.white);
//            g2.setFont(font);
//            g2.drawString(oblastColor.get(color).getName(), 25, HEIGHT - 315);
//            g2.setColor(Color.white);
//            g2.setFont(font);
//            g2.drawString(oblastColor.get(color).getName(), 25, HEIGHT - 315);
//            g2.setFont(font1);
//            g2.drawString(time.toString(), 25, HEIGHT - 295);
//            Weather weather = oblastColor.get(color).getWeather();
//
//            int i = -1;
//            int k = 0;
//            for(String str : weather.getHourly().getTime()){
//                i++;
//                if(str.equals(time.toString())) k = i;
//            }
//            g2.drawString(String.valueOf(weather.getHourly().getTemperature_2m()[k]), 25, HEIGHT - 275);
//        }

    }



    private void updateCursorPosition(){
        x = mouseHandler.x;
        y = mouseHandler.y;
        if(x >= mapColored.getWidth()) x = mapColored.getWidth() - 1;
        if(y >= mapColored.getHeight()) y = mapColored.getHeight() - 1;
    }

    private void drawMap(Graphics2D g2){
        g2.drawImage(map, 0 ,0, this);
    }
}
