package data.weather;

import java.util.Arrays;

public class Hourly {
    private String[] time;
    private float[] temperature_2m;
    private int[] weathercode;


    public Hourly() {
    }

    public Hourly(String[] time, float[] temperature_2m, int[] weathercode) {
        this.time = time;
        this.temperature_2m = temperature_2m;
        this.weathercode = weathercode;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public float[] getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(float[] temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public int[] getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(int[] weathercode) {
        this.weathercode = weathercode;
    }

    @Override
    public String toString() {
        return "Hourly{" +
                "time=" + Arrays.toString(time) +
                ", temperature_2m=" + Arrays.toString(temperature_2m) +
                ", weathercode=" + Arrays.toString(weathercode) +
                '}';
    }
}
