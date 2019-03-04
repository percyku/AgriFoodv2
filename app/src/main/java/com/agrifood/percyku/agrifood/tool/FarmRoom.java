package com.agrifood.percyku.agrifood.tool;

/**
 * Created by percyku on 16/10/30.
 */
public class FarmRoom {



    private String name;
    private String tel;
    private String introduction;
    private String openHours;
    private String latitude;
    private String longitude;
    private String distance;
    private int mPic;
    private boolean state;


    public FarmRoom(String name, String tel, String introduction, String openHours, String latitude, String longitude, String distance){


        this.name=name;
        this.tel=tel;
        this.introduction=introduction;
        this.openHours=openHours;
        this.latitude=latitude;
        this.longitude=longitude;
        this.distance=distance;

    }


    public String getNamw() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getOpenHours() {
        return openHours;
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
