package com.example.catchup_android;

import java.io.Serializable;
import java.sql.Timestamp;

public class DiaryVO implements Serializable {
    private int diary_seq;
    private String diary_title;
    private String diary_content;
    private String diary_dt;
    private String diary_id;
    private int diary_temp;
    private int diary_humid;
    private int diary_percent;
    private String diary_pesti;
    private int diary_cnt;

    // diary_list에 보여줄 생성자
    public DiaryVO(String diary_title, String diary_content, Timestamp diary_dt) {
    }

    public DiaryVO(String diaryTitle, String diary_id, String diary_content, String diary_dt){

    }

    public DiaryVO(String diary_dt, String diary_temp, String diary_humid, String diary_percent, String diary_cnt) {

    }

    public DiaryVO(int diary_seq, String diary_title, String diary_content, String diary_dt, String diary_id) {
        this.diary_seq = diary_seq;
        this.diary_title = diary_title;
        this.diary_content = diary_content;
        this.diary_dt = diary_dt;
        this.diary_id = diary_id;
    }

//    public DiaryVO(int diary_seq, String diary_title, String diary_content, String diary_dt, int diary_temp, int diary_humid, int diary_percent, String diary_pesti, int diary_cnt) {
//        this.diary_seq = diary_seq;
//        this.diary_title = diary_title;
//        this.diary_content = diary_content;
//        this.diary_dt = diary_dt;
//        this.diary_temp = diary_temp;
//        this.diary_humid = diary_humid;
//        this.diary_percent = diary_percent;
//        this.diary_pesti = diary_pesti;
//        this.diary_cnt = diary_cnt;
//    }

    public DiaryVO(int diary_seq, String diary_title, String diary_content, String diary_dt, String diary_id, int diary_temp, int diary_humid, int diary_percent, String diary_pesti, int diary_cnt) {
        this.diary_seq = diary_seq;
        this.diary_title = diary_title;
        this.diary_content = diary_content;
        this.diary_dt = diary_dt;
        this.diary_id = diary_id;
        this.diary_temp = diary_temp;
        this.diary_humid = diary_humid;
        this.diary_percent = diary_percent;
        this.diary_pesti = diary_pesti;
        this.diary_cnt = diary_cnt;
    }

    public DiaryVO(String diary_title, String diary_content, String diary_dt, String diary_id, int diary_temp, int diary_humid, int diary_percent, String diary_pesti, int diary_cnt) {
        this.diary_title = diary_title;
        this.diary_content = diary_content;
        this.diary_dt = diary_dt;
        this.diary_id = diary_id;
        this.diary_temp = diary_temp;
        this.diary_humid = diary_humid;
        this.diary_percent = diary_percent;
        this.diary_pesti = diary_pesti;
        this.diary_cnt = diary_cnt;
    }

    public DiaryVO() {
        this.diary_id = diary_id;
        this.diary_temp = diary_temp;
        this.diary_humid = diary_humid;
        this.diary_percent = diary_percent;
        this.diary_pesti = diary_pesti;
        this.diary_cnt = diary_cnt;
    }

    public DiaryVO(String diary_title, String diary_content, String diary_dt, int diary_temp, int diary_humid, int diary_percent, int diary_cnt, String diary_pesti) {
    }
    // 다이어리 전체 ㄴ리스트
    public DiaryVO(int diary_seq, String diary_title, String diary_content, String diary_dt, int diary_temp, int diary_humid, int diary_percent, int diary_cnt, String diary_pesti) {
        this.diary_seq = diary_seq;
        this.diary_title = diary_title;
        this.diary_content = diary_content;
        this.diary_dt = diary_dt;
        this.diary_temp = diary_temp;
        this.diary_humid = diary_humid;
        this.diary_percent = diary_percent;
        this.diary_pesti = diary_pesti;
        this.diary_cnt = diary_cnt;
    }

    @Override
    public String toString() {
        return "DiaryVO{" +
                "diary_seq=" + diary_seq +
                ", diary_title='" + diary_title + '\'' +
                ", diary_content='" + diary_content + '\'' +
                ", diary_dt='" + diary_dt + '\'' +
                ", diary_id='" + diary_id + '\'' +
                ", diary_temp=" + diary_temp +
                ", diary_humid=" + diary_humid +
                ", diary_percent=" + diary_percent +
                ", diary_pesti='" + diary_pesti + '\'' +
                ", diary_cnt=" + diary_cnt +
                '}';
    }


    public int getDiary_seq() {
        return diary_seq;
    }

    public void setDiary_seq(int diary_seq) {
        this.diary_seq = diary_seq;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public String getDiary_content() {
        return diary_content;
    }

    public void setDiary_content(String diary_content) {
        this.diary_content = diary_content;
    }

    public String getDiary_dt() {
        return diary_dt;
    }

    public void setDiary_dt(String diary_dt) {
        this.diary_dt = diary_dt;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
    }

    public int getDiary_temp() {
        return diary_temp;
    }

    public void setDiary_temp(int diary_temp) {
        this.diary_temp = diary_temp;
    }

    public int getDiary_humid() {
        return diary_humid;
    }

    public void setDiary_humid(int diary_humid) {
        this.diary_humid = diary_humid;
    }

    public int getDiary_percent() {
        return diary_percent;
    }

    public void setDiary_percent(int diary_percent) {
        this.diary_percent = diary_percent;
    }

    public String getDiary_pesti() {
        return diary_pesti;
    }

    public void setDiary_pesti(String diary_pesti) {
        this.diary_pesti = diary_pesti;
    }

    public int getDiary_cnt() {
        return diary_cnt;
    }

    public void setDiary_cnt(int diary_cnt) {
        this.diary_cnt = diary_cnt;
    }
}
