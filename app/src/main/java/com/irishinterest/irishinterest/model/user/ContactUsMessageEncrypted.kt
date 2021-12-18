package com.irishinterest.irishinterest.model.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("contactName", "contactEmail", "contactMessage")
class ContactUsMessageEncrypted {
    @get:JsonProperty("contactName")
    @set:JsonProperty("contactName")
    @JsonProperty("contactName")
    var contactName: String? = null

    @get:JsonProperty("contactEmail")
    @set:JsonProperty("contactEmail")
    @JsonProperty("contactEmail")
    var contactEmail: String? = null

    @get:JsonProperty("contactMessage")
    @set:JsonProperty("contactMessage")
    @JsonProperty("contactMessage")
    var contactMessage: String? = null

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