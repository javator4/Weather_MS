package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String url = "http://api.apixu.com/v1/current.json?key=e5434bc67a674701ac281204191307&q=Torun";
        WeatherService weatherService = new WeatherService("http://api.apixu.com/v1/current.json", "e5434bc67a674701ac281204191307");

        WeatherForecast weatherForecast = new OrgImplementation(weatherService, "Torun");
        WeatherForecast weatherForecast1 = new FasterImplementation(weatherService, "Torun");

        System.out.println(weatherForecast.getWeather());




    }
}
