package pl.sda;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

@Data
@NoArgsConstructor
public abstract class AbstractJsonData {

    private String site;
    private String key;
    private String finalURL;
    private String data = "";

    public AbstractJsonData(String site) {
        this.finalURL = this.site + "?key=" + this.key;
    }

    public void build(){
        this.finalURL = this.site + "?key=" + this.key;
    }

    abstract Weather getWeather();


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
