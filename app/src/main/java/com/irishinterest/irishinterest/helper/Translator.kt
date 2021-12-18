package com.irishinterest.irishinterest.helper

import com.irishinterest.irishinterest.helper.Translates.Companion.getTranslateTextValue
import com.irishinterest.irishinterest.helper.Translates

object Translator {
    fun translate(translate: String?): String {
        return getTranslateTextValue(translate!!)
    }
}