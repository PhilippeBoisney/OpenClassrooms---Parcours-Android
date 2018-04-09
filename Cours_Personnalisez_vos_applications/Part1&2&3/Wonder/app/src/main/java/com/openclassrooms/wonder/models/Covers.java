
package com.openclassrooms.wonder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covers {

    @SerializedName("original")
    @Expose
    private String original;

    // --- GETTERS ---

    public String getOriginal() { return original; }

    // --- SETTERS ---
    public void setOriginal(String original) { this.original = original; }
}
