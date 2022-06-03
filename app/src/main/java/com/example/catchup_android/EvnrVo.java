package com.example.catchup_android;

import java.io.Serializable;

public class EvnrVo implements Serializable {

    private int env_seq;
    private int env_temprt;
    private int env_humid;
    private String env_time;
    private String user_id;


    @Override
    public String toString() {
        return "EvnrVo{" +
                "env_seq=" + env_seq +
                ", env_temprt=" + env_temprt +
                ", env_humid=" + env_humid +
                ", env_time='" + env_time + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public EvnrVo() {
    }

    public EvnrVo(int env_seq, int env_temprt, int env_humid, String env_time, String user_id) {
        this.env_seq = env_seq;
        this.env_temprt = env_temprt;
        this.env_humid = env_humid;
        this.env_time = env_time;
        this.user_id = user_id;
    }

    public int getEnv_seq() {
        return env_seq;
    }

    public void setEnv_seq(int env_seq) {
        this.env_seq = env_seq;
    }

    public int getEnv_temprt() {
        return env_temprt;
    }

    public void setEnv_temprt(int env_temprt) {
        this.env_temprt = env_temprt;
    }

    public int getEnv_humid() {
        return env_humid;
    }

    public void setEnv_humid(int env_humid) {
        this.env_humid = env_humid;
    }

    public String getEnv_time() {
        return env_time;
    }

    public void setEnv_time(String env_time) {
        this.env_time = env_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
