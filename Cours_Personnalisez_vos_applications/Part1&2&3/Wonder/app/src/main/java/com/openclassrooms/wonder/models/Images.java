
package com.openclassrooms.wonder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("138")
    @Expose
    private String imageProfile;

    // --- GETTERS ---
    public String getImageProfile() { return imageProfile; }

    // --- SETTERS ---
    public void setImageProfile(String imageProfile) { this.imageProfile = imageProfile; }
}
