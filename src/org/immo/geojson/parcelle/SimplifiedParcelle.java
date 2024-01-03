package org.immo.geojson.parcelle;

import org.immo.geojson.geometry.GeometryPolygon;

import java.util.List;
import java.util.Objects;

public class SimplifiedParcelle {
    private String id;
    private GeometryPolygon geometryPolygon;
    private List<Double> bbox ;
    private double distanceFromReference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeometryPolygon getGeometryPolygon() {
        return geometryPolygon;
    }

    public void setGeometryPolygon(GeometryPolygon geometryPolygon) {
        this.geometryPolygon = geometryPolygon;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public double getDistanceFromReference() {
        return distanceFromReference;
    }

    public void setDistanceFromReference(double distanceFromReference) {
        this.distanceFromReference = distanceFromReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplifiedParcelle that = (SimplifiedParcelle) o;
        return Double.compare(distanceFromReference, that.distanceFromReference) == 0 && Objects.equals(id, that.id)
                && Objects.equals(geometryPolygon, that.geometryPolygon)
                && Objects.equals(bbox, that.bbox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geometryPolygon, bbox, distanceFromReference);
    }
}
