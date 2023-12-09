package org.immo.geojson.parcelle;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class Parcelle {
    private String type;
    @JsonProperty("features")
    private List<FeatureParcelle> featuresParcelle;
    private int totalFeatures;
    private int numberMatched;
    private String timeStamp;
    private Crs crs ;
    @JsonProperty("bbox")
    private List<Double> bbox ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FeatureParcelle> getFeaturesParcelle() {
        return featuresParcelle;
    }

    public void setFeaturesParcelle(List<FeatureParcelle> featuresParcelle) {
        this.featuresParcelle = featuresParcelle;
    }

    public int getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(int totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public int getNumberMatched() {
        return numberMatched;
    }

    public void setNumberMatched(int numberMatched) {
        this.numberMatched = numberMatched;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Crs getCrs() {
        return crs;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }
}
