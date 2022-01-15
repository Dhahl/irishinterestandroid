package com.irishinterest.irishinterest.webservice

import java.net.URL
import java.net.URLEncoder
import java.util.*

data class BookDetails(val authorid: Int,
                       val categoryid: Int,
                       val ebook: Int,
                       val firstname: String,
                       val genre: String,
                       val hardback: Int,
                       val id: Int,
                       val isbn: String?,
                       val isbn13: String,
                       val language: String,
                       val lastname: String,
                       val pages: Int?,
                       val paperback: Int,
                       val publisher: String,
                       val synopsis: String,
                       val title: String,
                       val vendor: String?,
                       val vendorurl: String?,
                       val published: String?
) {
    val author: String
        get() = arrayOf<String?>(firstname, lastname).mapNotNull { it }.joinToString(" ")

    val isbnToDisplay: String
        get() {
            if(isbn13.isEmpty()) {
                isbn?.let { return it }
                return ""
            } else {
                return isbn13
            }
        }

    val synopsisToDisplay: String
        get() = synopsis
            .replace("\r\n\r\n", "\r\n")
            .replace("\r\n\r\n", "\r\n")
            .replace("\n\n", "\n")
            .replace("<br>", "\n")
            .trim("'".single())

    val textToShare: String
        get() = author + " - " + title

    val linkToShare: URL
        get() {
            val titleEncoded: String = URLEncoder.encode(title, "utf-8")
            return URL("https://www.irishinterest.ie/book/?id=${id}&t=${titleEncoded}")
        }

    val twitterURL: URL
        get() = URL("https://twitter.com/Irish1nterest?ref_src=twsrc%5Etfw%7Ctwcamp%5Eembeddedtimeline%7Ctwterm%5Eprofile%3AIrish1nterest%7Ctwgr%5EeyJ0ZndfZXhwZXJpbWVudHNfY29va2llX2V4cGlyYXRpb24iOnsiYnVja2V0IjoxMjA5NjAwLCJ2ZXJzaW9uIjpudWxsfSwidGZ3X2hvcml6b25fdHdlZXRfZW1iZWRfOTU1NSI6eyJidWNrZXQiOiJodGUiLCJ2ZXJzaW9uIjpudWxsfSwidGZ3X3NwYWNlX2NhcmQiOnsiYnVja2V0Ijoib2ZmIiwidmVyc2lvbiI6bnVsbH19&ref_url=https%3A%2F%2Firishinterest.ie%2F%23searchresults")

    val facebookURL: URL
        get() = URL("https://www.facebook.com/sharer/sharer.php?u=irishinterest.ie/book/?id=${id}")

    val youtubeURL: URL
        get() = URL("https://www.youtube.com/channel/UCBVh-eIxXZEfh_BK9r8wwdQ")


    companion object {
        @JvmStatic
        fun empty(): BookDetails {
            return BookDetails(authorid= -1, categoryid= 0, ebook= 0, firstname= "", genre= "", hardback= 0, id= -1, isbn= null, isbn13= "", language= "", lastname= "", pages= 0, paperback= 0, publisher= "", synopsis= "", title= "", vendor= null, vendorurl= null, published= null)
        }
    }
}
