package com.irishinterest.irishinterest.network.api.helper

import com.irishinterest.irishinterest.network.helper.ApiConstants
import com.irishinterest.irishinterest.network.helper.Modules

object RequestBuilder {
    //CategoryValues
    fun getAllCategories(token: String?): String {
        return String.format(
            ApiConstants.API_URL.key + "value=%s&token=%s&apiKey=%s",
            Modules.CATEGORY.key,
            token,
            ApiConstants.API_KEY.key
        )
    }

    fun getCategoryById(token: String?, id: Int): String {
        return String.format(
            ApiConstants.API_URL.key + "value=%s&token=%s&apiKey=%s&id=%s",
            Modules.CATEGORY.key,
            token,
            ApiConstants.API_KEY.key,
            id
        )
    }

    //Books
    fun getLatestBooks(token: String?, page: Int): String {
        return String.format(
            ApiConstants.API_URL.key + "value=books&type=getLatest&offset=%s&apiKey=%s&token=%s",
            0,
            ApiConstants.API_KEY.key,
            token
        )
    }

    fun getBooksByCategory(token: String?, categoryId: String?, page: Int): String {
        //TODO
        return String.format("")
    }

    fun getBooksBySearch(token: String?, searchParam: String?, page: Int): String {
        //TODO
        return String.format("")
    }

    fun getBooksByAuthor(token: String?, authorId: Int, page: Int): String {
        //TODO
        return String.format("")
    }
}