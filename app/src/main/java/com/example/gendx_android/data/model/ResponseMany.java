package com.example.gendx_android.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMany {
    @SerializedName("accuracy")
    private String accuracy;

    @SerializedName("format")
    private String format;

    @SerializedName("msg")
    private String msg;

    @SerializedName("people")
    private List<Person> people;

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
