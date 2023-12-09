package org.immo.geojson.parcelle;

import org.immo.geojson.Geometry;

public class FeatureParcelle {
    private String type;
    private String id;
    private Geometry geometry;
    private String geometryName;
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

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
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
