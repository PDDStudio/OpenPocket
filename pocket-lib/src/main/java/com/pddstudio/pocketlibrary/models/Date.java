package com.pddstudio.pocketlibrary.models;
/*
 * This Class was created by Patrick J
 * on 09.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import java.io.Serializable;

public class Date implements Serializable {

    private int year;
    private int month;
    private int day;

    public Date() {}

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
