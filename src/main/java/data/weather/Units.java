package data.weather;

public class Units {
    private String time;
    private String temperature_2m;
    private String relativehumidity_2m;
    private String precipitation;
    private String windspeed_10m;

    public Units() {
    }

    public Units(String time, String temperature_2m, String relativehumidity_2m, String precipitation, String windspeed_10m) {
        this.time = time;
        this.temperature_2m = temperature_2m;
        this.relativehumidity_2m = relativehumidity_2m;
        this.precipitation = precipitation;
        this.windspeed_10m = windspeed_10m;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(String temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public String getRelativehumidity_2m() {
        return relativehumidity_2m;
    }

    public void setRelativehumidity_2m(String relativehumidity_2m) {
        this.relativehumidity_2m = relativehumidity_2m;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getWindspeed_10m() {
        return windspeed_10m;
    }

    public void setWindspeed_10m(String windspeed_10m) {
        this.windspeed_10m = windspeed_10m;
    }

    @Override
    public String toString() {
        return "Units{" +
                "time='" + time + '\'' +
                ", temperature_2m='" + temperature_2m + '\'' +
                ", relativehumidity_2m='" + relativehumidity_2m + '\'' +
                ", precipitation='" + precipitation + '\'' +
                ", windspeed_10m='" + windspeed_10m + '\'' +
                '}';
    }
}
