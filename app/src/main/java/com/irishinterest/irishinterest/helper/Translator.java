package com.irishinterest.irishinterest.helper;

public final class Translator {

    /**
     * This class is used to translate strings of text. If in the future there is ever a need to
     * support multiple languages this will make it easier.
     *
     * Basic idea is that whenever we set a text to a GUI object we set it dynamically
     * so that it passes through this translator. This translator fetches a string translate
     * from the translates ENUM depending on the language.
     *
     */

    public Translator() {
        //Todo
    }

    public static String translate(String translate){
        return Translates.getTranslateTextValue(translate);
    }
}
