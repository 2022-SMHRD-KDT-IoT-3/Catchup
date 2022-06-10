package com.example.catchup_android;

public class MonitVo {

    private String monit_area;
    private String monit_infected;

    public MonitVo(){

    }

    @Override
    public String toString() {
        return "MonitVo{" +
                "monit_area='" + monit_area + '\'' +
                ", monit_infected='" + monit_infected + '\'' +
                '}';
    }

    public MonitVo(String monit_area, String monit_infected) {
        this.monit_area = monit_area;
        this.monit_infected = monit_infected;
    }

    public String getMonit_area() {
        return monit_area;
    }

    public void setMonit_area(String monit_area) {
        this.monit_area = monit_area;
    }

    public String getMonit_infected() {
        return monit_infected;
    }

    public void setMonit_infected(String monit_infected) {
        this.monit_infected = monit_infected;
    }
}
