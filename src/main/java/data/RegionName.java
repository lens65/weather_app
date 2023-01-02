package data;

public enum RegionName {
    Crimea("Крым", "https://api.open-meteo.com/v1/forecast?latitude=45.41&longitude=34.12&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Vinnytsia("Виннитская область", "https://api.open-meteo.com/v1/forecast?latitude=49.23&longitude=28.47&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Volyn("Волынская область", "https://api.open-meteo.com/v1/forecast?latitude=50.76&longitude=25.34&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Dnipro("Днепровская область", "https://api.open-meteo.com/v1/forecast?latitude=48.47&longitude=35.04&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Donetsk("Донетская область", "https://api.open-meteo.com/v1/forecast?latitude=48.02&longitude=37.80&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Zhytomyr("Житомирская область", "https://api.open-meteo.com/v1/forecast?latitude=50.26&longitude=28.68&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Zakarpattia("Закарпатская область", "https://api.open-meteo.com/v1/forecast?latitude=48.62&longitude=22.29&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Zaporizhzhia("Запорожская область", "https://api.open-meteo.com/v1/forecast?latitude=46.96&longitude=35.04&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    IvanoFrankivsk("Ивано-Франковская область","https://api.open-meteo.com/v1/forecast?latitude=48.92&longitude=24.71&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Kyiv("Киевская область", "https://api.open-meteo.com/v1/forecast?latitude=50.45&longitude=30.52&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Kirovohrad("Кировоградская область", "https://api.open-meteo.com/v1/forecast?latitude=48.54&longitude=32.28&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Luhansk("Луганская область", "https://api.open-meteo.com/v1/forecast?latitude=48.57&longitude=39.32&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Lviv("Львовская область", "https://api.open-meteo.com/v1/forecast?latitude=49.84&longitude=24.02&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Mykolaiv("Николаевская область", "https://api.open-meteo.com/v1/forecast?latitude=46.98&longitude=31.99&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Odesa("Одесская область", "https://api.open-meteo.com/v1/forecast?latitude=46.43&longitude=30.68&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Poltava("Полтавская область", "https://api.open-meteo.com/v1/forecast?latitude=49.59&longitude=34.55&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Rivne("Ровненская область", "https://api.open-meteo.com/v1/forecast?latitude=50.62&longitude=26.23&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Sumy("Сумская область", "https://api.open-meteo.com/v1/forecast?latitude=50.92&longitude=34.80&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Ternopil("Тернопольская область", "https://api.open-meteo.com/v1/forecast?latitude=49.55&longitude=25.59&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Kharkiv("Харьковская область", "https://api.open-meteo.com/v1/forecast?latitude=49.98&longitude=36.25&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Kherson("Херсонская область", "https://api.open-meteo.com/v1/forecast?latitude=46.64&longitude=32.61&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Khmelnytskyi("Хмельницкая область", "https://api.open-meteo.com/v1/forecast?latitude=49.36&longitude=26.93&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Chernivtsi("Черновицкая область", "https://api.open-meteo.com/v1/forecast?latitude=48.29&longitude=25.93&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Chernihiv("Черниговская область", "https://api.open-meteo.com/v1/forecast?latitude=51.51&longitude=31.28&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo"),
    Cherkasy("Черкасская область", "https://api.open-meteo.com/v1/forecast?latitude=49.44&longitude=32.06&hourly=temperature_2m,relativehumidity_2m,precipitation,windspeed_10m&timezone=Africa%2FCairo");

    private String name;
    private String request;
    private Weather weather;


    RegionName(String name, String request){
        this.name = name;
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public String getRequest() {
        return request;
    }

    public void setWeather(Weather weather){
        this.weather = weather;
    }

    public Weather getWeather(){
        return weather;
    }
}
