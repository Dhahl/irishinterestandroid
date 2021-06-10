package com.irishinterest.irishinterest.network.api.helper;

import com.irishinterest.irishinterest.network.helper.ApiConstants;
import com.irishinterest.irishinterest.network.helper.Modules;

public final class RequestBuilder {

    //CategoryValues
    public static String getAllCategories(String token){
       return String.format(ApiConstants.API_URL.getKey() + "value=%s&token=%s&apiKey=%s", Modules.CATEGORY.getKey(), token, ApiConstants.API_KEY.getKey());
    }

    public static String getCategoryById(String token, int id){
        return String.format(ApiConstants.API_URL.getKey() + "value=%s&token=%s&apiKey=%s&id=%s", Modules.CATEGORY.getKey(), token, ApiConstants.API_KEY.getKey(), id);
    }

    //Books
    public static String getLatestBooks(String token, int page){
        return String.format(ApiConstants.API_URL.getKey() + "value=books&type=getLatest&offset=%s&apiKey=%s&token=%s", 0, ApiConstants.API_KEY.getKey(), token);
    }

    public static String getBooksByCategory(String token, String categoryId, int page){
        //TODO
        return String.format("");
    }

    public static String getBooksBySearch(String token, String searchParam, int page){
        //TODO
        return String.format("");
    }

    public static String getBooksByAuthor(String token, int authorId, int page){
        //TODO
        return String.format("");
    }
}
