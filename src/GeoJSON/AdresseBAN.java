package GeoJSON;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
* Objet utilisé pour la récupération d'une FeatureCollection de l'API Adresse
* */

public class AdresseBAN {
    @JsonProperty("type")
    String type;
    @JsonProperty("version")
    String version;
    @JsonProperty("features")
    List<Feature> features;
    String attribution;
    String licence;
    String query;
    int limit;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> feature) {
        this.features = feature;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "AdresseBAN{" +
                "type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", features=" + features +
                ", attribution='" + attribution + '\'' +
                ", licence='" + licence + '\'' +
                ", query='" + query + '\'' +
                ", limit=" + limit +
                '}';
    }
}
