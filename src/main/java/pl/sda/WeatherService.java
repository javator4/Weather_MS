package pl.sda;

import pl.sda.model.Current;

public class WeatherService {
    private String site;
    private String key;
    private String finalURL;

    public WeatherService(String site, String key) {
        this.site = site;
        this.key = key;
        finalURL = site + "?key=" + key;
    }

    public Current getCityWeather(String city) {
        finalURL = finalURL + "&q=" + city;
        return new Current();
    }
}