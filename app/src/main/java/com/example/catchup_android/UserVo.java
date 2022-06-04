package com.example.catchup_android;

import java.io.Serializable;

public class UserVo implements Serializable {

    private String id;
    private String pw;
    private String name;
    private String nick;
    private String mail;
    private String serial;
    private String joindate;

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", mail='" + mail + '\'' +
                ", serial='" + serial + '\'' +
                ", joindate='" + joindate + '\'' +
                '}';
    }

    public UserVo() {
    }

    public UserVo(String id, String pw, String name, String nick, String mail, String serial, String joindate) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nick = nick;
        this.mail = mail;
        this.serial = serial;
        this.joindate = joindate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }
}

