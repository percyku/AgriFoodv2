package com.agrifood.percyku.agrifood.tool;

/**
 * Created by percyku on 16/11/1.
 */
public class AgriGood {


    private String name;
    private String spot;
    private String latitude;
    private String longitude;
    private String distance;


    public AgriGood(String name, String spot, String latitude, String longitude, String distance){


        this.name=name;
        this.spot=spot;
        this.latitude=latitude;
        this.longitude=longitude;
        this.distance=distance;

    }


    public String getNamw() {
        return name;
    }

    public String getSpot() {
        return spot;
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
