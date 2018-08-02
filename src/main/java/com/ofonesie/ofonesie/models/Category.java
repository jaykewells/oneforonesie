package com.ofonesie.ofonesie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=50, message = "Category Name Must Be At Least 3 Characters and Less Than 50")
    private String title;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Tag> tags = new ArrayList<>();

    public Category(){}

    public Category(String aTitle){
        this.title = aTitle;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTags(){
        return tags;
    }

}
