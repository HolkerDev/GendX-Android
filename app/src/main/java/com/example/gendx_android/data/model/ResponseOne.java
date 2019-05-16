package com.example.gendx_android.data.model;

import com.google.gson.annotations.SerializedName;

public class ResponseOne {
    @SerializedName("accuracy")
    private String accuracy;

    @SerializedName("format")
    private String format;

    @SerializedName("gender")
    private String gender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
