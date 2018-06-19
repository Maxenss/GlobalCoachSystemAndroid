package com.easylabs.globalcoachsystemandroid.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.data.SessionData;
import com.easylabs.globalcoachsystemandroid.model.Couch;
import com.easylabs.globalcoachsystemandroid.model.User;
import com.easylabs.globalcoachsystemandroid.server.ApiWorker;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    // Всё для пользователя
    RelativeLayout rlUser;

    private Button btChangeName;
    private Button btChangeSecondName;
    private Button btChangeAge;
    private Button btChangePhone;
    private Button btChangeEmail;
    private Button btChangeFacebook;
    private Button btChangeGooglePlus;
    private Button btChangeSkype;
    private Button btChangeVk;
    private Button btChangeViber;
    private Button btChangeWhatsUp;
    private Button btChangeTelegram;

    private TextView tvName;
    private TextView tvSecondName;
    private TextView tvAge;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvFacebook;
    private TextView tvGooglePlus;
    private TextView tvSkype;
    private TextView tvVk;
    private TextView tvViber;
    private TextView tvWhatsUp;
    private TextView tvTelegram;

    private ImageView ivPhoneVIsible;
    private ImageView ivEmailIsVisible;
    private ImageView ivSkypeIsVisible;
    private ImageView ivTelegramIsVisible;
    private ImageView ivViberIsVisible;
    private ImageView ivWhatsUpIsVisible;
    private ImageView ivVkIsVisible;
    private ImageView ivFacebookIsVisible;
    private ImageView ivGooglePlusIsVisible;
    //////////

    private AlertDialog adChangeUserInfo;

    // Всё для студента
    private Button btNewSkill;
    private RelativeLayout rlStudent;

    private Button btCategory;
    private Button btSpecializations;
    private Button btSkillLanguages;

    private AlertDialog adNewSkill;
    private AlertDialog adCategoryList;
    private AlertDialog adSpecList;
    private AlertDialog adSkillLanguage;

    private ExpandableListView elvUserSkills;

    // Вся для прпода
    private RelativeLayout rlCouch;
    private ExpandableListView elvCouchSkills;
    private AlertDialog adIncdicateSkill;
    private Button btIndicateSkill;

    // Для новых запросов у препода
    private TextView tvNewRequestTitle;
    private Button btAcceptRequest;
    private Button btNextRequest;


    private GetUserSpecializationsAsyncTask getUserSpecializationsAsyncTask;

    private void initizalizeUserLayout() {
        rlUser = findViewById(R.id.rlUser);

        tvName = (TextView) findViewById(R.id.tvName);
        tvSecondName = (TextView) findViewById(R.id.tvSecondName);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvFacebook = (TextView) findViewById(R.id.tvFacebook);
        tvGooglePlus = (TextView) findViewById(R.id.tvGooglePlus);
        tvSkype = (TextView) findViewById(R.id.tvSkype);
        tvVk = (TextView) findViewById(R.id.tvVk);
        tvViber = (TextView) findViewById(R.id.tvViber);
        tvWhatsUp = (TextView) findViewById(R.id.tvWhatsUp);
        tvTelegram = (TextView) findViewById(R.id.tvTelegram);

        btChangeName = findViewById(R.id.btChangeName);
        btChangeSecondName = findViewById(R.id.btChangeSecondName);
        btChangeAge = findViewById(R.id.btChangeAge);
        btChangePhone = findViewById(R.id.btChangePhone);
        btChangeEmail = findViewById(R.id.btChangeEmail);
        btChangeFacebook = findViewById(R.id.btChangeFacebook);
        btChangeGooglePlus = findViewById(R.id.btChangeGooglePlus);
        btChangeSkype = findViewById(R.id.btChangeSkype);
        btChangeVk = findViewById(R.id.btChangeVk);
        btChangeViber = findViewById(R.id.btChangeViber);
        btChangeWhatsUp = findViewById(R.id.btChangeWhatsUp);
        btChangeTelegram = findViewById(R.id.btChangeTelegram);

        ivPhoneVIsible = findViewById(R.id.ivPhoneVIsible);
        ivEmailIsVisible = findViewById(R.id.ivEmailIsVisible);
        ivSkypeIsVisible = findViewById(R.id.ivSkypeIsVisible);
        ivTelegramIsVisible = findViewById(R.id.ivTelegramIsVisible);
        ivViberIsVisible = findViewById(R.id.ivViberIsVisible);
        ivWhatsUpIsVisible = findViewById(R.id.ivWhatsUpIsVisible);
        ivVkIsVisible = findViewById(R.id.ivVkIsVisible);
        ivFacebookIsVisible = findViewById(R.id.ivFacebookIsVisible);
        ivGooglePlusIsVisible = findViewById(R.id.ivGooglePlusIsVisible);

        tvName.setText("Name*: " + SessionData.currentUser.getFirstName());
        tvSecondName.setText("Surname*: " + SessionData.currentUser.getLastName());
        tvAge.setText("Age*: " + SessionData.currentUser.getAge());
        tvPhone.setText("Phone: " + (SessionData.currentUser.getPhone() == null ?
                "Empty Field"
                : SessionData.currentUser.getPhone()));
        tvEmail.setText("Email: " + SessionData.currentUser.getEmail());
        tvFacebook.setText("Facebook: "
                + (SessionData.currentUser.getFacebook() == null
                ? "Empty Field"
                : SessionData.currentUser.getFacebook()));
        tvGooglePlus.setText("GooglePlus: " + (SessionData.currentUser.getGooglePlus() == null ? "Empty Field"
                : SessionData.currentUser.getGooglePlus()));
        tvSkype.setText("Skype: " + (SessionData.currentUser.getSkype() == null ?
                "Empty Field"
                : SessionData.currentUser.getSkype()));
        tvVk.setText("VK: " + (SessionData.currentUser.getVK() == null ?
                "Empty Field"
                : SessionData.currentUser.getVK()));
        tvViber.setText("Viber: " + (SessionData.currentUser.getViber() == null ?
                "Empty Field"
                : SessionData.currentUser.getViber()));
        tvWhatsUp.setText("WhatsUp: " + (SessionData.currentUser.getWhatsUp() == null ?
                "Empty Field"
                : SessionData.currentUser.getWhatsUp()));
        tvTelegram.setText("Telegram: " + (SessionData.currentUser.getTelegram() == null ?
                "Empty Field"
                : SessionData.currentUser.getTelegram()));

        ivPhoneVIsible.setBackgroundResource(
                SessionData.currentUser.isPhoneIsVisible()
                        ? R.drawable.eye_outline : R.drawable.eye_off_outline
        );

        ivSkypeIsVisible.setBackgroundResource(SessionData.currentUser.isPhoneIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        ivTelegramIsVisible.setBackgroundResource(SessionData.currentUser.isTelegramIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        ivViberIsVisible.setBackgroundResource(SessionData.currentUser.isViberIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        ivWhatsUpIsVisible.setBackgroundResource(SessionData.currentUser.isWhatsUpIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        ivVkIsVisible.setBackgroundResource(SessionData.currentUser.isViberIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        ivFacebookIsVisible.setBackgroundResource(SessionData.currentUser.isFacebookIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        ivGooglePlusIsVisible.setBackgroundResource(SessionData.currentUser.isGooglePlusIsVisible()
                ? R.drawable.eye_outline : R.drawable.eye_off_outline);

        btChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(0, SessionData.currentUser.getFirstName(), false);
            }
        });

        btChangeSecondName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(1, SessionData.currentUser.getLastName(), false);
            }
        });

        btChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(3, SessionData.currentUser.getPhone(), SessionData.currentUser.isPhoneIsVisible());
            }
        });

        btChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(4, SessionData.currentUser.getPhone(), SessionData.currentUser.isEmailIsVisible());
            }
        });

        btChangeSkype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(5, SessionData.currentUser.getSkype(), SessionData.currentUser.isSkypeIsVisible());
            }
        });

        btChangeTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(6, SessionData.currentUser.getTelegram(), SessionData.currentUser.isTelegramIsVisible());
            }
        });

        btChangeViber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(7, SessionData.currentUser.getViber(), SessionData.currentUser.isViberIsVisible());
            }
        });

        btChangeWhatsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(8, SessionData.currentUser.getWhatsUp(), SessionData.currentUser.isWhatsUpIsVisible());
            }
        });

        btChangeVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(9, SessionData.currentUser.getVK(), SessionData.currentUser.isVKIsVisible());
            }
        });

        btChangeFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(10, SessionData.currentUser.getFacebook(), SessionData.currentUser.isFacebookIsVisible());
            }
        });

        btChangeGooglePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdUserInfoEdit(11, SessionData.currentUser.getGooglePlus(), SessionData.currentUser.isGooglePlusIsVisible());
            }
        });
    }

    private void createAdUserInfoEdit(final int type, String value, boolean isChecked) {
        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(30, 30, 0, 0);
        input.setLayoutParams(lp);
        input.setText(value);
        input.setHint("Enter value...");

        final CheckBox isVisibleCb = new CheckBox(this);
        isVisibleCb.setText("Is Visible");
        isVisibleCb.setChecked(isChecked);

        LinearLayout llRoot = new LinearLayout(this);
        llRoot.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        String title = "Change Field";

        switch (type) {
            case 0:
                title = "Change Name";
                alertDialog.setView(input);
                break;
            case 1:
                title = "Change Surname";
                alertDialog.setView(input);
                break;
            case 3:
                title = "Change phone";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 4:
                title = "Change Email";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 5:
                title = "Change Skype";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 6:
                title = "Change Telegram";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 7:
                title = "Change Viber";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 8:
                title = "Change WhatsUp";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 9:
                title = "Change VK";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 10:
                title = "Change Facebook";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
            case 11:
                title = "Change GooglePlus";

                llRoot.addView(input);
                llRoot.addView(isVisibleCb);
                alertDialog.setView(llRoot);
                break;
        }

        alertDialog.setTitle(title);

        alertDialog.setPositiveButton("Change",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String note = input.getText().toString();

                        switch (type) {
                            case 0:
                                SessionData.currentUser.setFirstName(note);
                                break;
                            case 1:
                                SessionData.currentUser.setLastName(note);
                                break;
                            case 3:
                                SessionData.currentUser.setPhone(note);
                                SessionData.currentUser.setPhoneIsVisible(isVisibleCb.isChecked());
                                break;
                            case 4:
                                SessionData.currentUser.setEmail(note);
                                SessionData.currentUser.setEmailIsVisible(isVisibleCb.isChecked());
                                break;
                            case 5:
                                SessionData.currentUser.setSkype(note);
                                SessionData.currentUser.setSkypeIsVisible(isVisibleCb.isChecked());
                            case 6:
                                SessionData.currentUser.setTelegram(note);
                                SessionData.currentUser.setTelegramIsVisible(isVisibleCb.isChecked());
                            case 7:
                                SessionData.currentUser.setViber(note);
                                SessionData.currentUser.setViberIsVisible(isVisibleCb.isChecked());
                            case 8:
                                SessionData.currentUser.setWhatsUp(note);
                                SessionData.currentUser.setWhatsUpIsVisible(isVisibleCb.isChecked());
                            case 9:
                                SessionData.currentUser.setVK(note);
                                SessionData.currentUser.setVKIsVisible(isVisibleCb.isChecked());
                            case 10:
                                SessionData.currentUser.setFacebook(note);
                                SessionData.currentUser.setFacebookIsVisible(isVisibleCb.isChecked());
                            case 11:
                                SessionData.currentUser.setGooglePlus(note);
                                SessionData.currentUser.setGooglePlusIsVisible(isVisibleCb.isChecked());
                        }

                        UpdateUserInfoAsyncTask updateUserInfoAsyncTask =
                                new UpdateUserInfoAsyncTask(SessionData.currentUser);
                        updateUserInfoAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.setCancelable(false);

        adChangeUserInfo = alertDialog.create();
        adChangeUserInfo.show();
    }

    private void createAdNewSkill() {
        SessionData.selectedCategory = null;
        SessionData.selectedSpecialization = null;
        SessionData.selectedSpecializations = null;

        if (SessionData.currentUser.getLanguages().size() != 0)
            SessionData.selectedLanguage = SessionData.currentUser.getLanguages().get(0);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("New Skill");
        View view = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.new_skill_alert_dialog, null);

        btCategory = view.findViewById(R.id.btCategory);
        btCategory.setText("Select Category");
        btCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdCategoryList();
                adCategoryList.show();
            }
        });

        btSpecializations = view.findViewById(R.id.btSpecializations);
        btSpecializations.setText("Select Specialization");
        btSpecializations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createAdSpecializationList()) {
                    adSpecList.show();
                }
            }
        });

        btSkillLanguages = view.findViewById(R.id.btSkillLanguages);
        if (SessionData.selectedLanguage != null)
            btSkillLanguages.setText(SessionData.selectedLanguage.title);
        btSkillLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdLanguagesList();
                adSkillLanguage.show();
            }
        });

        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (SessionData.selectedCategory == null) {
                    Toast.makeText(MainActivity.this, "Please Select Category", Toast.LENGTH_SHORT).show();
                    return;
                } else if (SessionData.selectedSpecialization == null) {
                    Toast.makeText(MainActivity.this, "Please Select Specialization ", Toast.LENGTH_SHORT).show();
                    return;
                }

                PostUserSpecializationAsyncTask postUserSpecializationAsyncTask
                        = new PostUserSpecializationAsyncTask(
                        SessionData.currentUser.getId(),
                        SessionData.selectedSpecialization.id,
                        SessionData.selectedCategory.id,
                        SessionData.selectedLanguage.languageId);

                postUserSpecializationAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        adb.setView(view);
        adNewSkill = adb.create();
    }

    private void createAdIndicatekill() {
        SessionData.selectedCategory = null;
        SessionData.selectedSpecialization = null;
        SessionData.selectedSpecializations = null;
        SessionData.selectedLanguage = SessionData.currentUser.getLanguages().get(0);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Indicate Skill");
        View view = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.new_skill_alert_dialog, null);

        btCategory = view.findViewById(R.id.btCategory);
        btCategory.setText("Select Category");
        btCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdCategoryList();
                adCategoryList.show();
            }
        });

        btSpecializations = view.findViewById(R.id.btSpecializations);
        btSpecializations.setText("Select Specialization");
        btSpecializations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createAdSpecializationList()) {
                    adSpecList.show();
                }
            }
        });

        btSkillLanguages = view.findViewById(R.id.btSkillLanguages);
        btSkillLanguages.setText(SessionData.selectedLanguage.title);
        btSkillLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdLanguagesList();
                adSkillLanguage.show();
            }
        });

        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (SessionData.selectedCategory == null) {
                    Toast.makeText(MainActivity.this, "Please Select Category", Toast.LENGTH_SHORT).show();
                    return;
                } else if (SessionData.selectedSpecialization == null) {
                    Toast.makeText(MainActivity.this, "Please Select Specialization ", Toast.LENGTH_SHORT).show();
                    return;
                }

                PostCouchSpecializationAsyncTask postCouchSpecializationAsyncTask
                        = new PostCouchSpecializationAsyncTask(
                        SessionData.currentUser.getId(),
                        SessionData.selectedSpecialization.id,
                        SessionData.selectedCategory.id,
                        SessionData.selectedLanguage.languageId);

                postCouchSpecializationAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        adb.setView(view);
        adIncdicateSkill = adb.create();
    }

    private void createAdCategoryList() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setItems(SessionData.getCategoriesTitlesArray(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Index - " + i, Toast.LENGTH_SHORT).show();

                SessionData.selectedCategory = SessionData.categories.get(i);

                btCategory.setText(SessionData.selectedCategory.title);
            }
        });
        adb.setNegativeButton("Cancel", null);
        adb.setTitle("Select Category");

        // Создаём объект класса AlertDialog
        adCategoryList = adb.create();
    }

    private void createAdLanguagesList() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setItems(SessionData.currentUser.getLanguagesTitlesArray()
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Index - " + i, Toast.LENGTH_SHORT).show();

                        SessionData.selectedLanguage = SessionData.currentUser.getLanguages().get(i);

                        btSkillLanguages.setText(SessionData.selectedLanguage.title);
                    }
                });
        adb.setNegativeButton("Cancel", null);
        adb.setTitle("Select Language");

        // Создаём объект класса AlertDialog
        adSkillLanguage = adb.create();
    }

    private boolean createAdSpecializationList() {
        if (SessionData.selectedCategory == null) {
            Toast.makeText(this, "Please select categoty", Toast.LENGTH_SHORT).show();
            return false;
        }

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setItems(SessionData.getSpecializationsTitlesArray(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Index - " + i, Toast.LENGTH_SHORT).show();

                SessionData.selectedSpecialization = SessionData.selectedSpecializations.get(i);

                btSpecializations.setText(SessionData.selectedSpecialization.title);
            }
        });
        adb.setNegativeButton("Cancel", null);
        adb.setTitle("Select Specialization");

        // Создаём объект класса AlertDialog
        adSpecList = adb.create();
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navUser:
                    setTitle("User Info");
                    rlUser.setVisibility(View.VISIBLE);
                    rlStudent.setVisibility(View.GONE);
                    rlCouch.setVisibility(View.GONE);
                    return true;
                case R.id.navStudent:
                    setTitle("Student");
                    rlUser.setVisibility(View.GONE);
                    rlStudent.setVisibility(View.VISIBLE);
                    rlCouch.setVisibility(View.GONE);
                    return true;
                case R.id.navCouch:
                    setTitle("Couch");
                    rlUser.setVisibility(View.GONE);
                    rlStudent.setVisibility(View.GONE);
                    rlCouch.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    private void initializeListTest(String[] awaitSkillsArray,
                                    String[] processSkillsArray,
                                    String[] doneSkillsArray) {

        // названия компаний (групп)
        String[] groups = new String[]{"Await Skills", "In Process Skills", "Done Skills"};

        // названия телефонов (элементов)
        String[] phonesHTC = awaitSkillsArray.clone();
        String[] phonesSams = processSkillsArray.clone();
        String[] phonesLG = doneSkillsArray.clone();

        // коллекция для групп
        ArrayList<Map<String, String>> groupData;

        // коллекция для элементов одной группы
        ArrayList<Map<String, String>> childDataItem;

        // общая коллекция для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> childData;
        // в итоге получится childData = ArrayList<childDataItem>

        // список атрибутов группы или элемента
        Map<String, String> m;

        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании
            groupData.add(m);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};


        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список атрибутов для каждого элемента
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone); // название телефона
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{"phoneName"};
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        int childTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvUserSkills.setAdapter(adapter);
        elvUserSkills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "index = " + i, Toast.LENGTH_SHORT).show();
            }
        });
        elvUserSkills.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i == 1 || i == 2) {
                    Intent intent = new Intent(MainActivity.this, CouchActivity.class);
                    startActivity(intent);
                }

                System.out.println(i);
                System.out.println(i1);
                return false;
            }
        });
    }

    private void initializeListCouchSkills(
            String[] processSkillsArray,
            String[] doneSkillsArray,
            String[] masterSkillsArray) {

        // названия компаний (групп)
        String[] groups = new String[]{"In Process Skills", "Done Skills", "Master Skills"};

        // названия телефонов (элементов)
        String[] phonesHTC = processSkillsArray.clone();
        String[] phonesSams = doneSkillsArray.clone();
        String[] phonesLG = masterSkillsArray.clone();

        // коллекция для групп
        ArrayList<Map<String, String>> groupData;

        // коллекция для элементов одной группы
        ArrayList<Map<String, String>> childDataItem;

        // общая коллекция для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> childData;
        // в итоге получится childData = ArrayList<childDataItem>

        // список атрибутов группы или элемента
        Map<String, String> m;

        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании
            groupData.add(m);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};


        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список атрибутов для каждого элемента
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone); // название телефона
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{"phoneName"};
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        int childTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvCouchSkills.setAdapter(adapter);
        elvCouchSkills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "index = " + i, Toast.LENGTH_SHORT).show();
            }
        });
        elvCouchSkills.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i == 0 || i == 1) {
                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                    startActivity(intent);
                }

                System.out.println(i);
                System.out.println(i1);
                return false;
            }
        });
    }

    private void initializeElvUserSkills() {
        elvUserSkills = findViewById(R.id.elvUserSkills);

        String[] ar1 = SessionData.currentUser.getAwaitSkillsTitles();
        String[] ar2 = SessionData.currentUser.getProcessSkillsTitles();
        String[] ar3 = SessionData.currentUser.getDoneSkillsTitles();

        initializeListTest(ar1, ar2, ar3);
    }

    private void initializeElvCouchSkills() {
        elvCouchSkills = findViewById(R.id.elvCouchSkillList);

        String[] ar1 = SessionData.currentUser.getInProgressSkillsTitlesCouchMode();
        String[] ar2 = SessionData.currentUser.getDoneSkillsTitlesCouchMode();
        String[] ar3 = SessionData.currentUser.getMasterSkillsTitles();

        initializeListCouchSkills(ar1, ar2, ar3);
    }

    private void initialize() {
        initizalizeUserLayout();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        Toast.makeText(this
                , "Hello, " + SessionData.currentUser.getFirstName() + "!"
                , Toast.LENGTH_SHORT).show();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        rlStudent = findViewById(R.id.rlStudent);
        rlCouch = findViewById(R.id.rlCouch);
        btNewSkill = findViewById(R.id.btNewSkill);
        btIndicateSkill = findViewById(R.id.btIndicateSkill);

        tvNewRequestTitle = findViewById(R.id.tvNewRequestTitle);
        btAcceptRequest = findViewById(R.id.btAcceptRequest);
        btNextRequest = findViewById(R.id.btNextRequest);

        btNewSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdNewSkill();
                adNewSkill.show();
            }
        });

        btIndicateSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAdIndicatekill();
                adIncdicateSkill.show();
            }
        });

        btAcceptRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptStudentRequestAsyncTask acceptStudentRequestAsyncTask =
                        new AcceptStudentRequestAsyncTask(
                                SessionData.currentUser.getId()
                                , SessionData.currentStudentRequest.id);
                acceptStudentRequestAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        btNextRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionData.nextStudentRequest();
            }
        });

        GetCatAndSpecAsyncTask getCategoriesAsyncTask =
                new GetCatAndSpecAsyncTask();
        getCategoriesAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        GetUserSpecializationsAsyncTask getUserSpecializationsAsyncTask =
                new GetUserSpecializationsAsyncTask();
        getUserSpecializationsAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        GetNewStudentsAsyncTask getNewStudentsAsyncTask =
                new GetNewStudentsAsyncTask();
        getNewStudentsAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        navigation.setSelectedItemId(R.id.navUser);
    }

    private void updateStudentRequest() {
        String message = SessionData.nextStudentRequest();

        tvNewRequestTitle.setText(message);
    }

    class GetCatAndSpecAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            ApiWorker.getCategories();
            ApiWorker.getSpecializations();
            return null;
        }
    }

    class PostUserSpecializationAsyncTask extends AsyncTask<Void, Void, Void> {
        int userId;
        int specializationId;
        int categoryId;
        int languageId;

        ApiWorker.NewUserSpecializationStatus status;

        public PostUserSpecializationAsyncTask(int userId, int specializationId, int categoryId, int languageId) {
            this.userId = userId;
            this.specializationId = specializationId;
            this.categoryId = categoryId;
            this.languageId = languageId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            status = ApiWorker.postUserSpecialization(userId, specializationId, categoryId, languageId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (status.equals(ApiWorker.NewUserSpecializationStatus.Correct)) {
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            } else if (status.equals(ApiWorker.NewUserSpecializationStatus.ALREADY_EXIST)) {
                Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class PostCouchSpecializationAsyncTask extends AsyncTask<Void, Void, Void> {
        int userId;
        int specializationId;
        int categoryId;
        int languageId;

        ApiWorker.NewCouchSpecializationStatus status;

        public PostCouchSpecializationAsyncTask(int userId, int specializationId, int categoryId, int languageId) {
            this.userId = userId;
            this.specializationId = specializationId;
            this.categoryId = categoryId;
            this.languageId = languageId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            status = ApiWorker.postCouchSpecialization(userId, specializationId, categoryId, languageId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (status.equals(ApiWorker.NewCouchSpecializationStatus.Correct)) {
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                GetMasterSkillsAsyncTask getMasterSkillsAsyncTask =
                        new GetMasterSkillsAsyncTask();
                getMasterSkillsAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (status.equals(ApiWorker.NewCouchSpecializationStatus.ALREADY_EXIST)) {
                Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class GetUserSpecializationsAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            boolean flag = true;

            while (flag) {
                try {
                    ApiWorker.getUserSpecializations(SessionData.currentUser.getId());
                    ApiWorker.getMasterSkills(SessionData.currentUser.getId());
                    ApiWorker.getStudents(SessionData.currentUser.getId());

                    publishProgress();
                    Thread.sleep(180000); // 60000
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            initializeElvUserSkills();
            initializeElvCouchSkills();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class GetMasterSkillsAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean isCorrect;

        @Override
        protected Void doInBackground(Void... voids) {
            ApiWorker.getMasterSkills(SessionData.currentUser.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            initializeElvCouchSkills();
        }
    }

    class GetNewStudentsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ApiWorker.getNewStudentsRequests(SessionData.currentUser.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateStudentRequest();
            super.onPostExecute(aVoid);
        }
    }

    class AcceptStudentRequestAsyncTask extends AsyncTask<Void, Void, Void> {
        int couchId;
        int userSpecId;
        boolean isCorrect;

        public AcceptStudentRequestAsyncTask(int couchId, int userSpecId) {
            this.couchId = couchId;
            this.userSpecId = userSpecId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            isCorrect = ApiWorker.acceptStudentRequest(couchId, userSpecId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (isCorrect) {
                Toast.makeText(MainActivity.this, "Всё гуд", Toast.LENGTH_SHORT).show();
                updateStudentRequest();
            } else {
                Toast.makeText(MainActivity.this, "Не гуд", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }
    }

    class UpdateUserInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        boolean isCorrect;
        ProgressDialog progressDialog;

        public UpdateUserInfoAsyncTask(User user) {
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Update User Info");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            isCorrect = ApiWorker.updateUserInfo(user);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();

            if (isCorrect) {
                initizalizeUserLayout();
                Toast.makeText(MainActivity.this, "Correct request", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Some error...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
