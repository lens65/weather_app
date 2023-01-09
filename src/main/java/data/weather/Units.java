package data.weather;

public class Units {
    private String time;
    private String temperature_2m;
    private String weathercode;


    public Units() {
    }

    public Units(String time, String temperature_2m, String weathercode) {
        this.time = time;
        this.temperature_2m = temperature_2m;
        this.weathercode = weathercode;
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

    public String getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(String weathercode) {
        this.weathercode = weathercode;
    }

    @Override
    public String toString() {
        return "Units{" +
                "time='" + time + '\'' +
                ", temperature_2m='" + temperature_2m + '\'' +
                ", weathercode='" + weathercode + '\'' +
                '}';
    }
}
