package org.immo.geojson.geometry;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeometryPoint extends AbstractGeometry<List<Double>>{

    @Override
    public String toString() {
        return "{\"type\": \"Point\",\"coordinates\":"+coordinates+"}";
    }
}

