package com.irishinterest.irishinterest.network.helper;

public enum ApiConstants {
    API_KEY("testApiKey"),
    TOKEN("token"),
    VALUE("value"),
    API_URL("https://irishinterest.ie/API/rest/"),
    SERVER_SIGNATURE("38735FAA2F49335E665B76EBAD6C1AE3C9532B25FB2989AD31B5BACE83"),
    USER_APP_SIGNATURE("08d6b25183e0191def96ac96fe1fd127");

    private String key;

    ApiConstants(String key) {
        this.key = key;

    }

    public String getKey() {
        return key;
    }
}
