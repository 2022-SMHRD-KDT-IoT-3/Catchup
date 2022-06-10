package com.example.catchup_android;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class BoardVo implements Serializable {

    private int board_seq;
    private String board_type;
    private String board_title;
    private String board_time;
    private String board_content;
    private int board_cnt;
    private int board_commcnt;
    private String user_id;

    @Override
    public String toString() {
        return "BoardVo{" +
                "board_seq=" + board_seq +
                ", board_type=" + board_type +
                ", board_title='" + board_title + '\'' +
                ", board_time='" + board_time + '\'' +
                ", board_content='" + board_content + '\'' +
                ", board_cnt=" + board_cnt +
                ", board_commcnt=" + board_commcnt +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public BoardVo() {
    }

    public BoardVo(int board_seq, String board_type, String board_title, String board_time, String board_content, int board_cnt, int board_commcnt, String user_id) {
        this.board_seq = board_seq;
        this.board_type = board_type;
        this.board_title = board_title;
        this.board_time = board_time;
        this.board_content = board_content;
        this.board_cnt = board_cnt;
        this.board_commcnt = board_commcnt;
        this.user_id = user_id;
    }

    public int getBoard_seq() {
        return board_seq;
    }

    public void setBoard_seq(int board_seq) {
        this.board_seq = board_seq;
    }

    public String getBoard_type() {
        return board_type;
    }

    public void setBoard_type(String board_type) {
        this.board_type = board_type;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_time() {
        return board_time;
    }

    public void setBoard_time(String board_time) {
        this.board_time = board_time;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public int getBoard_cnt() {
        return board_cnt;
    }

    public void setBoard_cnt(int board_cnt) {
        this.board_cnt = board_cnt;
    }

    public int getBoard_commcnt() {
        return board_commcnt;
    }

    public void setBoard_commcnt(int board_commcnt) {
        this.board_commcnt = board_commcnt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}