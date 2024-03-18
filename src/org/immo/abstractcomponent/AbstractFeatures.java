package org.immo.abstractcomponent;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immo.geojson.geometry.GeometryPolygon;

import java.util.List;

public abstract class AbstractFeatures<T> {
    private String type;
    private String id;
    private GeometryPolygon geometry;
    @JsonProperty("geometry_name")
    private String geometryName;
    @JsonProperty("properties")
    private T terrainProperties;

    @JsonProperty("bbox")
    private List<Double> bbox ;

    public List<Double> getBbox() {
        return bbox;
    }

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

    public T getTerrainProperties() {
        return terrainProperties;
    }

    public void setParcelleProperties(T parcelleProperties) {
        this.terrainProperties = parcelleProperties;
    }

    /**
     * Ajout suite modification API Cadastre le 18/03/2024
     * @return
     */
    public String convertBboxToString(){
        StringBuilder bbox = new StringBuilder();
        try {
            for (Double point: this.getBbox()) {
                bbox.append(point).append(",");
            }
        } catch (NullPointerException e){
            System.out.println("Pas de résultat pour la conversion de la Bbox en String");
            bbox.append("empty");
        }

        return bbox.substring(0, bbox.length()-1);
    }
}
