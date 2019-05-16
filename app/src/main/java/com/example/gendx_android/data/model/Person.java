package com.example.gendx_android.data.model;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("gender")
    private String gender;

    public Person(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
