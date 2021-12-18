package com.irishinterest.irishinterest.model.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.irishinterest.irishinterest.model.user.UserRegisterMessageEncrypted
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("action", "apiKey", "token", "isTest", "enc")
class UserRegisterMessage {
    @get:JsonProperty("action")
    @set:JsonProperty("action")
    @JsonProperty("action")
    var action: String? = null

    @get:JsonProperty("apiKey")
    @set:JsonProperty("apiKey")
    @JsonProperty("apiKey")
    var apiKey: String? = null

    @get:JsonProperty("token")
    @set:JsonProperty("token")
    @JsonProperty("token")
    var token: String? = null

    @get:JsonProperty("isTest")
    @set:JsonProperty("isTest")
    @JsonProperty("isTest")
    var isTest: Boolean? = null

    @get:JsonProperty("enc")
    @set:JsonProperty("enc")
    @JsonProperty("enc")
    var enc: UserRegisterMessageEncrypted? = null

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