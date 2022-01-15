package com.irishinterest.irishinterest.webservice

import java.net.URL

data class Book(val author: String?,
                val authorid: Int,
                val id: Int,
                val image: String,
                val title: String,
                val displayTitle: String = title.trim("'".single())
                    .replace("<br>", " ")
) {
    val imageURL: URL?
        get() {
            if (image.isEmpty()) { return null }
            return URL("https://irishinterest.ie/upload/${image}")
        }
}
