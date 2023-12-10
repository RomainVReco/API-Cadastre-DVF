package org.immo.geojson.mutation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immo.geojson.geometry.GeometryPolygon;

import java.util.List;

public class FeatureMutation {
    /**
     * L'id correspond à l'idmutation
     */
   @JsonProperty("id")
   private int id;
   @JsonProperty("type")
   private String type;
   @JsonProperty("geometry")
   private GeometryPolygon geometry;
   @JsonProperty("properties")
   GeomutationPoperties geomutationPoperties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeometryPolygon getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryPolygon geometry) {
        this.geometry = geometry;
    }

    public GeomutationPoperties getGeomutationPoperties() {
        return geomutationPoperties;
    }

    public void setGeomutationPoperties(GeomutationPoperties geomutationPoperties) {
        this.geomutationPoperties = geomutationPoperties;
    }
}
