package test.goeuro.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    @JsonProperty("_id")
    private Long _id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("geo_position")
    private GeoPosition geoPosition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("_id", _id)
                .append("name", name)
                .append("type", type)
                .append("geoPosition", geoPosition)
                .toString();
    }
}