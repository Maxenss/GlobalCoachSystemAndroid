package com.easylabs.globalcoachsystemandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.easylabs.globalcoachsystemandroid.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setTitle("Chat");
    }
}
