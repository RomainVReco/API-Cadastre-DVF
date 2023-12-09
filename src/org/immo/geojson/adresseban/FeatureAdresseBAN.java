package org.immo.geojson.adresseban;

import org.immo.geojson.Geometry;

public class FeatureAdresseBAN {
    String type;
    Geometry geometry;
    AdressProperties properties;

    public String getType() {
        return type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public AdressProperties getProperties() {
        return properties;
    }

    public void setProperties(AdressProperties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "type='" + type + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
