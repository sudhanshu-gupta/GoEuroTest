package test.goeuro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import test.goeuro.clients.CityLocationClient;
import test.goeuro.service.CityLocationService;
import test.goeuro.service.Service;
import test.goeuro.wrapper.CSVWriter;
import test.goeuro.wrapper.FileWriter;

import java.io.IOException;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */

/**
 * <h3>GoEuroConfiguration class</h3>
 * <p>Common Configuration class to load beans of all necessary classes.
 * It also has property Source placeholder configuration bean to load bean while packaging.
 * It reads the property file placed at resources/config.properties.</p>
 */
@Configuration
@PropertySource("classpath:config.properties")
public class GoEuroConfiguration {

    /**
     * <p>Return a propertysourcesPlaceholderConfigurer object which is required to load the
     * beans and property values while compiling the project.</p>
     * @return PropertySourcesPlaceholderConfiguerer instance
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * <p>Return a spring rest template bean with the custom read and connect timeout values set
     * This internally calls the {@link #clientHttpRequestFactory(Integer)} to set the timout values.</p>
     * @param httpClientTimeout Custom value of timeout from config
     * @return RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate(@Value("${http.client.timeout}") Integer httpClientTimeout) {
        return new RestTemplate(clientHttpRequestFactory(httpClientTimeout));
    }

    /**
     * <p>Return a ClientHttpRequestFactory bean. This is required to provide external settings to
     * the rest template class</p>
     * @param httpClientTimeout Integer: Accepts the timeout value in Integer format
     * @return ClientHttpRequestFactory instance
     */
    private ClientHttpRequestFactory clientHttpRequestFactory(Integer httpClientTimeout) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(httpClientTimeout);
        factory.setConnectTimeout(httpClientTimeout);
        return factory;
    }

    /**
     * <p>Return a csvWriter bean which implements FileWriter interface. This is the wapper around the
     * apache csv library.</p>
     * @param csvOutputFile String: accepts the name of the output file where csv formatted data to be written
     * @return CSVWriter instance
     * @throws IOException throws exception while instantiating the instance
     */
    @Bean
    public FileWriter csvWriter(@Value("${output.file.name}") String csvOutputFile) throws IOException {
        return new CSVWriter(csvOutputFile);
    }

    /**
     * <p>Return a CityLocationClient bean. This class talks to server to get the data around the city and transform
     * the data to the java pojo form.</p>
     * @param cityLocationApi String: Accepts the city location api uri which is loaded from config
     * @return CityLocationClient class instance
     */
    @Bean
    public CityLocationClient cityLocationClient(@Value("${city.location.api}") String cityLocationApi) {
        return new CityLocationClient(cityLocationApi);
    }

    /**
     * <p>Return a CityLocationService bean. This class is responsible for actual computation of
     * server data and transforming to file output format.</p>
     * @return CityLocationService class instance
     */
    @Bean
    public Service cityLocationService() {
        return new CityLocationService();
    }
}
