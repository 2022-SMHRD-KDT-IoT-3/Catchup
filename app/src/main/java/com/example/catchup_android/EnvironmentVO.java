package com.example.catchup_android;

import java.io.Serializable;

public class EnvironmentVO implements Serializable {
    private int envr_seq;
    private int envr_temp;
    private int envr_humid;
    private String envr_time;
    private String user_id;


    @Override
    public String toString() {
        return "EnvironmentVO{" +
                "envr_seq=" + envr_seq +
                ", envr_temp=" + envr_temp +
                ", envr_humid=" + envr_humid +
                ", envr_time='" + envr_time + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public EnvironmentVO() {
    }

    public EnvironmentVO(int envr_temp, int envr_humid) {
        this.envr_temp = envr_temp;
        this.envr_humid = envr_humid;
    }

    public EnvironmentVO(int envr_seq, int envr_temp, int envr_humid, String envr_time, String user_id) {
        this.envr_seq = envr_seq;
        this.envr_temp = envr_temp;
        this.envr_humid = envr_humid;
        this.envr_time = envr_time;
        this.user_id = user_id;
    }

    // getter, setter
    public int getEnvr_seq() {
        return envr_seq;
    }

    public void setEnvr_seq(int envr_seq) {
        this.envr_seq = envr_seq;
    }

    public int getEnvr_temp() {
        return envr_temp;
    }

    public void setEnvr_temp(int envr_temp) {
        this.envr_temp = envr_temp;
    }

    public int getEnvr_humid() {
        return envr_humid;
    }

    public void setEnvr_humid(int envr_humid) {
        this.envr_humid = envr_humid;
    }

    public String getEnvr_time() {
        return envr_time;
    }

    public void setEnvr_time(String envr_time) {
        this.envr_time = envr_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
