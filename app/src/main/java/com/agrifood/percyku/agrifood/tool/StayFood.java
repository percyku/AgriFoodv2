package com.agrifood.percyku.agrifood.tool;

/**
 * Created by percyku on 16/11/1.
 */
public class StayFood {

    private String name;
    private String tel;
    private String hostWords;
    private String stayFeature;
    private String latitude;
    private String longitude;
    private String distance;


    public StayFood(String name, String tel,String hostWords, String stayFeature, String latitude, String longitude, String distance) {


        this.name = name;
        this.tel = tel;
        this.hostWords=hostWords;
        this.stayFeature = stayFeature;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;

    }


    public String getNamw() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getHostWords() {
        return hostWords;
    }

    public String getStayFeature() {
        return stayFeature;
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
