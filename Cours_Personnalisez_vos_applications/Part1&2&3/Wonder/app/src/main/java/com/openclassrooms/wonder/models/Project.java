
package com.openclassrooms.wonder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("stats")
    @Expose
    private Stats stats;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("covers")
    @Expose
    private Covers covers;

    // --- GETTERS ---

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Stats getStats() { return stats; }
    public String getDescription() { return description; }
    public Covers getCovers() { return covers;
    }
    // --- SETTERS ---

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setStats(Stats stats) { this.stats = stats; }
    public void setDescription(String description) { this.description = description; }
    public void setCovers(Covers covers) { this.covers = covers; }
}
