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
    private int size;
    private int season;
    private int color;
    private int theme;

    @ManyToOne
    private UserInfo owner;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getOwnerId() {
        return owner.getId();
    }

    public void setOwner(UserInfo owner) {
        this.owner = owner;
    }

    public UserInfo getOwner(){
        return this.owner;
    }
}
