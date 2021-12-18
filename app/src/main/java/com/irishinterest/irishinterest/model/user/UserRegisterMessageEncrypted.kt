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
    "firstname",
    "lastname",
    "publishername",
    "telephone",
    "position",
    "email",
    "password",
    "confirm",
    "usertype"
)
class UserRegisterMessageEncrypted {
    @get:JsonProperty("firstname")
    @set:JsonProperty("firstname")
    @JsonProperty("firstname")
    var firstname: String? = null

    @get:JsonProperty("lastname")
    @set:JsonProperty("lastname")
    @JsonProperty("lastname")
    var lastname: String? = null

    @get:JsonProperty("publishername")
    @set:JsonProperty("publishername")
    @JsonProperty("publishername")
    var publishername: String? = null

    @get:JsonProperty("telephone")
    @set:JsonProperty("telephone")
    @JsonProperty("telephone")
    var telephone: String? = null

    @get:JsonProperty("position")
    @set:JsonProperty("position")
    @JsonProperty("position")
    var position: String? = null

    @get:JsonProperty("email")
    @set:JsonProperty("email")
    @JsonProperty("email")
    var email: String? = null

    @get:JsonProperty("password")
    @set:JsonProperty("password")
    @JsonProperty("password")
    var password: String? = null

    @get:JsonProperty("confirm")
    @set:JsonProperty("confirm")
    @JsonProperty("confirm")
    var confirm: String? = null

    @get:JsonProperty("usertype")
    @set:JsonProperty("usertype")
    @JsonProperty("usertype")
    var usertype: String? = null

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