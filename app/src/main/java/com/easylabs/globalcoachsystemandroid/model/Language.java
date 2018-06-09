package com.easylabs.globalcoachsystemandroid.model;

/**
 * Created by Maxim on 13.05.2018.
 */

public class Language {
    public int id;

    public int languageId;

    public String title;

    public Language(int id, int languageId, String title) {
        this.id = id;
        this.languageId = languageId;
        this.title = title;
    }

    public Language() {

    }
}

