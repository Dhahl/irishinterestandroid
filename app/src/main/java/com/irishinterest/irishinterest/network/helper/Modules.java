package com.irishinterest.irishinterest.network.helper;

public enum Modules {
    USER("user"),
    CATEGORY("categories"),
    BOOK("authors"),
    AUTHOR("books"),
    ;

    private String key;

    Modules(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
