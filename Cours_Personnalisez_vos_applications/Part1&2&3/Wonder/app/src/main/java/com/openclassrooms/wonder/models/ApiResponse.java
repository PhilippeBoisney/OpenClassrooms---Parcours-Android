package com.openclassrooms.wonder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Philippe on 27/03/2018.
 */

public class ApiResponse {

    @SerializedName("projects")
    @Expose
    private List<Project> projects;

    @SerializedName("project")
    @Expose
    private Project project;

    @SerializedName("comments")
    @Expose
    private List<Comment> comments;

    // --- GETTERS ---

    public List<Project> getProjects() { return projects;  }
    public Project getProject() { return project; }
    public List<Comment> getComments() { return comments; }

    // --- SETTERS ---

    public void setProjects(List<Project> projects) { this.projects = projects; }
    public void setProject(Project project) { this.project = project; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
}
