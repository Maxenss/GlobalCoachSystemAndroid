package com.easylabs.globalcoachsystemandroid.server;

import com.easylabs.globalcoachsystemandroid.model.Category;
import com.easylabs.globalcoachsystemandroid.model.Couch;
import com.easylabs.globalcoachsystemandroid.model.Language;
import com.easylabs.globalcoachsystemandroid.model.Specialization;
import com.easylabs.globalcoachsystemandroid.model.User;
import com.easylabs.globalcoachsystemandroid.model.UserSpecialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Maxim on 10.04.2018.
 */

public class JsonWorker {
    // Метод для парсинга токена

    // Метод для парcинга пользователя
    public static User getUserFromJson(JSONObject rootJson, String jsonTitle) {
        User user = new User();

        try {
            JSONObject userJson = rootJson.getJSONObject(jsonTitle);

            user.setId(userJson.getInt("id"));
            user.setLogin(userJson.getString("login"));
            user.setPassword(userJson.getString("password"));
            user.setEmail(userJson.getString("email"));
            user.setPhone(userJson.getString("phone"));
            user.setAbout(userJson.getString("about"));
            user.setAge(userJson.getInt("age"));
            user.setFacebook(userJson.getString("facebook"));
            user.setGooglePlus(userJson.getString("googlePlus"));
            user.setSkype(userJson.getString("skype"));
            user.setTelegram(userJson.getString("telegram"));
            user.setViber(userJson.getString("viber"));
            user.setVK(userJson.getString("vk"));
            user.setWhatsUp(userJson.getString("whatsUp"));
            user.setFirstName(userJson.getString("firstName"));
            user.setLastName(userJson.getString("lastName"));
            user.setPhoneIsVisible(userJson.getBoolean("phoneIsVisible"));
            user.setEmailIsVisible(userJson.getBoolean("emailIsVisible"));
            user.setFacebookIsVisible(userJson.getBoolean("facebookIsVisible"));
            user.setGooglePlusIsVisible(userJson.getBoolean("googlePlusIsVisible"));
            user.setSkypeIsVisible(userJson.getBoolean("skypeIsVisible"));
            user.setVKIsVisible(userJson.getBoolean("vkIsVisible"));
            user.setViberIsVisible(userJson.getBoolean("viberIsVisible"));
            user.setWhatsUpIsVisible(userJson.getBoolean("whatsUpIsVisible"));
            user.setTelegramIsVisible(userJson.getBoolean("telegramIsVisible"));


            JSONArray jsonLanguages = rootJson.getJSONArray("languages");
            ArrayList<Language> languages = new ArrayList<>();

            for (int i = 0; i < jsonLanguages.length(); i++) {
                JSONObject jsonLanguage = jsonLanguages.getJSONObject(i);

                int id = jsonLanguage.getInt("id");
                int languageId = jsonLanguage.getInt("languageId");
                String title = jsonLanguage.getString("title");

                Language language = new Language(id, languageId, title);
                languages.add(language);
            }

            user.setLanguages(languages);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return user;
    }

    public static ArrayList<Category> getCategoriesFromJson(JSONObject categoriesJson) {
        try {
            JSONArray categoriesArrayJson = categoriesJson.getJSONArray("categories");

            ArrayList<Category> categoryArrayList = new ArrayList<>();
            Category category;

            for (int i = 0; i < categoriesArrayJson.length(); i++) {
                JSONObject categoryJson = categoriesArrayJson.getJSONObject(i);

                int id = categoryJson.getInt("id");
                String title = categoryJson.getString("title");

                category = new Category(id, title);

                categoryArrayList.add(category);
            }

            return categoryArrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Specialization> getSpecializationsFromJson(JSONObject specializationsJson) {
        try {
            JSONArray specializationsArrayJson = specializationsJson.getJSONArray("specializations");

            ArrayList<Specialization> specializationsArrayList = new ArrayList<>();
            Specialization specialization;

            for (int i = 0; i < specializationsArrayJson.length(); i++) {
                JSONObject specializationJson = specializationsArrayJson.getJSONObject(i);

                int id = specializationJson.getInt("id");
                int categoryId = specializationJson.getInt("categoryId");
                String title = specializationJson.getString("title");

                specialization = new Specialization(id, categoryId, title);

                specializationsArrayList.add(specialization);
            }

            return specializationsArrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ArrayList<UserSpecialization>>
    getUserSpecializationsFromJson(JSONObject jsonroot) {
        try {
            JSONArray specializationsArrayJson = jsonroot.getJSONArray("specializations");

            ArrayList<ArrayList<UserSpecialization>> specializationsArrayList = new ArrayList<>();
            ArrayList<UserSpecialization> al1 = new ArrayList<>();
            ArrayList<UserSpecialization> al2 = new ArrayList<>();
            ArrayList<UserSpecialization> al3 = new ArrayList<>();

            UserSpecialization specialization;

            for (int i = 0; i < specializationsArrayJson.length(); i++) {
                JSONObject specializationJson = specializationsArrayJson.getJSONObject(i);

                int id = specializationJson.getInt("id");
                int specializationId = specializationJson.getInt("specializationId");
                String specializationTitle = specializationJson.getString("specializationTitle");
                int categoryId = specializationJson.getInt("categoryId");
                String categoryTitle = specializationJson.getString("categoryTitle");
                int userId = specializationJson.getInt("userId");
                int languageId = specializationJson.getInt("languageId");
                String languageTitle = specializationJson.getString("languageTitle");
                int couchId = specializationJson.getInt("couchId");
                //   int status = specializationJson.getInt("status");

                specialization = new UserSpecialization(
                        id, specializationId, specializationTitle
                        , categoryId, categoryTitle, userId, languageId
                        , languageTitle, couchId);

                if (specialization.status == UserSpecialization.STATUS_AWAIT) {
                    al1.add(specialization);
                } else if (specialization.status == UserSpecialization.STATUS_INPROGRESS) {
                    al2.add(specialization);
                } else {
                    al3.add(specialization);
                }

                specializationsArrayList.add(al1);
                specializationsArrayList.add(al2);
                specializationsArrayList.add(al3);
            }

            return specializationsArrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ArrayList<UserSpecialization>>
    getStudentSpecsFromJson(JSONObject jsonroot) {
        try {
            JSONArray specializationsArrayJson = jsonroot.getJSONArray("specializations");

            ArrayList<ArrayList<UserSpecialization>> specializationsArrayList = new ArrayList<>();

            ArrayList<UserSpecialization> al2 = new ArrayList<>();
            ArrayList<UserSpecialization> al3 = new ArrayList<>();

            UserSpecialization specialization;

            for (int i = 0; i < specializationsArrayJson.length(); i++) {
                JSONObject specializationJson = specializationsArrayJson.getJSONObject(i);

                int id = specializationJson.getInt("id");
                int specializationId = specializationJson.getInt("specializationId");
                String specializationTitle = specializationJson.getString("specializationTitle");
                int categoryId = specializationJson.getInt("categoryId");
                String categoryTitle = specializationJson.getString("categoryTitle");
                int userId = specializationJson.getInt("userId");
                int languageId = specializationJson.getInt("languageId");
                String languageTitle = specializationJson.getString("languageTitle");
                int couchId = specializationJson.getInt("couchId");
                //   int status = specializationJson.getInt("status");

                specialization = new UserSpecialization(
                        id, specializationId, specializationTitle
                        , categoryId, categoryTitle, userId, languageId
                        , languageTitle, couchId);

                if (specialization.status == UserSpecialization.STATUS_INPROGRESS) {
                    al2.add(specialization);
                } else {
                    al3.add(specialization);
                }

                specializationsArrayList.add(al2);
                specializationsArrayList.add(al3);
            }

            return specializationsArrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<UserSpecialization>
    getNewStudentsFromJson(JSONArray jsonArray) {
        try {
            JSONArray specializationsArrayJson = jsonArray;

            ArrayList<UserSpecialization> specializationsArrayList = new ArrayList<>();

            UserSpecialization specialization;

            for (int i = 0; i < specializationsArrayJson.length(); i++) {
                JSONObject specializationJson = specializationsArrayJson.getJSONObject(i);

                int id = specializationJson.getInt("id");
                int specializationId = specializationJson.getInt("specializationId");
                String specializationTitle = specializationJson.getString("specializationTitle");
                int categoryId = specializationJson.getInt("categoryId");
                String categoryTitle = specializationJson.getString("categoryTitle");
                int userId = specializationJson.getInt("userId");
                int languageId = specializationJson.getInt("languageId");
                String languageTitle = specializationJson.getString("languageTitle");
                int couchId = specializationJson.getInt("couchId");
                //   int status = specializationJson.getInt("status");

                specialization = new UserSpecialization(
                        id, specializationId, specializationTitle
                        , categoryId, categoryTitle, userId, languageId
                        , languageTitle, couchId);

                specializationsArrayList.add(specialization);
            }

            return specializationsArrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Couch>
    getMasterSkillsFromJson(JSONArray rootArray) {
        ArrayList<Couch> couches = new ArrayList<>();

        try {
            Couch couch;

            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject couchJsonObject = rootArray.getJSONObject(i);

                int id = couchJsonObject.getInt("id");
                int couchId = couchJsonObject.getInt("couchId");
                int languageId = couchJsonObject.getInt("languageId");
                String languageTitle = couchJsonObject.getString("languageTitle");
                int categoryId = couchJsonObject.getInt("categoryId");
                String categoryTitle = couchJsonObject.getString("categoryTitle");
                int specializationId = couchJsonObject.getInt("specializationId");
                String specializationTitle = couchJsonObject.getString("specializationTitle");

                couch = new Couch(id, couchId, languageId, languageTitle
                        , categoryId, categoryTitle, specializationId, specializationTitle);

                couches.add(couch);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return couches;
    }
}
