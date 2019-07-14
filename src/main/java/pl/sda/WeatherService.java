package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Condition;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {
    private String site;
    private String key;
    private String finalURL;
    private String data = "";

    public WeatherService(String site, String key) {
        this.site = site;
        this.key = key;
        this.finalURL = this.site + "?key=" + this.key;
    }


    public String getJSONData(String city){
        if(this.data.isEmpty()) {
            this.finalURL = this.finalURL + "&q=" + city;
            String data = "";
            try {
                data = IOUtils.toString(new URL(this.finalURL), Charset.forName("UTF-8"));
                JSONObject jsonObject = new JSONObject(data);

            } catch (IOException e) {
                e.printStackTrace();
            }
            this.data = data;
        }
            return data;

    }




}