package pl.sda;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Condition;
import pl.sda.model.Current;
import pl.sda.model.Location;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {
    private String site;
    private String key;
    private String finalURL;
    private String data;

    public WeatherService(String site, String key) {
        this.site = site;
        this.key = key;
        this.finalURL = this.site + "?key=" + this.key;
    }

    public Location getLocation(){

        JSONObject jsonObject = new JSONObject(data);

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

        return location;
    }

    public WeatherService getJSONData(String city){
        this.finalURL = this.finalURL + "&q=" + city;
        String data = "";
        try {
            data = IOUtils.toString(new URL(this.finalURL), Charset.forName("UTF-8"));
            JSONObject jsonObject = new JSONObject(data);

        }  catch (IOException e) {
            e.printStackTrace();
        }
        this.data = data;
        return this;
    }

    public Current getCityWeather() {

            JSONObject jsonObject = new JSONObject(data);

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

            return current;
        }

}