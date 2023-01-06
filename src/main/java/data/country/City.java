package data.country;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;
    @Column(name = "time")
    private String time;
    @Column(name = "weather")
    private String jsonWeather;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    public City() {
    }

    public City(String name, double latitude, double longitude, int x, int y, Country country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.x = x;
        this.y = y;
        this.country = country;
    }

    public City(String name, double latitude, double longitude, int x, int y, String time, String jsonWeather) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.x = x;
        this.y = y;
        this.time = time;
        this.jsonWeather = jsonWeather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJsonWeather() {
        return jsonWeather;
    }

    public void setJsonWeather(String jsonWeather) {
        this.jsonWeather = jsonWeather;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
