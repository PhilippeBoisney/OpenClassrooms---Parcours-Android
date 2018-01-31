package com.openclassrooms.firebaseoc.mentor_chat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.api.MessageHelper;
import com.openclassrooms.firebaseoc.api.UserHelper;
import com.openclassrooms.firebaseoc.base.BaseActivity;
import com.openclassrooms.firebaseoc.models.Message;
import com.openclassrooms.firebaseoc.models.User;

import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Philippe on 31/01/2018.
 */

public class MentorChatActivity extends BaseActivity implements MentorChatAdapter.Listener {

    // FOR DESIGN
    @BindView(R.id.activity_mentor_chat_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.activity_mentor_chat_text_view_recycler_view_empty) TextView textViewRecyclerViewEmpty;
    @BindView(R.id.activity_mentor_chat_message_edit_text) TextInputEditText editTextMessage;
    @BindView(R.id.activity_mentor_chat_image_chosen_preview) ImageView imageViewPreview;

    // FOR DATA
    private MentorChatAdapter mentorChatAdapter;
    @Nullable private User modelCurrentUser;
    private String currentChatName;

    // STATIC DATA FOR CHAT
    private static final String CHAT_NAME_ANDROID = "android";
    private static final String CHAT_NAME_BUG = "bug";
    private static final String CHAT_NAME_FIREBASE = "firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.configureRecyclerView(CHAT_NAME_ANDROID);
        this.configureToolbar();
        this.getCurrentUserFromFirestore();
    }

    @Override
    public int getFragmentLayout() { return R.layout.activity_mentor_chat; }

    // --------------------
    // ACTIONS
    // --------------------

    @OnClick(R.id.activity_mentor_chat_send_button)
    public void onClickSendMessage() { }

    @OnClick({ R.id.activity_mentor_chat_android_chat_button, R.id.activity_mentor_chat_firebase_chat_button, R.id.activity_mentor_chat_bug_chat_button})
    public void onClickChatButtons(ImageButton imageButton) {
        switch (Integer.valueOf(imageButton.getTag().toString())){
            case 10:
                this.configureRecyclerView(CHAT_NAME_ANDROID);
                break;
            case 20:
                this.configureRecyclerView(CHAT_NAME_FIREBASE);
                break;
            case 30:
                this.configureRecyclerView(CHAT_NAME_BUG);
                break;
        }
    }

    @OnClick(R.id.activity_mentor_chat_add_file_button)
    public void onClickAddFile() { }

    // --------------------
    // REST REQUESTS
    // --------------------

    private void getCurrentUserFromFirestore(){
        UserHelper.getUser(getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                modelCurrentUser = documentSnapshot.toObject(User.class);
            }
        });
    }

    // --------------------
    // UI
    // --------------------

    private void configureRecyclerView(String chatName){
        //Track current chat name
        this.currentChatName = chatName;
        //Configure Adapter & RecyclerView
        this.mentorChatAdapter = new MentorChatAdapter(generateOptionsForAdapter(MessageHelper.getAllMessageForChat(this.currentChatName)), Glide.with(this), this, this.getCurrentUser().getUid());
        mentorChatAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                recyclerView.smoothScrollToPosition(mentorChatAdapter.getItemCount()); // Scroll to bottom on new messages
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.mentorChatAdapter);
    }

    private FirestoreRecyclerOptions<Message> generateOptionsForAdapter(Query query){
        return new FirestoreRecyclerOptions.Builder<Message>()
                .setQuery(query, Message.class)
                .setLifecycleOwner(this)
                .build();
    }

    // --------------------
    // CALLBACK
    // --------------------

    @Override
    public void onDataChanged() {
        textViewRecyclerViewEmpty.setVisibility(this.mentorChatAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}

