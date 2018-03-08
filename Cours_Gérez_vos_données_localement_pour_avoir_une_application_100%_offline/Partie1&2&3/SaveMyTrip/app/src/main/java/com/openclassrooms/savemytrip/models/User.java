package com.openclassrooms.savemytrip.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Philippe on 08/03/2018.
 */

@Entity
public class User {

    @PrimaryKey
    private long id;
    private String username;
    private String urlPicture;

    public User(long id, String username, String urlPicture) {
        this.id = id;
        this.username = username;
        this.urlPicture = urlPicture;
    }

    // --- GETTER ---

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getUrlPicture() { return urlPicture; }

    // --- SETTER ---

    public void setId(long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setUrlPicture(String urlPicture) { this.urlPicture = urlPicture; }
}