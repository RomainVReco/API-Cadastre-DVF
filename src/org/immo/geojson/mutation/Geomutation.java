package org.immo.geojson.mutation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Geomutation {
    @JsonProperty("type")
    private String type;
    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("features")
    private List<FeatureMutation> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<FeatureMutation> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureMutation> features) {
        this.features = features;
    }
}
