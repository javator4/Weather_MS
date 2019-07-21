package pl.sda;


import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
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
            try {
                data = IOUtils.toString(new URL(this.finalURL), Charset.forName("UTF-8"));
                JSONObject jsonObject = new JSONObject(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return data;
    }
}