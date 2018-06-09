package com.easylabs.globalcoachsystemandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.model.Message;

/**
 * Created by Maxim on 05.04.2018.
 */

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView timeText;
    TextView nameText;
    ImageView profileImage;

    ReceivedMessageHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        nameText = (TextView) itemView.findViewById(R.id.text_message_name);
        profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
    }

    void bind(Message message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        timeText.setText("12:31");
     //   nameText.setText(message.getSender().);

        // Insert the profile image from the URL into the ImageView.
        // Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
    }
}