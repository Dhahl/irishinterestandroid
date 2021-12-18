package com.irishinterest.irishinterest.model.misc

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.irishinterest.irishinterest.model.misc.BooksNumberObj
import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("response", "token")
class GetAllBooksResponse {
    @get:JsonProperty("response")
    @set:JsonProperty("response")
    @JsonProperty("response")
    var response: List<BooksNumberObj>? = null

    @get:JsonProperty("token")
    @set:JsonProperty("token")
    @JsonProperty("token")
    var token: String? = null

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