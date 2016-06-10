package test.goeuro.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import test.goeuro.models.City;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */

/**
 * <p>This class talks to server to get the data around the city and transform
 * the data to the java pojo form.</p>
 */
public class CityLocationClient {

    private String cityLocationURI;

    @Autowired
    private RestTemplate restTemplate;

    public CityLocationClient(String cityLocationURI) {
        this.cityLocationURI = cityLocationURI;
    }

    /**
     * <p>This method talks to server to get city data and transform the data to List of City and return it.</p>
     * @param city Name of city for which data is required
     * @return {@link java.util.List<test.goeuro.models.City>}
     */
    public List<City> getCityLocations(String city) {
        City[] cities = restTemplate.getForObject(String.format(cityLocationURI, city), City[].class);
        return Arrays.asList(cities);
    }
}
