package com.irishinterest.irishinterest.model.authors;

import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data;

import java.util.ArrayList;

public class AuthorValues extends Data {
    private ArrayList<Author> authors;

    public AuthorValues(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }
}
