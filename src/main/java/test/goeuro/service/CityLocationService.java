package test.goeuro.service;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import test.goeuro.clients.CityLocationClient;
import test.goeuro.models.City;
import test.goeuro.models.CityLocationCSVHeaders;
import test.goeuro.wrapper.FileWriter;

import java.io.IOException;
import java.util.List;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */

/**
 * <p>This is actual class which do all processing. It implements the Service interface. It gets the
 * api data, process it and store it in the file.</p>
 */
public class CityLocationService implements Service {

    private static final Logger logger = LoggerFactory.getLogger(CityLocationService.class.getName());

    private String city;

    @Autowired
    private CityLocationClient cityLocationClient;

    @Autowired
    private FileWriter fileWriter;

    /**
     * <p>Overriden method which gets called from the {@link test.goeuro.GoEuroApplication#run(String...)} method of
     * application before it calls {@link #execute()} method. Its accepts all the command line argements and sets
     * the corresponding values in the fields</p>
     * @param args list of command line arguments
     */
    @Override
    public void setArgs(String... args) {
        this.city = args[0];
        if(StringUtils.isBlank(city))
            throw new RuntimeException("City field can't be null");
    }

    /**
     * <p>Overriden method which gets called from the {@link test.goeuro.GoEuroApplication#run(String...)} method of
     * application for processing. It is actual method which do processing.</p>
     */
    @Override
    public void execute() {

        List<City> cityInfo = cityLocationClient.getCityLocations(city);

        try {

            fileWriter.printRecord(CityLocationCSVHeaders.getNames());

            fileWriter.printRecords(parseCityRecordsToList(cityInfo));

        } catch (IOException e) {
            logger.error("Exception while printing records {}", e);
        }

    }

    /**
     * <p>This method convert the list city object to the list of list of strings for writing as records to the file. </p>
     * @param cities Accepts list of city objects
     * @return {@link java.util.List} of {@link java.util.List} of {@link java.lang.String}
     */
    List<List<String>> parseCityRecordsToList(List<City> cities) {
        List<List<String>> cityIteratorList = Lists.newArrayList();

        for(City city: cities) {

            List<String> cityLocationInfo = Lists.newArrayList();

            cityLocationInfo.add(String.valueOf(city.getId()));

            cityLocationInfo.add(String.valueOf(city.getName()));

            cityLocationInfo.add(String.valueOf(city.getType()));

            cityLocationInfo.add(String.valueOf(city.getGeoPosition().getLatitude()));

            cityLocationInfo.add(String.valueOf(city.getGeoPosition().getLongitude()));

            cityIteratorList.add(cityLocationInfo);
        }

        return cityIteratorList;
    }
}
