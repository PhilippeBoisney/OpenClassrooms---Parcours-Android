package com.openclassrooms.firebaseoc.api;

import com.google.firebase.firestore.Query;

/**
 * Created by Philippe on 31/01/2018.
 */

public class MessageHelper {

    private static final String COLLECTION_NAME = "messages";

    // --- GET ---

    public static Query getAllMessageForChat(String chat){
        return ChatHelper.getChatCollection().document(chat).collection(COLLECTION_NAME).orderBy("dateCreated").limit(50);
    }
}
