package com.example.learnio.model;

import com.example.learnio.R;

import java.util.ArrayList;

public class Category {

    private String categoryName;
    private int imageRes;

    public Category(String categoryName, int imageRes) {
        this.categoryName = categoryName;
        this.imageRes = imageRes;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImageRes() {
        return imageRes;
    }

    public static ArrayList<Category> getCategories() {
        return new ArrayList<Category>() {{
            add(new Category("Coding", R.drawable.laptop));
            add(new Category("Photography", R.drawable.camera));
            add(new Category("Marketing", R.drawable.money));
            add(new Category("Music", R.drawable.microphone));
        }};
    }
}
