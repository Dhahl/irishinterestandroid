package com.irishinterest.irishinterest.helper

import com.irishinterest.irishinterest.helper.Translates

enum class Translates(val translate: String, val translateText: String) {
    /**
     * This is where we keep all the translates.
     */
    EN_IRISH_INTEREST("en-irishInterest", "Irish Interest"), EN_LOGIN(
        "en-login",
        "Login"
    ),
    EN_LOGOUT("en-logout", "Logout"), EN_SIGN_UP("en-signUp", "Sign up");

    companion object {
        @JvmStatic
        fun getTranslateTextValue(translateText: String): String {
            for (translates in values()) {
                if (translates.translate == translateText) {
                    return translates.translateText
                }
            }
            return translateText
        }
    }
}