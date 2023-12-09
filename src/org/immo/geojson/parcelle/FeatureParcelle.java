package org.immo.geojson.parcelle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immo.geojson.geometry.GeometryPolygon;

public class FeatureParcelle {
    private String type;
    private String id;
    private GeometryPolygon geometry;
    @JsonProperty("geometry_name")
    private String geometryName;
    @JsonProperty("properties")
    private ParcelleProperties parcelleProperties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeometryPolygon getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryPolygon geometry) {
        this.geometry = geometry;
    }

    public String getGeometryName() {
        return geometryName;
    }

    public void setGeometryName(String geometryName) {
        this.geometryName = geometryName;
    }

    public ParcelleProperties getParcelleProperties() {
        return parcelleProperties;
    }

    public void setParcelleProperties(ParcelleProperties parcelleProperties) {
        this.parcelleProperties = parcelleProperties;
    }
}
