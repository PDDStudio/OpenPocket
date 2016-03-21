package com.pddstudio.pocketlibrary.models;
/*
 * This Class was created by Patrick J
 * on 09.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import java.io.Serializable;

public class Category implements Serializable {

    private String categoryName;
    private String categoryDescription;
    private String categoryIcon;

    public Category() {}

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

}
