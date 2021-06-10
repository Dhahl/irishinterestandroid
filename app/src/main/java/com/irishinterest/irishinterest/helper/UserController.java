package com.irishinterest.irishinterest.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.model.user.UserValues;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserController {

    private static UserController userController;

    public static synchronized UserController getInstance(){
        if(userController == null){
            userController = new UserController();
        }
        return  userController;
    }

    /**
     * This function is used to read the user from within shared preferences.
     *
     * @param context - The context of the application.
     * @return - User values object or null if there is no user saved.
     */
    public static synchronized UserValues getUser(Context context){
        UserValues userValues = new UserValues();
        try {
            SharedPreferences sp = context.getSharedPreferences(
                   context.getString(R.string.saved_user),Context.MODE_PRIVATE);
            String userJson = sp.getString(context.getString(R.string.saved_user),"");
            if(userJson.equals("")){
                return null;
            }
            userValues = new ObjectMapper().readValue(userJson, UserValues.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userValues;
    }

    /**
     * This function is used to save a user object to shared preferences.
     *
     * @param userValues - The provided user data.
     * @param context - The context of the application.
     */
    public static synchronized void saveUserData(UserValues userValues, Context context){
        try {
            SharedPreferences sp = context.getSharedPreferences(
                    context.getString(R.string.saved_user), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            String userString = new ObjectMapper().writeValueAsString(userValues);
            editor.putString(context.getString(R.string.saved_user),userString);
            editor.apply();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static synchronized boolean isUserLoggedIn(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.is_user_logged_in), Context.MODE_PRIVATE);
       return sp.getBoolean(context.getString(R.string.is_user_logged_in),false);
    }

    public static synchronized void setUserLoggedIn(boolean isUserLoggedIn, Context context){
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.is_user_logged_in), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(context.getString(R.string.is_user_logged_in), isUserLoggedIn);
        editor.apply();
    }

    public static synchronized void logout(Context context){
        setUserLoggedIn(false, context);
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.saved_user), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(context.getString(R.string.saved_user),"");
        editor.apply();
    }
}
