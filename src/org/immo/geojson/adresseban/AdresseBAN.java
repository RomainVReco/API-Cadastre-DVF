package org.immo.geojson.adresseban;


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
    List<FeatureAdresseBAN> featureAdresseBAN;
    String attribution;
    String licence;
    String query;
    int limit;

    public List<FeatureAdresseBAN> getFeatures() {
        return featureAdresseBAN;
    }

    public void setFeatures(List<FeatureAdresseBAN> featureAdresseBAN) {
        this.featureAdresseBAN = featureAdresseBAN;
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
                ", features=" + featureAdresseBAN +
                ", attribution='" + attribution + '\'' +
                ", licence='" + licence + '\'' +
                ", query='" + query + '\'' +
                ", limit=" + limit +
                '}';
    }
}
