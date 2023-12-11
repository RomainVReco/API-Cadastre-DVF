package org.immo.geojson.parcelle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immo.geojson.adresseban.FeatureAdresseBAN;

import java.sql.Timestamp;
import java.util.List;

public class Parcelle {
    private String type;
    @JsonProperty("features")
    private List<FeatureParcelle> featuresParcelle;
    private int totalFeatures;
    private int numberMatched;
    @JsonProperty("numberReturned")
    private int numberReturned;
    private String timeStamp;
    @JsonProperty("links")
    private List<Links> links;
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

    public int getNumberReturned() {
        return numberReturned;
    }

    public List<Links> getLinks() {
        return links;
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

    public String convertBboxToString(){
        StringBuilder bbox = new StringBuilder();
        for (Double point: this.getBbox()) {
            bbox.append(point).append(",");
        }
        return bbox.substring(0, bbox.length()-1);
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public String showParcelleContent() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String limitLine = "\n################################################\n\n";
        System.out.println(getFeaturesParcelle().size());
        for (FeatureParcelle feature : getFeaturesParcelle()) {
            sb.append("Parcelle id : ").append(feature.getId()).append(",\n");
            sb.append("Parcelle idu : ").append(feature.getParcelleProperties().getIdu()).append(",\n");
            sb.append("Contenance : ").append(feature.getParcelleProperties().getContenance()).append("m²").append(",\n");
            sb.append("Border box : ").append(this.convertBboxToString());
            if (!(i == getFeaturesParcelle().size()-1)) {
                sb.append(limitLine);
            }
            i++;
        }
        return sb.toString();
    }
}
