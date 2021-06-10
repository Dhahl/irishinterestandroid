package com.irishinterest.irishinterest.model.books;

import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data;

import java.util.ArrayList;

public class BookValues extends Data {
    private ArrayList<Book> books;

    public BookValues(ArrayList<Book> books) {
        this.books = books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
