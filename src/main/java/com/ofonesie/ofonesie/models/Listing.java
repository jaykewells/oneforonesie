package com.ofonesie.ofonesie.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Listing {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=120, message = "Title Must Be At Least 3 Characters Long") //Limits length of title for display
    private String title;

    @NotNull
    @Size(min=1, message = "Description Must Not Be Empty")
    private String description;

    //TODO: Rework as HashMap with the Category Name as the Key and the Tag as the value
    private String size;
    private String season;
    private String color;
    private String theme;



//TODO: Add a Tag object field for each Category Associated With a Onesie
    public Listing(){}

    public Listing(String aTitle, String aDescription){
        this.title = aTitle;
        this.description = aDescription;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
