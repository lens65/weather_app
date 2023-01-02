package data;

public class Region {
    private String name;
    private String request;
    private Weather weather;

    public Region(String name, String request) {
        this.name = name;
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Oblast{" +
                "name='" + name + '\'' +
                ", weather=" + weather.toString() +
                '}';
    }
}
