package org.immo.geojson.parcelle;

public class Crs {
    private String type;
    private CrsProperties crsProperties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CrsProperties getCrsProperties() {
        return crsProperties;
    }

    public void setCrsProperties(CrsProperties crsProperties) {
        this.crsProperties = crsProperties;
    }
}
