package org.immo.geojson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Geometry {
    @JsonProperty("type")
    String type;
    @JsonProperty("coordinates")
    List<Double> coordinates;


}

