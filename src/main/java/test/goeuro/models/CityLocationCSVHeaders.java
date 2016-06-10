package test.goeuro.models;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */
public enum CityLocationCSVHeaders {

    _id, name, type, latitude, longitude;

    public static List<String> getNames() {
        return Stream.of(CityLocationCSVHeaders.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
