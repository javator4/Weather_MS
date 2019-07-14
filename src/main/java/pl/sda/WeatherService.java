package pl.sda;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Condition;
import pl.sda.model.Current;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {
    private String site;
    private String key;
    private String finalURL;

    public WeatherService(String site, String key) {
        this.site = site;
        this.key = key;
        this.finalURL = this.site + "?key=" + this.key;
    }

    public Current getCityWeather(String city) {
        this.finalURL = this.finalURL + "&q=" + city;

        try {
            String data = IOUtils.toString(new URL(this.finalURL), Charset.forName("UTF-8"));

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


//            current.setTemp_c(Double.parseDouble(tempC));
//            current.setLast_updated(lastUpdate);
//            current.setTemp_f(Double.parseDouble(tempF));
//            current.setFeelslike_c(Double.parseDouble(feelsTempC));
//            current.setIs_day(Integer.parseInt(isDay));

            return current;

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

}