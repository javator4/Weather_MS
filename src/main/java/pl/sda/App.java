package pl.sda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;

public class App 
{
    private static Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        logger.info("Uruchomienie aplikacji");
        logger.warn("WARNING!");
        logger.debug(("DEBUG"));
        logger.error("ERROR");

        String url = "http://api.apixu.com/v1/current.json?key=e5434bc67a674701ac281204191307&q=Torun";
        WeatherService weatherService = new WeatherService("http://api.apixu.com/v1/current.json", "e5434bc67a674701ac281204191307");

        WeatherForecast weatherForecast = new OrgImplementation(weatherService, "Torun");
        WeatherForecast weatherForecast1 = new FasterImplementation(weatherService, "Torun");

        System.out.println(weatherForecast1.getWeather());
        System.out.println(weatherForecast1.getWeather());

        JsonDataFaster jsonDataFaster = new JsonDataFaster();
        jsonDataFaster.setSite("http://api.apixu.com/v1/current.json");
        jsonDataFaster.setKey("e5434bc67a674701ac281204191307");
        jsonDataFaster.setCity("Warszawa");
        jsonDataFaster.build();

        System.out.println(jsonDataFaster.getWeather());

        System.out.println("TEST");

        JsonDataOrg jsonDataOrg = new JsonDataOrg();
        jsonDataOrg.setKey("e5434bc67a674701ac281204191307");
        jsonDataOrg.setSite("http://api.apixu.com/v1/current.json");
        jsonDataOrg.setCity("Bydgoszcz");
        jsonDataOrg.build();
        System.out.println(jsonDataOrg.getWeather());

        //todo Udoskonalić projekt. Użytkownik ma wpisać miasto i pogoda ma zostac wyswietlona

    }
}
