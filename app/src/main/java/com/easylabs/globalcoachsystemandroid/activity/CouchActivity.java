package com.easylabs.globalcoachsystemandroid.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.data.SessionData;
import com.easylabs.globalcoachsystemandroid.model.Couch;
import com.easylabs.globalcoachsystemandroid.model.CouchUser;
import com.easylabs.globalcoachsystemandroid.model.StudentUser;
import com.easylabs.globalcoachsystemandroid.server.ApiWorker;

public class CouchActivity extends AppCompatActivity {

    TextView tvPhone;
    TextView tvName;
    TextView tvSecondName;
    TextView tvAge;

    private void initializeView() {
        tvPhone = findViewById(R.id.tvPhone);
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecondName);
        tvAge = findViewById(R.id.tvAge);
        /// тут остальня иницилзиация

        StudentUser student = SessionData.currentUser.student;

        tvName.setText("Имя: " + student.currentCouch.getFirstName());
        tvSecondName.setText("Фамилия: " + student.currentCouch.getLastName());
        tvAge.setText("Возраст: " + student.currentCouch.getAge());

        if (student.currentCouch.isPhoneIsVisible()) {
            tvPhone.setText("Телефон: " + student.currentCouch.getPhone());
        } else {
            tvPhone.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CouchActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        GetCouchSpec getCouchSpec = new GetCouchSpec();
        getCouchSpec.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class GetCouchSpec extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ApiWorker.getCouchSpec(SessionData.currentUser.student.currentUserSpec.id);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            initializeView();

        }
    }

}
