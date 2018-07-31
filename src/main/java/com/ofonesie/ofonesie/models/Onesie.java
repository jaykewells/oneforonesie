package com.ofonesie.ofonesie.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Onesie {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=120, message = "Title Must Be At Least 3 Characters Long") //Limits length of title for display
    private String title;

    @NotNull
    @Size(min=1, message = "Description Must Not Be Empty")
    private String description;

    public Onesie(){}

    public Onesie(String aTitle, String aDescription){
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
}
