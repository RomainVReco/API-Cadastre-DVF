package org.immo.geojson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AbstractGeometry<T> {
    @JsonProperty("type")
    String type;
    @JsonProperty("coordinates")
    List<T> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<T> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
