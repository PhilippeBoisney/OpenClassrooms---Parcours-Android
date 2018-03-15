package com.openclassrooms.savemytrip.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.openclassrooms.savemytrip.database.dao.ItemDao;
import com.openclassrooms.savemytrip.database.dao.UserDao;
import com.openclassrooms.savemytrip.models.Item;
import com.openclassrooms.savemytrip.models.User;

/**
 * Created by Philippe on 27/02/2018.
 */

@Database(entities = {Item.class, User.class}, version = 1, exportSchema = false)
public abstract class SaveMyTripDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile SaveMyTripDatabase INSTANCE;

    // --- DAO ---
    public abstract ItemDao itemDao();
    public abstract UserDao userDao();

    // --- INSTANCE ---
    public static SaveMyTripDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveMyTripDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyTripDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // ---

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("username", "Philippe");
                contentValues.put("urlPicture", "https://oc-user.imgix.net/users/avatars/15175844164713_frame_523.jpg?auto=compress,format&q=80&h=100&dpr=2");

                db.insert("User", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }
}


