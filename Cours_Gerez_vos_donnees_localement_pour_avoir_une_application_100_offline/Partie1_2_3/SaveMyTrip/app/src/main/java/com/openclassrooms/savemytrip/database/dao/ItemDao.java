package com.openclassrooms.savemytrip.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.openclassrooms.savemytrip.models.Item;

import java.util.List;

/**
 * Created by Philippe on 27/02/2018.
 */

@Dao
public interface ItemDao {

    @Query("SELECT * FROM Item WHERE userId = :userId")
    LiveData<List<Item>> getItems(long userId);

    @Query("SELECT * FROM Item WHERE userId = :userId")
    Cursor getItemsWithCursor(long userId);

    @Insert
    long insertItem(Item item);

    @Update
    int updateItem(Item item);

    @Query("DELETE FROM Item WHERE id = :itemId")
    int deleteItem(long itemId);
}
