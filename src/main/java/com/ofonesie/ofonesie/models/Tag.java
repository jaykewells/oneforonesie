package com.ofonesie.ofonesie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=50, message = "Tag Name Must Be At Least 3 Characters and Less Than 50")
    private String title;

    @Size(max=300, message= "Description Must Be Less than 300 Characters")
    private String description;

    @ManyToOne
    private Category category;



    public Tag(){}

    public Tag(String aTitle, String aDesc){

        this.title = aTitle;
        this.description = aDesc;

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
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
