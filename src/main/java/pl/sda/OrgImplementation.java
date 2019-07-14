package pl.sda;

import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

public class OrgImplementation implements WeatherForecast {

    private WeatherService weatherService;
    private String city;

    public OrgImplementation(WeatherService weatherService, String city) {
        this.weatherService = weatherService;
        this.city = city;
    }

    @Override
    public Weather getWeather() {

        Weather weather = new Weather();

        JSONObject jsonObject = new JSONObject(this.weatherService.getJSONData(this.city));

        String tempC = jsonObject.getJSONObject("current").get("temp_c").toString();
        String tempF = jsonObject.getJSONObject("current").get("temp_f").toString();
        String lastUpdate = jsonObject.getJSONObject("current").get("last_updated").toString();
        String feelsTempC = jsonObject.getJSONObject("current").get("feelslike_c").toString();
        String isDay = jsonObject.getJSONObject("current").get("is_day").toString();


        Current current = Current.builder()
                .temp_c(Double.parseDouble(tempC))
                .last_updated(lastUpdate)
                .feelslike_c(Double.parseDouble(feelsTempC))
                .is_day(Integer.parseInt(isDay))
                .build();


        String region = jsonObject.getJSONObject("location").get("region").toString();
        String country = jsonObject.getJSONObject("location").get("country").toString();
        String lat = jsonObject.getJSONObject("location").get("lat").toString();
        String lon = jsonObject.getJSONObject("location").get("lon").toString();
        String localTime = jsonObject.getJSONObject("location").get("localtime").toString();

        Location location = Location.builder()
                .region(region)
                .country(country)
                .lat(Double.parseDouble(lat))
                .lon(Double.parseDouble(lon))
                .localtime(localTime)
                .build();

        weather.setCurrent(current);
        weather.setLocation(location);

        return weather;
    }
}
