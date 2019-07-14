package pl.sda;

import pl.sda.model.Current;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        // http://api.apixu.com/v1/current.json?key=e5434bc67a674701ac281204191307&q=Paris
        WeatherService weatherService = new WeatherService("http://api.apixu.com/v1/current.json", "e5434bc67a674701ac281204191307");


        System.out.println(weatherService.getJSONData("Torun").getCityWeather());
        System.out.println(weatherService.getJSONData("Torun").getLocation());

    }
}
