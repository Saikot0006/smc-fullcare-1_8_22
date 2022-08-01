package com.gs.smc_mmc.model;

public class PW_RecordModel {

    private String date;
    private String name;
    private String age;


    public PW_RecordModel(String date, String name, String age) {
        this.date = date;
        this.name = name;
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
