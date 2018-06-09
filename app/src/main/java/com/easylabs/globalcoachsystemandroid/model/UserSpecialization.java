package com.easylabs.globalcoachsystemandroid.model;

public class UserSpecialization {
    public static final int STATUS_AWAIT = 0;
    public static final int STATUS_INPROGRESS = 1;
    public static final int STATUS_DONE = 2;

    public int id;

    public int specializationId;

    public String specializationTitle;

    public int categoryId;

    public String categoryTitle;

    public int userId;

    public int languageId;

    public String languageTitle;

    // Если -1, то наставника нету
    public int couchId;

    public int status;

    public UserSpecialization(int id, int specializationId
            , String specializationTitle, int categoryId
            , String categoryTitle, int userId, int languageId
            , String languageTitle, int couchId) {
        this.id = id;
        this.specializationId = specializationId;
        this.specializationTitle = specializationTitle;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.userId = userId;
        this.languageId = languageId;
        this.languageTitle = languageTitle;
        this.couchId = couchId;

        if (couchId > 0)
            this.status = STATUS_INPROGRESS;
        else if (couchId == 0)
            this.status = STATUS_DONE;
        else if (couchId == -1)
            this.status = STATUS_AWAIT;
    }
}
