package com.irishinterest.irishinterest.model.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "bookid",
    "userid",
    "date",
    "rating",
    "recommend",
    "status",
    "reviewText",
    "authorname"
)
class ReviewsObj {
    @get:JsonProperty("bookid")
    @set:JsonProperty("bookid")
    @JsonProperty("bookid")
    var bookid: Int? = null

    @get:JsonProperty("userid")
    @set:JsonProperty("userid")
    @JsonProperty("userid")
    var userid: Int? = null

    @get:JsonProperty("date")
    @set:JsonProperty("date")
    @JsonProperty("date")
    var date: String? = null

    @get:JsonProperty("rating")
    @set:JsonProperty("rating")
    @JsonProperty("rating")
    var rating: Int? = null

    @get:JsonProperty("recommend")
    @set:JsonProperty("recommend")
    @JsonProperty("recommend")
    var recommend: Int? = null

    @get:JsonProperty("status")
    @set:JsonProperty("status")
    @JsonProperty("status")
    var status: String? = null

    @get:JsonProperty("reviewText")
    @set:JsonProperty("reviewText")
    @JsonProperty("reviewText")
    var reviewText: String? = null

    @get:JsonProperty("authorname")
    @set:JsonProperty("authorname")
    @JsonProperty("authorname")
    var authorName: String? = null

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
}