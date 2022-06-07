package com.example.catchup_android;

import java.io.Serializable;

public class FarmVo implements Serializable {

    private int farm_seq; //  농장 번호
    private int farm_plant; //  농장 주수
    private int farm_linenum; //  농장 라인수
    private int farm_areanum; //  농장 구역수
    private String user_id; //  회원 아이디

    @Override
    public String toString() {
        return "FarmVo{" +
                "farm_seq=" + farm_seq +
                ", farm_plant=" + farm_plant +
                ", farm_linenum=" + farm_linenum +
                ", farm_areanum=" + farm_areanum +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public FarmVo() {
    }

    public FarmVo(int farm_seq, int farm_plant, int farm_linenum, int farm_areanum, String user_id) {
        this.farm_seq = farm_seq;
        this.farm_plant = farm_plant;
        this.farm_linenum = farm_linenum;
        this.farm_areanum = farm_areanum;
        this.user_id = user_id;
    }

    public int getFarm_seq() {
        return farm_seq;
    }

    public void setFarm_seq(int farm_seq) {
        this.farm_seq = farm_seq;
    }

    public int getFarm_plant() {
        return farm_plant;
    }

    public void setFarm_plant(int farm_plant) {
        this.farm_plant = farm_plant;
    }

    public int getFarm_linenum() {
        return farm_linenum;
    }

    public void setFarm_linenum(int farm_linenum) {
        this.farm_linenum = farm_linenum;
    }

    public int getFarm_areanum() {
        return farm_areanum;
    }

    public void setFarm_areanum(int farm_areanum) {
        this.farm_areanum = farm_areanum;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
