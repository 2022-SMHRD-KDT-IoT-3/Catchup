package com.example.catchup_android;

import java.io.Serializable;
import java.sql.Timestamp;

public class DiaryVO implements Serializable {
    private int diary_seq;
    private String diary_title;
    private String diary_content;
    private String diary_dt;
    private String diary_id;

    // diary_list에 보여줄 생성자
    public DiaryVO(String diary_title, String diary_content, Timestamp diary_dt) {
    }

    @Override
    public String toString() {
        return "DiaryVO{" +
                "diary_seq=" + diary_seq +
                ", diary_title='" + diary_title + '\'' +
                ", diary_content='" + diary_content + '\'' +
                ", diary_dt=" + diary_dt +
                ", diary_id='" + diary_id + '\'' +
                '}';
    }

    public DiaryVO(String diaryTitle, String diary_id, String diary_content, String diary_dt){

    }



    public DiaryVO(int diary_seq, String diary_title, String diary_content, String diary_dt, String diary_id) {
        this.diary_seq = diary_seq;
        this.diary_title = diary_title;
        this.diary_content = diary_content;
        this.diary_dt = diary_dt;
        this.diary_id = diary_id;
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
}
