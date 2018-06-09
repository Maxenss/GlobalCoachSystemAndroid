package com.easylabs.globalcoachsystemandroid.data;

import com.easylabs.globalcoachsystemandroid.model.Category;
import com.easylabs.globalcoachsystemandroid.model.Language;
import com.easylabs.globalcoachsystemandroid.model.Specialization;
import com.easylabs.globalcoachsystemandroid.model.User;
import com.easylabs.globalcoachsystemandroid.model.UserSpecialization;

import java.util.ArrayList;

/**
 * Created by Maxim on 05.04.2018.
 */

public class SessionData {
    public static User currentUser;

    public static String token = "";

    public static ArrayList<Category> categories;

    public static ArrayList<Specialization> specializations;

    public static Category selectedCategory;

    public static ArrayList<Specialization> selectedSpecializations;
    public static Specialization selectedSpecialization;

    public static Language selectedLanguage;

    public static ArrayList<UserSpecialization> newStudentsRequestList;
    public static UserSpecialization currentStudentRequest;
    public static int currentStudentRequestIndex;

    public static String nextStudentRequest(){
        if (currentStudentRequestIndex < newStudentsRequestList.size()){
            currentStudentRequest = newStudentsRequestList.get(currentStudentRequestIndex);
            ++currentStudentRequestIndex;
            return currentStudentRequest.specializationTitle
                    + " USerId: " + currentStudentRequest.userId;
        }
        else{
            return "Not Found";
        }
    }

    public static ArrayList<String> getCategoriesTitles() {
        ArrayList<String> titles = new ArrayList<>();

        for (Category category :
                categories) {
            titles.add(category.title);
        }

        return titles;
    }

    public static String[] getCategoriesTitlesArray() {
        String[] titles = new String[categories.size()];

        for (int i = 0; i < categories.size(); i++) {
            titles[i] = categories.get(i).title;
        }

        return titles;
    }

    public static String[] getSpecializationsTitlesArray() {
        ArrayList<Specialization> specializationsTemp = new ArrayList<>();

        for (Specialization item :
                specializations) {
            if (item.categoryId == selectedCategory.id) {
                specializationsTemp.add(item);
            }
        }

        // Про всяк выпадок
        selectedSpecializations = specializationsTemp;

        String[] titles = new String[specializationsTemp.size()];

        for (int i = 0; i < specializationsTemp.size(); i++) {
            titles[i] = specializationsTemp.get(i).title;
        }

        return titles;
    }
}
