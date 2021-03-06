package com.easylabs.globalcoachsystemandroid.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.data.SessionData;
import com.easylabs.globalcoachsystemandroid.server.ApiWorker;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        GetStudentSpec getStudentSpec = new GetStudentSpec();
        getStudentSpec.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class GetStudentSpec extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ApiWorker.getStudentSpec(SessionData.currentUser.couch.currentUserSpec.id);
            return null;
        }
    }
}
