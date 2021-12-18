package com.irishinterest.irishinterest.helper

import android.content.Context
import kotlin.jvm.Synchronized
import com.irishinterest.irishinterest.helper.UserController
import com.irishinterest.irishinterest.model.user.UserValues
import android.content.SharedPreferences
import com.irishinterest.irishinterest.R
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.JsonProcessingException
import java.io.IOException

object UserController {

    /**
     * This function is used to read the user from within shared preferences.
     *
     * @param context - The context of the application.
     * @return - User values object or null if there is no user saved.
     */
    @JvmStatic
    fun getUser(context: Context): UserValues? {
        var userValues: UserValues? = UserValues()
        try {
            val sp = context.getSharedPreferences(
                context.getString(R.string.saved_user), Context.MODE_PRIVATE
            )
            val userJson = sp.getString(context.getString(R.string.saved_user), "")
            if (userJson == "") {
                return null
            }
            userValues = ObjectMapper().readValue(userJson, UserValues::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return userValues
    }

    /**
     * This function is used to save a user object to shared preferences.
     *
     * @param userValues - The provided user data.
     * @param context - The context of the application.
     */
    @JvmStatic
    fun saveUserData(userValues: UserValues?, context: Context) {
        try {
            val sp = context.getSharedPreferences(
                context.getString(R.string.saved_user), Context.MODE_PRIVATE
            )
            val editor = sp.edit()
            val userString = ObjectMapper().writeValueAsString(userValues)
            editor.putString(context.getString(R.string.saved_user), userString)
            editor.apply()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun isUserLoggedIn(context: Context): Boolean {
        val sp = context.getSharedPreferences(
            context.getString(R.string.is_user_logged_in), Context.MODE_PRIVATE
        )
        return sp.getBoolean(context.getString(R.string.is_user_logged_in), false)
    }

    @JvmStatic
    fun setUserLoggedIn(isUserLoggedIn: Boolean, context: Context) {
        val sp = context.getSharedPreferences(
            context.getString(R.string.is_user_logged_in), Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.putBoolean(context.getString(R.string.is_user_logged_in), isUserLoggedIn)
        editor.apply()
    }

    @JvmStatic
    fun logout(context: Context) {
        setUserLoggedIn(false, context)
        val sp = context.getSharedPreferences(
            context.getString(R.string.saved_user), Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.putString(context.getString(R.string.saved_user), "")
        editor.apply()
    }
}