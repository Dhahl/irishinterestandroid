package com.irishinterest.irishinterest.webservice

data class Category(
    val id: Int,
    val name: String
) {
    val displayName: String
        get() = name.trim()
            .replace("^[\n\r]", "")
            .replace("[\n\r]$", "")
}
