package com.example.catchup_android;

import androidx.annotation.NonNull;

public class ReservationVO {

    private String rsvtime;
    private String pesticide;
    private int rsv_seq;


    public ReservationVO(){

    }

    @Override
    public String toString(){
        return "ReservationVO{" +
                "rsvtime='"+ rsvtime+'\''+
                "pesticide='" + pesticide+'\''+
                "rsv_seq=" + rsv_seq+'\''+
                "}";
    }

    public ReservationVO(String rsvtime, String pesticide, int rsv_seq){
        this.rsvtime = rsvtime;
        this.pesticide = pesticide;
        this.rsv_seq = rsv_seq;

    }

    public int getRsv_seq(){return  rsv_seq;}
    public void setRsv_seq(int rsv_seq) { this.rsv_seq = rsv_seq; }

    public String getRsvtime() { return rsvtime; }
    public void setRsvtime(String rsvtime) { this.rsvtime = rsvtime; }

    public String getPesticide() { return  pesticide;}
    public void setPesticide(String pesticide) { this.pesticide = pesticide;}


}
