package com.openclassrooms.firebaseoc.mentor_chat;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.models.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Philippe on 31/01/2018.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    //ROOT VIEW
    @BindView(R.id.activity_mentor_chat_item_root_view) RelativeLayout rootView;

    //PROFILE CONTAINER
    @BindView(R.id.activity_mentor_chat_item_profile_container) LinearLayout profileContainer;
    @BindView(R.id.activity_mentor_chat_item_profile_container_profile_image) ImageView imageViewProfile;
    @BindView(R.id.activity_mentor_chat_item_profile_container_is_mentor_image) ImageView imageViewIsMentor;

    //MESSAGE CONTAINER
    @BindView(R.id.activity_mentor_chat_item_message_container) RelativeLayout messageContainer;
    //IMAGE SENDED CONTAINER
    @BindView(R.id.activity_mentor_chat_item_message_container_image_sent_cardview) CardView cardViewImageSent;
    @BindView(R.id.activity_mentor_chat_item_message_container_image_sent_cardview_image) ImageView imageViewSent;
    //TEXT MESSAGE CONTAINER
    @BindView(R.id.activity_mentor_chat_item_message_container_text_message_container) LinearLayout textMessageContainer;
    @BindView(R.id.activity_mentor_chat_item_message_container_text_message_container_text_view) TextView textViewMessage;
    //DATE TEXT
    @BindView(R.id.activity_mentor_chat_item_message_container_text_view_date) TextView textViewDate;


    //FOR DATA
    private final int colorCurrentUser;
    private final int colorRemoteUser;

    public MessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        colorCurrentUser = ContextCompat.getColor(itemView.getContext(), R.color.colorAccent);
        colorRemoteUser = ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary);
    }

    public void updateWithMessage(Message message, String currentUserId, RequestManager glide){

        // Check if current user is the sender
        Boolean isCurrentUser = message.getUserSender().getUid().equals(currentUserId);

        // Update message TextView
        this.textViewMessage.setText(message.getMessage());
        this.textViewMessage.setTextAlignment(isCurrentUser ? View.TEXT_ALIGNMENT_TEXT_END : View.TEXT_ALIGNMENT_TEXT_START);

        // Update date TextView
        if (message.getDateCreated() != null) this.textViewDate.setText(this.convertDateToHour(message.getDateCreated()));

        // Update isMentor ImageView
        this.imageViewIsMentor.setVisibility(message.getUserSender().getIsMentor() ? View.VISIBLE : View.INVISIBLE);

        // Update profile picture ImageView
        if (message.getUserSender().getUrlPicture() != null)
            glide.load(message.getUserSender().getUrlPicture())
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageViewProfile);

        // Update image sent ImageView
        if (message.getUrlImage() != null){
            glide.load(message.getUrlImage())
                    .into(imageViewSent);
            this.imageViewSent.setVisibility(View.VISIBLE);
        } else {
            this.imageViewSent.setVisibility(View.GONE);
        }

        //Update Message Bubble Color Background
        ((GradientDrawable) textMessageContainer.getBackground()).setColor(isCurrentUser ? colorCurrentUser : colorRemoteUser);

        // Update all views alignment depending is current user or not
        this.updateDesignDependingUser(isCurrentUser);
    }

    private void updateDesignDependingUser(Boolean isSender){

        // PROFILE CONTAINER
        RelativeLayout.LayoutParams paramsLayoutHeader = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLayoutHeader.addRule(isSender ? RelativeLayout.ALIGN_PARENT_RIGHT : RelativeLayout.ALIGN_PARENT_LEFT);
        this.profileContainer.setLayoutParams(paramsLayoutHeader);

        // MESSAGE CONTAINER
        RelativeLayout.LayoutParams paramsLayoutContent = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLayoutContent.addRule(isSender ? RelativeLayout.LEFT_OF : RelativeLayout.RIGHT_OF, R.id.activity_mentor_chat_item_profile_container);
        this.messageContainer.setLayoutParams(paramsLayoutContent);

        // CARDVIEW IMAGE SEND
        RelativeLayout.LayoutParams paramsImageView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsImageView.addRule(isSender ? RelativeLayout.ALIGN_LEFT : RelativeLayout.ALIGN_RIGHT, R.id.activity_mentor_chat_item_message_container_text_message_container);
        this.cardViewImageSent.setLayoutParams(paramsImageView);

        this.rootView.requestLayout();
    }

    // ---

    private String convertDateToHour(Date date){
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        return dfTime.format(date);
    }
}
