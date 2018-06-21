package com.easylabs.globalcoachsystemandroid.model;

import java.util.ArrayList;

/**
 * Created by Maxim on 12.04.2018.
 */

public class User {
    private int id;
    private String Login;
    private String Password;
    private String Email;
    private String Phone;
    private String About;
    private String Facebook;
    private String GooglePlus;
    private String Skype;
    private String VK;
    private String Viber;
    private String WhatsUp;
    private String Telegram;
    private int Age;
    private String FirstName;
    private String LastName;

    private boolean PhoneIsVisible;
    private boolean EmailIsVisible;
    private boolean FacebookIsVisible;
    private boolean GooglePlusIsVisible;
    private boolean SkypeIsVisible;
    private boolean VKIsVisible;
    private boolean ViberIsVisible;
    private boolean WhatsUpIsVisible;
    private boolean TelegramIsVisible;

    private ArrayList<Language> Languages;

    // Это то чему, хочет учиться юзер
    private ArrayList<ArrayList<UserSpecialization>> UserSpecializations;

    // Студенты тренера
    public ArrayList<ArrayList<UserSpecialization>> studentsSpecialization;


    public ArrayList<Couch> masterSkills;

    public StudentUser student = new StudentUser();

    public CouchUser couch = new CouchUser();

    public User(int id, String login, String password, String email, String phone, String about, String facebook, String googlePlus, String skype, String VK, String viber, String whatsUp, String telegram, int age, String firstName, String lastName, boolean phoneIsVisible, boolean emailIsVisible, ArrayList<Language> languages) {
        this.id = id;
        Login = login;
        Password = password;
        Email = email;
        Phone = phone;
        About = about;
        Facebook = facebook;
        GooglePlus = googlePlus;
        Skype = skype;
        this.VK = VK;
        Viber = viber;
        WhatsUp = whatsUp;
        Telegram = telegram;
        Age = age;
        FirstName = firstName;
        LastName = lastName;
        PhoneIsVisible = phoneIsVisible;
        EmailIsVisible = emailIsVisible;
        Languages = languages;
    }

