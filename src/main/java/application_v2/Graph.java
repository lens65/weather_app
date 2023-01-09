package application_v2;

import data.country.City;
import data.weather.Weather;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Graph {
    //получить одно общее изображение
    public static BufferedImage[] getWeathersImages(City city, Weather weather, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        BufferedImage top = getTopImage(city, weather, width, height * 2/ 10);
        BufferedImage days = getDaysImage(weather,width,height * 2 / 10);
        BufferedImage graph = getGraphImage(weather.getHourly().getTemperature_2m(), width, height * 6 / 10);
        g.drawImage(days,0,0, null);
        g.drawImage(graph, 0, height * 2 / 8, null);
        return new BufferedImage[]{top, days, graph};
    }
    //получить изображение графика с сеткой
    public static BufferedImage getGraphImage(float[] yAxis, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        int[]maxAndMin = getMaxAndMin(yAxis);
        float step = ((float) maxAndMin[1] - (float)maxAndMin[0]) / 3.0f;
        Font font = new Font("Serif", Font.BOLD, (int)(width * 0.02));
        int widthOfGraph = (int)(width * 0.92);
        int heightOfGraph = (int)(height * 0.92);
        //сетка горизонтальная
        for(int i = 0; i < 4; i++){
            g.setColor(Color.white);
            g.setFont(font);
            g.drawString(String.valueOf((float) Math.floor((maxAndMin[1] - step * i) * 10) / 10),0,font.getSize()  + heightOfGraph * i / 3);
            g.drawLine(font.getSize() * 2, font.getSize() + heightOfGraph * i / 3, font.getSize() * 2 + widthOfGraph, font.getSize() + heightOfGraph * i / 3);
        }
        //сетка вертикальная
        for(int i = 1; i <= 14; i++){
            if(i % 2 == 0) g.drawLine(font.getSize() * 2 + i * widthOfGraph / 14,0,font.getSize() * 2 + i * widthOfGraph / 14,font.getSize() + heightOfGraph);
            else g.drawLine(font.getSize() * 2 + i * widthOfGraph / 14,font.getSize(),font.getSize() * 2 + i * widthOfGraph / 14,font.getSize() + heightOfGraph);
        }
        //кривая графика
        g.setColor(new Color(3, 236, 252));
        for(int i = 0; i < yAxis.length - 1; i++){
            g.drawLine(
                    font.getSize() * 2 + i * widthOfGraph / yAxis.length,
                    font.getSize() + (int)(heightOfGraph * Math.abs(maxAndMin[1] - yAxis[i]) / Math.abs(maxAndMin[1] - maxAndMin[0])),
                    font.getSize() * 2 + (i + 1) * widthOfGraph / yAxis.length,
                    font.getSize() + (int)(heightOfGraph * Math.abs(maxAndMin[1] - yAxis[i + 1]) / Math.abs(maxAndMin[1] - maxAndMin[0]))
                    );
        }
        return image;
    }
    public static int[] getMaxAndMin(float[] numbers){
        //{нижнее значение графика, верхнее значение графика}
        int[] maxAndMin = new int[2];
        float max = -50.0f;
        float min = 50.0f;
        int maxInt;
        int minInt;
        for(float f : numbers){
            if(max < f) max = f;
            if(min > f) min = f;
        }
        maxInt = (int)Math.ceil(max);
        minInt = (int)Math.floor(min);
        if(Math.abs(maxInt - minInt) == 0){
            maxAndMin[0] = minInt;
            maxAndMin[1] = minInt + 1;
        } else if(Math.abs(maxInt - minInt) == 3){
            maxAndMin[0] = minInt;
            maxAndMin[1] = maxInt + 1;
        } else {
            maxAndMin[0] = minInt;
            maxAndMin[1] = maxInt;
        }
        return maxAndMin;
    }
    //получить часть изображение со днями
    public static BufferedImage getDaysImage(Weather weather, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        Font font = new Font("Serif", Font.BOLD, (int)(width * 0.02));
        int widthContent = (int)(width * 0.92);
        g.setColor(Color.white);
        g.setFont(font);
        for(int i = 0; i < 7; i++){
            //линия разделения дней
            g.drawLine(
                    font.getSize() * 2 + (i + 1) * widthContent / 7,
                    0,
                    font.getSize() * 2 + (i + 1) * widthContent / 7,
                    height);
            //дата
            g.drawString(
                    weather.getHourly().getTime()[1 + i * 24].replaceAll("(\\d{4})(-)(\\d{2})(-)(\\d{2})(T\\d{2}:\\d{2})","$5.$3"),
                    font.getSize() * 2 + i * widthContent / 7,
                    font.getSize()
                    );
            //картинка погоды
            g.drawImage(
                    getWeatherIcon(weather.getHourly().getWeathercode()[12 + i * 24], font.getSize() * 2),
                    font.getSize() * 2 + i * widthContent / 7,
                    font.getSize() ,
                    null);
            int[]maxAndMin = getMaxAndMin(Arrays.copyOfRange(weather.getHourly().getTemperature_2m(), i * 24, i * 24 + 23));
            //минимальная температура
            g.drawString(
                    "Мин." + String.valueOf(maxAndMin[0]),
                    font.getSize() * 2 + i * widthContent / 7 ,
                    font.getSize() * 4
            );
            //максимальная температура
            g.drawString(
                    "Макс." + String.valueOf(maxAndMin[1]),
                    font.getSize() * 2 + i * widthContent / 7,
                    font.getSize() * 5
            );
        }

        return image;
    }
    public static Image getWeatherIcon(int code, int size){
        BufferedImage icon = null;
        try {
            if(code == 0) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\0.png"));
            else if(code == 1) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\1.png"));
            else if(code == 2) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\2.png"));
            else if(code == 3) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\3.png"));
            else if(code == 45 || code == 48) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\45.png"));
            else if(code == 51 || code == 53 || code == 55) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\51.png"));
            else if(code == 56 || code == 57) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\56.png"));
            else if(code == 61 || code == 63 || code == 65) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\61.png"));
            else if(code == 71 || code == 73 || code == 75) icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\71.png"));
            else icon = ImageIO.read(new File("src\\main\\resources\\weather_code_images\\none.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return icon.getScaledInstance(size,size,Image.SCALE_DEFAULT);
    }
    public static BufferedImage getTopImage(City city, Weather weather, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        Font font = new Font("Serif", Font.BOLD, (int)(width * 0.02));
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(city.getName() + ",", 0,font.getSize());
        g.drawString(city.getCountry().getName(), 0, font.getSize() * 2);
        String hour = new SimpleDateFormat("HH").format(new Date());
        g.drawString( hour + ":00    " + weather.getHourly().getTemperature_2m()[Integer.parseInt(hour)], 0, font.getSize() * 3);
        return image;
    }
}
