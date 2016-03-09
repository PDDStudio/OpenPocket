package com.pddstudio.pocketlibrary;
/*
 * This Class was created by Patrick J
 * on 09.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import com.pddstudio.pocketlibrary.models.Category;
import com.pddstudio.pocketlibrary.models.Profile;

import java.util.LinkedList;
import java.util.List;

import io.paperdb.Paper;

public class CategoryManager {

    //the key under which the categories are saved
    private static final String CATEGORY_ITEMS = "mCategoryItems";

    protected CategoryManager() {}

    public List<Category> getCategoryList() {
        return Paper.book().read(CATEGORY_ITEMS, new LinkedList<Category>());
    }

    public void addCategory(Category category) {
        Paper.book().write(CATEGORY_ITEMS, Paper.book().read(CATEGORY_ITEMS, new LinkedList<Category>()).add(category));
    }

    public void removeCategory(Category category) {
        List<Category> categories = Paper.book().read(CATEGORY_ITEMS, new LinkedList<Category>());
        for(int i = 0; i < categories.size(); i++) {
            if(category.getCategoryName().equals(categories.get(i).getCategoryName())) categories.remove(i);
        }
    }

}
