package com.openclassrooms.savemytrip;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.savemytrip.database.SaveMyTripDatabase;
import com.openclassrooms.savemytrip.models.Item;
import com.openclassrooms.savemytrip.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Philippe on 09/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ItemDaoTest {

    // FOR DATA
    private SaveMyTripDatabase database;

    // DATA SET FOR TEST
    private static long USER_ID = 1;
    private static User USER_DEMO = new User(USER_ID, "Philippe", "https://www.google.fr, ");
    private static Item NEW_ITEM_PLACE_TO_VISIT = new Item("Visite cet endroit de rêve !", 0, USER_ID);
    private static Item NEW_ITEM_IDEA = new Item("On pourrait faire du chien de traîneau ?", 1, USER_ID);
    private static Item NEW_ITEM_RESTAURANTS = new Item("Ce restaurant à l'air sympa", 2, USER_ID);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SaveMyTripDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetUser() throws InterruptedException {
        // BEFORE : Adding a new user
        this.database.userDao().createUser(USER_DEMO);
        // TEST
        User user = LiveDataTestUtil.getValue(this.database.userDao().getUser(USER_ID));
        assertTrue(user.getUsername().equals(USER_DEMO.getUsername()) && user.getId() == USER_ID);
    }

    @Test
    public void getItemsWhenNoItemInserted() throws InterruptedException {
        // TEST
        List<Item> items = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID));
        assertTrue(items.isEmpty());
    }

    @Test
    public void insertAndGetItems() throws InterruptedException {
        // BEFORE : Adding demo user & demo items

        this.database.userDao().createUser(USER_DEMO);
        this.database.itemDao().insertItem(NEW_ITEM_PLACE_TO_VISIT);
        this.database.itemDao().insertItem(NEW_ITEM_IDEA);
        this.database.itemDao().insertItem(NEW_ITEM_RESTAURANTS);

        // TEST
        List<Item> items = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID));
        assertTrue(items.size() == 3);
    }

    @Test
    public void insertAndUpdateItem() throws InterruptedException {
        // BEFORE : Adding demo user & demo items. Next, update item added & re-save it
        this.database.userDao().createUser(USER_DEMO);
        this.database.itemDao().insertItem(NEW_ITEM_PLACE_TO_VISIT);
        Item itemAdded = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID)).get(0);
        itemAdded.setSelected(true);
        this.database.itemDao().updateItem(itemAdded);

        //TEST
        List<Item> items = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID));
        assertTrue(items.size() == 1 && items.get(0).getSelected());
    }

    @Test
    public void insertAndDeleteItem() throws InterruptedException {
        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.
        this.database.userDao().createUser(USER_DEMO);
        this.database.itemDao().insertItem(NEW_ITEM_PLACE_TO_VISIT);
        Item itemAdded = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID)).get(0);
        this.database.itemDao().deleteItem(itemAdded.getId());

        //TEST
        List<Item> items = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID));
        assertTrue(items.isEmpty());
    }
}
