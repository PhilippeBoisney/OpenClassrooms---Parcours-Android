package com.openclassrooms.savemytrip.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

/**
 * Created by Philippe on 08/03/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId"))
public class Item {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String text;
    private int category;
    private Boolean isSelected;
    private long userId;

    public Item() { }

    public Item(String text, int category, long userID) {
        this.text = text;
        this.category = category;
        this.userId = userID;
        this.isSelected = false;
    }

    // --- GETTER ---
    public long getId() { return id; }
    public String getText() { return text; }
    public int getCategory() { return category; }
    public Boolean getSelected() { return isSelected; }
    public long getUserId() { return userId; }

    // --- SETTER ---
    public void setId(long id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setCategory(int category) { this.category = category; }
    public void setSelected(Boolean selected) { isSelected = selected; }
    public void setUserId(long userId) { this.userId = userId; }

    // --- UTILS ---
    public static Item fromContentValues(ContentValues values) {
        final Item item = new Item();
        if (values.containsKey("text")) item.setText(values.getAsString("text"));
        if (values.containsKey("category")) item.setCategory(values.getAsInteger("category"));
        if (values.containsKey("isSelected")) item.setSelected(values.getAsBoolean("isSelected"));
        if (values.containsKey("userId")) item.setUserId(values.getAsLong("userId"));
        return item;
    }
}