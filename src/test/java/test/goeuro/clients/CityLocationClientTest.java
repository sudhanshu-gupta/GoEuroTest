package test.goeuro.clients;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import test.goeuro.GoEuroConfiguration;
import test.goeuro.models.City;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by sudhanshu.gupta on 09/06/16.
 */

/**
 * <p>Test Class which check that server is responding and server data is parsing correctly.</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GoEuroConfiguration.class)
public class CityLocationClientTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Value("${city.location.api}")
    private String cityLocationUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CityLocationClient cityLocationClient;

    private List<City> expectedResponse;
    private List<City> actualResponse;

    /**
     * <p>Initializing method which get called before the test starts. It collects the data from server using
     * CityLocationClient as well as directly calling the server.</p>
     */
    @Before
    public void initialize() {

        String city = "acz";

        expectedResponse = Arrays.asList(restTemplate.getForObject(String.format(cityLocationUri, city), City[].class));

        actualResponse = cityLocationClient.getCityLocations(city);
    }

    /**
     * <p>Test whether data returned from the CityLocationClient is not null.</p>
     */
    @Test
    public void testCityLocationClientNotNull() {

        assertNotNull(actualResponse);
    }

    /**
     * <p>Test whether the size of the list of data returned directly from server and from client matches.</p>
     */
    @Test
    public void testCityLocationClientSizeEquals() {

        assertEquals(expectedResponse.size(), actualResponse.size());
    }

    /**
     * <p>Test the each of response value of data returned via both the medium should match. In case of non-match
     * test will fail.</p>
     */
    @Test
    public void testCityLocationClientObjectEquals() {

        for(int i=0; i<expectedResponse.size(); i++) {

            assertEquals(expectedResponse.get(i).getId(), actualResponse.get(i).getId());

            assertEquals(expectedResponse.get(i).getName(), actualResponse.get(i).getName());

            assertEquals(expectedResponse.get(i).getType(), actualResponse.get(i).getType());

            assertEquals(expectedResponse.get(i).getGeoPosition().getLatitude(), actualResponse.get(i).getGeoPosition().getLatitude());

            assertEquals(expectedResponse.get(i).getGeoPosition().getLongitude(), actualResponse.get(i).getGeoPosition().getLongitude());

        }
    }

    @Test
    public void testEmptyResponse() {

        List<City> response = cityLocationClient.getCityLocations("ss");

        assertEquals(0, response.size());
    }
}
