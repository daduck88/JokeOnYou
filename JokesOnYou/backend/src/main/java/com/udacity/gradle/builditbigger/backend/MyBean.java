package com.udacity.gradle.builditbigger.backend;

import com.jokesonyou.comedian.Comedian;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public MyBean() {
        Comedian comedian = new Comedian();
        this.myData = comedian.getJoke();
    }

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}