    public User(int id, String login, String password, String email, String phone, String about, String facebook, String googlePlus, String skype, String VK, String viber, String whatsUp, String telegram, int age, String firstName, String lastName, boolean phoneIsVisible, boolean emailIsVisible, boolean facebookIsVisible, boolean googlePlusIsVisible, boolean skypeIsVisible, boolean VKIsVisible, boolean viberIsVisible, boolean whatsUpIsVisible, boolean telegramIsVisible, ArrayList<Language> languages) {
        this.id = id;
        Login = login;
        Password = password;
        Email = email;
        Phone = phone;
        About = about;
        Facebook = facebook;
        GooglePlus = googlePlus;
        Skype = skype;
        this.VK = VK;
        Viber = viber;
        WhatsUp = whatsUp;
        Telegram = telegram;
        Age = age;
        FirstName = firstName;
        LastName = lastName;
        PhoneIsVisible = phoneIsVisible;
        EmailIsVisible = emailIsVisible;
        FacebookIsVisible = facebookIsVisible;
        GooglePlusIsVisible = googlePlusIsVisible;
        SkypeIsVisible = skypeIsVisible;
        this.VKIsVisible = VKIsVisible;
        ViberIsVisible = viberIsVisible;
        WhatsUpIsVisible = whatsUpIsVisible;
        TelegramIsVisible = telegramIsVisible;
        Languages = languages;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getGooglePlus() {
        return GooglePlus;
    }

    public void setGooglePlus(String googlePlus) {
        GooglePlus = googlePlus;
    }

    public String getSkype() {
        return Skype;
    }

    public void setSkype(String skype) {
        Skype = skype;
    }

    public String getVK() {
        return VK;
    }

    public void setVK(String VK) {
        this.VK = VK;
    }

    public String getViber() {
        return Viber;
    }

    public void setViber(String viber) {
        Viber = viber;
    }

    public String getWhatsUp() {
        return WhatsUp;
    }

    public void setWhatsUp(String whatsUp) {
        WhatsUp = whatsUp;
    }

    public String getTelegram() {
        return Telegram;
    }

    public void setTelegram(String telegram) {
        Telegram = telegram;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public boolean isPhoneIsVisible() {
        return PhoneIsVisible;
    }

    public void setPhoneIsVisible(boolean phoneIsVisible) {
        PhoneIsVisible = phoneIsVisible;
    }

    public boolean isEmailIsVisible() {
        return EmailIsVisible;
    }

    public boolean isFacebookIsVisible() {
        return FacebookIsVisible;
    }

    public void setFacebookIsVisible(boolean facebookIsVisible) {
        FacebookIsVisible = facebookIsVisible;
    }

    public boolean isGooglePlusIsVisible() {
        return GooglePlusIsVisible;
    }

    public void setGooglePlusIsVisible(boolean googlePlusIsVisible) {
        GooglePlusIsVisible = googlePlusIsVisible;
    }

    public boolean isSkypeIsVisible() {
        return SkypeIsVisible;
    }

    public void setSkypeIsVisible(boolean skypeIsVisible) {
        SkypeIsVisible = skypeIsVisible;
    }

    public boolean isVKIsVisible() {
        return VKIsVisible;
    }

    public void setVKIsVisible(boolean VKIsVisible) {
        this.VKIsVisible = VKIsVisible;
    }

    public boolean isViberIsVisible() {
        return ViberIsVisible;
    }

    public void setViberIsVisible(boolean viberIsVisible) {
        ViberIsVisible = viberIsVisible;
    }

    public boolean isWhatsUpIsVisible() {
        return WhatsUpIsVisible;
    }

    public void setWhatsUpIsVisible(boolean whatsUpIsVisible) {
        WhatsUpIsVisible = whatsUpIsVisible;
    }

    public boolean isTelegramIsVisible() {
        return TelegramIsVisible;
    }

    public void setTelegramIsVisible(boolean telegramIsVisible) {
        TelegramIsVisible = telegramIsVisible;
    }

    public void setEmailIsVisible(boolean emailIsVisible) {
        EmailIsVisible = emailIsVisible;
    }

    public ArrayList<Language> getLanguages() {
        return Languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        Languages = languages;
    }

    public String[] getLanguagesTitlesArray() {
        String[] languagesTitles = new String[Languages.size()];

        for (int i = 0; i < Languages.size(); i++) {
            languagesTitles[i] = Languages.get(i).title;
        }

        return languagesTitles;
    }

    public ArrayList<ArrayList<UserSpecialization>> getUserSpecializations() {
        return UserSpecializations;
    }

    public String[] getAwaitSkillsTitles() {
        if (UserSpecializations.size() != 0) {
            String[] titles = new String[UserSpecializations.get(0).size()];

            for (int i = 0; i < titles.length; i++) {
                titles[i] = UserSpecializations.get(0).get(i).specializationTitle;
            }

            return titles;
        }

        return new String[0];
    }

    public String[] getProcessSkillsTitles() {
        if (UserSpecializations.size() != 0) {
            String[] titles = new String[UserSpecializations.get(1).size()];

            for (int i = 0; i < titles.length; i++) {
                titles[i] = UserSpecializations.get(1).get(i).specializationTitle;
            }

            return titles;
        }

        return new String[0];
    }

    public String[] getDoneSkillsTitles() {
        if (UserSpecializations.size() != 0) {
            String[] titles = new String[UserSpecializations.get(2).size()];

            for (int i = 0; i < titles.length; i++) {
                titles[i] = UserSpecializations.get(2).get(i).specializationTitle;
            }

            return titles;
        }

        return new String[0];
    }

    public String[] getInProgressSkillsTitlesCouchMode() {
        if (studentsSpecialization.size() != 0) {
            String[] titles = new String[studentsSpecialization.get(0).size()];

            for (int i = 0; i < titles.length; i++) {
                titles[i] = studentsSpecialization.get(0).get(i).specializationTitle;
            }

            return titles;
        }

        return new String[0];
    }

    public String[] getDoneSkillsTitlesCouchMode() {
        if (studentsSpecialization.size() != 0) {
            String[] titles = new String[studentsSpecialization.get(1).size()];

            for (int i = 0; i < titles.length; i++) {
                titles[i] = studentsSpecialization.get(1).get(i).specializationTitle;
            }

            return titles;
        }

        return new String[0];
    }

    public String[] getMasterSkillsTitles() {
        if (masterSkills.size() != 0) {
            String[] titles = new String[masterSkills.size()];

            for (int i = 0; i < titles.length; i++) {
                titles[i] = masterSkills.get(i).SpecializationTitle;
            }

            return titles;
        }

        return new String[0];
    }

    public void setUserSpecializations(ArrayList<ArrayList<UserSpecialization>> userSpecializations) {
        UserSpecializations = userSpecializations;
    }
}
