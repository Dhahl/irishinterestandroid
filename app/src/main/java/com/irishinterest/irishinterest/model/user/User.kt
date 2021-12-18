package com.irishinterest.irishinterest.model.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("email", "password", "timestamp")
class User {
    @get:JsonProperty("email")
    @set:JsonProperty("email")
    @JsonProperty("email")
    var email: String? = null

    @get:JsonProperty("password")
    @set:JsonProperty("password")
    @JsonProperty("password")
    var password: String? = null

    @get:JsonProperty("timestamp")
    @set:JsonProperty("timestamp")
    @JsonProperty("timestamp")
    var timestamp: Long? = null

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