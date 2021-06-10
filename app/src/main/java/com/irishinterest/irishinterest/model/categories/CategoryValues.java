package com.irishinterest.irishinterest.model.categories;

import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data;

import java.util.ArrayList;

public class CategoryValues extends Data {

    private ArrayList<Category> categoryList;

    public CategoryValues(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }
}
