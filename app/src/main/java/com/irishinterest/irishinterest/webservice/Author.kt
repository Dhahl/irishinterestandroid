package com.irishinterest.irishinterest.webservice

import android.util.ArrayMap
import java.net.URL

data class Author(val id: Int,
                  val firstname: String,
                  val lastname: String,
                  val fullName: String = "${lastname}, ${firstname}",
                  val displayName: String = "${firstname} ${lastname}"
)

typealias AuthorsOfBooks = Map<String, List<Author>>

data class AuthorDetails(
    val dob: String? = null,
    val profile: String? = null,
    val image: String? = null,
    val altlink: URL? = null,   // eg. wikipedia link
    val firstname: String? = null,
    val lastname: String? = null,
    val url: URL? = null // eg. author's own website link
) {
    val author: String
        get() = arrayOf<String?>(firstname, lastname).mapNotNull { it }.joinToString(" ")

    val imageURL: URL?
        get() {
            image?.let {
              if(image.isEmpty()){
                  return null
              } else {
                  return URL("https://irishinterest.ie/upload/${image}")
              }
            }
            return null
        }

    /// Checks if there's anything useful we can shown as author bio details
    val isWorthToShow: Boolean
        get() {
            if(imageURL != null) { return true }
            if(profile?.isNotEmpty() == true) { return true }
            return false
        }

    companion object {
        @JvmStatic
        fun empty(): AuthorDetails {
            return AuthorDetails(dob= null,
            profile= null,
            image= null,
            altlink= null,
            firstname= null,
            lastname= null,
            url= null)
        }
    }
}


data class CountByLetter(val alpha: String, val count: Int)