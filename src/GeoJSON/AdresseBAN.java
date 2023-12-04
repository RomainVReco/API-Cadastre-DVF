package GeoJSON;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*
* Objet utilisé pour la récupération d'une FeatureCollection de l'API Adresse
* */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdresseBAN {
    String type;
    String version;
    List<Feature> feature;
    String attribution;
    String licence;
    String query;
    int limit;

    public AdresseBAN() {
    }

    public List<Feature> getFeature() {
        return feature;
    }

    public void setFeature(List<Feature> feature) {
        this.feature = feature;
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
                ", feature=" + feature +
                ", attribution='" + attribution + '\'' +
                ", licence='" + licence + '\'' +
                ", query='" + query + '\'' +
                ", limit=" + limit +
                '}';
    }
}
