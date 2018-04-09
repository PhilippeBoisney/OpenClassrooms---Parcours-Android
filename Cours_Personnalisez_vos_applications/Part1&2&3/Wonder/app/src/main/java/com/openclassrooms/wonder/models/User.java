
package com.openclassrooms.wonder.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("images")
    @Expose
    private Images images;

    // --- GETTERS ---

    public String getUsername() { return username; }
    public Images getImages() { return images; }

    // --- SETTERS ---

    public void setUsername(String username) { this.username = username; }
    public void setImages(Images images) { this.images = images; }
}
