
package com.openclassrooms.wonder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("created_on")
    @Expose
    private Integer createdOn;

    // --- GETTERS ---

    public User getUser() { return user; }
    public String getComment() { return comment; }
    public Integer getCreatedOn() { return createdOn; }

    // --- SETTERS ---

    public void setUser(User user) { this.user = user; }
    public void setComment(String comment) { this.comment = comment; }
    public void setCreatedOn(Integer createdOn) { this.createdOn = createdOn; }
}
