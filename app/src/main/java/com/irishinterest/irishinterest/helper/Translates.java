package com.irishinterest.irishinterest.helper;

public enum Translates {

    /**
     * This is where we keep all the translates.
     */

    EN_IRISH_INTEREST("en-irishInterest", "Irish Interest"),
    EN_LOGIN("en-login", "Login"),
    EN_LOGOUT("en-logout", "Logout"),
    EN_SIGN_UP("en-signUp", "Sign up");

    private String translate;
    private String translateText;

    Translates(String translate, String translateText){
        this.translate = translate;
        this.translateText = translateText;
    }

    public static String getTranslateTextValue(String translateText){
        for (Translates translates : Translates.values()){
            if(translates.getTranslate().equals(translateText)){
                return translates.getTranslateText();
            }
        }
        return translateText;
    }

    public String getTranslate() {
        return translate;
    }

    public String getTranslateText() {
        return translateText;
    }
}
