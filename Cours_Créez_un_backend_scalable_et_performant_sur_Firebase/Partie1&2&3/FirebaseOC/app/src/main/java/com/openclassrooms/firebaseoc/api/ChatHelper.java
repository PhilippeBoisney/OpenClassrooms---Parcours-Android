package com.openclassrooms.firebaseoc.api;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by Philippe on 31/01/2018.
 */

public class ChatHelper {

    private static final String COLLECTION_NAME = "chats";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getChatCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
}
