package com.irishinterest.irishinterest.model.books

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import androidx.annotation.RequiresApi
import android.os.Build
import com.irishinterest.irishinterest.network.api.helper.Response
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "title",
    "author",
    "authorid",
    "publisher",
    "publisherurl",
    "genre",
    "categoryid",
    "area",
    "synopsis",
    "id",
    "lastupdated",
    "published",
    "image",
    "isbn",
    "linktext",
    "linkurl",
    "vendor",
    "vendorurl",
    "hardback",
    "paperback",
    "ebook",
    "audio",
    "pages",
    "isbn13",
    "asin",
    "language",
    "user_id",
    "updated_by",
    "size",
    "editorschoice",
    "narrative",
    "UK_ASIN",
    "US_ASIN"
)
class Book : Response() {
    @get:JsonProperty("title")
    @set:JsonProperty("title")
    @JsonProperty("title")
    var title: String? = null

    @get:JsonProperty("author")
    @set:JsonProperty("author")
    @JsonProperty("author")
    var author: String? = null

    @get:JsonProperty("authorid")
    @set:JsonProperty("authorid")
    @JsonProperty("authorid")
    var authorid: Int? = null

    @get:JsonProperty("publisher")
    @set:JsonProperty("publisher")
    @JsonProperty("publisher")
    var publisher: String? = null

    @get:JsonProperty("publisherurl")
    @set:JsonProperty("publisherurl")
    @JsonProperty("publisherurl")
    var publisherurl: String? = null

    @get:JsonProperty("genre")
    @set:JsonProperty("genre")
    @JsonProperty("genre")
    var genre: String? = null

    @get:JsonProperty("categoryid")
    @set:JsonProperty("categoryid")
    @JsonProperty("categoryid")
    var categoryid: Int? = null

    @get:JsonProperty("area")
    @set:JsonProperty("area")
    @JsonProperty("area")
    var area: String? = null

    @get:JsonProperty("synopsis")
    @set:JsonProperty("synopsis")
    @JsonProperty("synopsis")
    var synopsis: String? = null

    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: Int? = null

    @get:JsonProperty("lastupdated")
    @set:JsonProperty("lastupdated")
    @JsonProperty("lastupdated")
    var lastupdated: String? = null

    @get:JsonProperty("published")
    @set:JsonProperty("published")
    @JsonProperty("published")
    var published: String? = null

    @get:JsonProperty("image")
    @set:JsonProperty("image")
    @JsonProperty("image")
    var image: String? = null

    @get:JsonProperty("isbn")
    @set:JsonProperty("isbn")
    @JsonProperty("isbn")
    var isbn: String? = null

    @get:JsonProperty("linktext")
    @set:JsonProperty("linktext")
    @JsonProperty("linktext")
    var linktext: String? = null

    @get:JsonProperty("linkurl")
    @set:JsonProperty("linkurl")
    @JsonProperty("linkurl")
    var linkurl: String? = null

    @get:JsonProperty("vendor")
    @set:JsonProperty("vendor")
    @JsonProperty("vendor")
    var vendor: String? = null

    @get:JsonProperty("vendorurl")
    @set:JsonProperty("vendorurl")
    @JsonProperty("vendorurl")
    var vendorurl: String? = null

    @get:JsonProperty("hardback")
    @set:JsonProperty("hardback")
    @JsonProperty("hardback")
    var hardback: Int? = null

    @get:JsonProperty("paperback")
    @set:JsonProperty("paperback")
    @JsonProperty("paperback")
    var paperback: Int? = null

    @get:JsonProperty("ebook")
    @set:JsonProperty("ebook")
    @JsonProperty("ebook")
    var ebook: Int? = null

    @get:JsonProperty("audio")
    @set:JsonProperty("audio")
    @JsonProperty("audio")
    var audio: Int? = null

    @get:JsonProperty("pages")
    @set:JsonProperty("pages")
    @JsonProperty("pages")
    var pages: Int? = null

    @get:JsonProperty("isbn13")
    @set:JsonProperty("isbn13")
    @JsonProperty("isbn13")
    var isbn13: String? = null

    @get:JsonProperty("asin")
    @set:JsonProperty("asin")
    @JsonProperty("asin")
    var asin: Any? = null

    @get:JsonProperty("language")
    @set:JsonProperty("language")
    @JsonProperty("language")
    var language: String? = null

    @get:JsonProperty("user_id")
    @set:JsonProperty("user_id")
    @JsonProperty("user_id")
    var userId: String? = null

    @get:JsonProperty("updated_by")
    @set:JsonProperty("updated_by")
    @JsonProperty("updated_by")
    var updatedBy: String? = null

    @get:JsonProperty("size")
    @set:JsonProperty("size")
    @JsonProperty("size")
    var size: Any? = null

    @get:JsonProperty("editorschoice")
    @set:JsonProperty("editorschoice")
    @JsonProperty("editorschoice")
    var editorschoice: String? = null

    @get:JsonProperty("narrative")
    @set:JsonProperty("narrative")
    @JsonProperty("narrative")
    var narrative: String? = null

    @get:JsonProperty("UK_ASIN")
    @set:JsonProperty("UK_ASIN")
    @JsonProperty("UK_ASIN")
    var ukAsin: String? = null

    @get:JsonProperty("US_ASIN")
    @set:JsonProperty("US_ASIN")
    @JsonProperty("US_ASIN")
    var usAsin: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false
        val book = other
        return title == book.title &&
                author == book.author &&
                authorid == book.authorid &&
                publisher == book.publisher &&
                publisherurl == book.publisherurl &&
                genre == book.genre &&
                categoryid == book.categoryid &&
                area == book.area &&
                synopsis == book.synopsis &&
                id == book.id &&
                lastupdated == book.lastupdated &&
                published == book.published &&
                image == book.image &&
                isbn == book.isbn &&
                linktext == book.linktext &&
                linkurl == book.linkurl &&
                vendor == book.vendor &&
                vendorurl == book.vendorurl &&
                hardback == book.hardback &&
                paperback == book.paperback &&
                ebook == book.ebook &&
                audio == book.audio &&
                pages == book.pages &&
                isbn13 == book.isbn13 &&
                asin == book.asin &&
                language == book.language &&
                userId == book.userId &&
                updatedBy == book.updatedBy &&
                size == book.size &&
                editorschoice == book.editorschoice &&
                narrative == book.narrative &&
                additionalProperties == book.additionalProperties
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(
            title,
            author,
            authorid,
            publisher,
            publisherurl,
            genre,
            categoryid,
            area,
            synopsis,
            id,
            lastupdated,
            published,
            image,
            isbn,
            linktext,
            linkurl,
            vendor,
            vendorurl,
            hardback,
            paperback,
            ebook,
            audio,
            pages,
            isbn13,
            asin,
            language,
            userId,
            updatedBy,
            size,
            editorschoice,
            narrative,
            additionalProperties
        )
    }
}