
package com.openclassrooms.wonder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("appreciations")
    @Expose
    private Integer appreciations;

    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("comments")
    @Expose
    private Integer comments;

    // --- GETTERS ---

    public Integer getAppreciations() { return appreciations; }
    public Integer getViews() { return views; }
    public Integer getComments() { return comments; }

    // --- SETTERS ---

    public void setAppreciations(Integer appreciations) { this.appreciations = appreciations; }
    public void setViews(Integer views) { this.views = views; }
    public void setComments(Integer comments) { this.comments = comments; }
}
