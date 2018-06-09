package com.easylabs.globalcoachsystemandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.adapters.MessageListAdapter;
import com.easylabs.globalcoachsystemandroid.data.SessionData;
import com.easylabs.globalcoachsystemandroid.model.Message;
import com.easylabs.globalcoachsystemandroid.model.User;

import java.util.ArrayList;

public class MessageListActivity extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

     // System.out.println("MessageListAdapter onCreate()");

     // SessionData.currentUser = new User("Root", "/http", 1 );
     // User user2 = new User("Ne Root", "/nehht", 10);

     // ArrayList<Message> messageList = new ArrayList<>();
     // messageList.add(new Message("Привет, мир!", SessionData.currentUser, 123));
     // messageList.add(new Message("Пакет, мир!", user2, 123));



       // mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
       // mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
       // mMessageRecycler.setHasFixedSize(true);
//
       // mMessageAdapter = new MessageListAdapter(this, messageList);
       // mMessageRecycler.setAdapter(mMessageAdapter);
//
       // messageList.add(new Message("Привет, мир!", SessionData.currentUser, 123));
     //   messageList.add(new Message("Пакет, мир!", user2, 123));



    }
}
