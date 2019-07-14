package pl.sda;

import org.apache.log4j.Logger;


/**
 * Hello world!
 *
 */
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




    }
}
