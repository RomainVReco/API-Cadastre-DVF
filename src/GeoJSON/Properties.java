package GeoJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {
        @JsonProperty("label")
        private String label;
        @JsonProperty("score")
        private double score;
        @JsonProperty("housenumber")
        private String housenumber;
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("postcode")
        private String postcode;
        @JsonProperty("citycode")
        private String citycode;
        @JsonProperty("x")
        private double x;
        @JsonProperty("y")
        private double y;
        @JsonProperty("city")
        private String city;
        @JsonProperty("district")
        private String district;
        @JsonProperty("context")
        private String context;
        @JsonProperty("type")
        private String propertyType;
        @JsonProperty("importance")
        private double importance;
        @JsonProperty("street")
        private String street;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
