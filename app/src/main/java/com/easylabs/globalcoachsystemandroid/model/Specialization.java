package com.easylabs.globalcoachsystemandroid.model;

/**
 * Created by Maxim on 08.05.2018.
 */

public class Specialization {
    public int id;
    public int categoryId;
    public String title;

    public Specialization(int id, int categoryId, String title) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
    }

    public Specialization() {
    }
}
