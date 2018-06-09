package com.easylabs.globalcoachsystemandroid.model;

public class Couch {
    public int Id;

    public int CouchId;

    public int LanguageId;

    public String LanguageTitle;

    public int CategoryId;

    public String CategoryTitle;

    public int SpecializationId;

    public String SpecializationTitle;

    public Couch(int id, int couchId, int languageId, String languageTitle, int categoryId, String categoryTitle, int specializationId, String specializationTitle) {
        Id = id;
        CouchId = couchId;
        LanguageId = languageId;
        LanguageTitle = languageTitle;
        CategoryId = categoryId;
        CategoryTitle = categoryTitle;
        SpecializationId = specializationId;
        SpecializationTitle = specializationTitle;
    }

    public Couch() {
    }
}
