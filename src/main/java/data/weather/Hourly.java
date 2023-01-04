package data.weather;

import java.util.Arrays;

public class Hourly {
    private String[] time;
    private float[] temperature_2m;
    private int[] relativehumidity_2m;
    private double[] precipitation;
    private float[] windspeed_10m;

    public Hourly() {
    }

    public Hourly(String[] time, float[] temperature_2m, int[] relativehumidity_2m, double[] precipitation, float[] windspeed_10m) {
        this.time = time;
        this.temperature_2m = temperature_2m;
        this.relativehumidity_2m = relativehumidity_2m;
        this.precipitation = precipitation;
        this.windspeed_10m = windspeed_10m;
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

    public int[] getRelativehumidity_2m() {
        return relativehumidity_2m;
    }

    public void setRelativehumidity_2m(int[] relativehumidity_2m) {
        this.relativehumidity_2m = relativehumidity_2m;
    }

    public double[] getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double[] precipitation) {
        this.precipitation = precipitation;
    }

    public float[] getWindspeed_10m() {
        return windspeed_10m;
    }

    public void setWindspeed_10m(float[] windspeed_10m) {
        this.windspeed_10m = windspeed_10m;
    }

    @Override
    public String toString() {
        return "Hours{" +
                "time=" + Arrays.toString(time) +
                ", temperature_2m=" + Arrays.toString(temperature_2m) +
                ", relativehumidity_2m=" + Arrays.toString(relativehumidity_2m) +
                ", precipitation=" + Arrays.toString(precipitation) +
                ", windspeed_10m=" + Arrays.toString(windspeed_10m) +
                '}';
    }
}
