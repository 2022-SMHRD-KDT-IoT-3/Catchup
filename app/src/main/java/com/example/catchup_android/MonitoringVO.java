package com.example.catchup_android;

import java.io.Serializable;

public class MonitoringVO implements Serializable {
    private int monit_seq;
    private String monit_area;
    private String monit_time;
    private char monit_done;
    private char monit_infected;
    private String user_id;
    private int res_req;
    private int monit_percent;
    private int monit_cnt;

    // toString


    @Override
    public String toString() {
        return "MonitoringVO{" +
                "monit_seq=" + monit_seq +
                ", monit_area='" + monit_area + '\'' +
                ", monit_time='" + monit_time + '\'' +
                ", monit_done=" + monit_done +
                ", monit_infected=" + monit_infected +
                ", user_id='" + user_id + '\'' +
                ", res_req=" + res_req +
                ", monit_percent=" + monit_percent +
                ", monit_cnt=" + monit_cnt +
                '}';
    }

    // constructor
    public MonitoringVO() {
    }

    public MonitoringVO(int monit_percent, int monit_cnt) {
        this.monit_percent = monit_percent;
        this.monit_cnt = monit_cnt;
    }

    public MonitoringVO(int monit_seq, String monit_area, String monit_time, char monit_done, char monit_infected, String user_id, int res_req, int monit_percent, int monit_cnt) {
        this.monit_seq = monit_seq;
        this.monit_area = monit_area;
        this.monit_time = monit_time;
        this.monit_done = monit_done;
        this.monit_infected = monit_infected;
        this.user_id = user_id;
        this.res_req = res_req;
        this.monit_percent = monit_percent;
        this.monit_cnt = monit_cnt;
    }

    // getter, setter
    public int getMonit_seq() {
        return monit_seq;
    }

    public void setMonit_seq(int monit_seq) {
        this.monit_seq = monit_seq;
    }

    public String getMonit_area() {
        return monit_area;
    }

    public void setMonit_area(String monit_area) {
        this.monit_area = monit_area;
    }

    public String getMonit_time() {
        return monit_time;
    }

    public void setMonit_time(String monit_time) {
        this.monit_time = monit_time;
    }

    public char getMonit_done() {
        return monit_done;
    }

    public void setMonit_done(char monit_done) {
        this.monit_done = monit_done;
    }

    public char getMonit_infected() {
        return monit_infected;
    }

    public void setMonit_infected(char monit_infected) {
        this.monit_infected = monit_infected;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getRes_req() {
        return res_req;
    }

    public void setRes_req(int res_req) {
        this.res_req = res_req;
    }

    public int getMonit_percent() {
        return monit_percent;
    }

    public void setMonit_percent(int monit_percent) {
        this.monit_percent = monit_percent;
    }

    public int getMonit_cnt() {
        return monit_cnt;
    }

    public void setMonit_cnt(int monit_cnt) {
        this.monit_cnt = monit_cnt;
    }
}
