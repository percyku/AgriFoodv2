package com.agrifood.percyku.agrifood.tool;

/**
 * Created by percyku on 16/11/1.
 */
public class Nearby {

    private String name;
    private String arealocation;
    private String feature;
    private String TrafficGuidelines;
    private String latitude;
    private String longitude;
    private String distance;


    public Nearby(String name, String arealocation, String feature,String TrafficGuidelines, String latitude, String longitude, String distance) {


        this.name = name;
        this.arealocation = arealocation;
        this.feature=feature;
        this.TrafficGuidelines = TrafficGuidelines;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;

    }


    public String getNamw() {
        return name;
    }

    public String getArealocation() {
        return arealocation;
    }

    public String getFeature() {
        return feature;
    }

    public String getTrafficGuidelines() {
        return TrafficGuidelines;
    }

    public String getLat() {
        return latitude;
    }

    public String getLon() {
        return longitude;
    }

    public String getDistance() {
        return distance;
    }
}
