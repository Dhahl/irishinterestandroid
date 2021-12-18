package com.irishinterest.irishinterest.model.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data
import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "id",
    "login",
    "pw",
    "real_name",
    "extra_info",
    "email",
    "tmp_mail",
    "access_level",
    "active",
    "publisher",
    "lastname",
    "telephone",
    "position"
)
class UserValues : Data() {
    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: Int? = null

    @get:JsonProperty("login")
    @set:JsonProperty("login")
    @JsonProperty("login")
    var login: String? = null

    @get:JsonProperty("pw")
    @set:JsonProperty("pw")
    @JsonProperty("pw")
    var pw: String? = null

    @get:JsonProperty("real_name")
    @set:JsonProperty("real_name")
    @JsonProperty("real_name")
    var realName: String? = null

    @get:JsonProperty("extra_info")
    @set:JsonProperty("extra_info")
    @JsonProperty("extra_info")
    var extraInfo: String? = null

    @get:JsonProperty("email")
    @set:JsonProperty("email")
    @JsonProperty("email")
    var email: String? = null

    @get:JsonProperty("tmp_mail")
    @set:JsonProperty("tmp_mail")
    @JsonProperty("tmp_mail")
    var tmpMail: String? = null

    @get:JsonProperty("access_level")
    @set:JsonProperty("access_level")
    @JsonProperty("access_level")
    var accessLevel: Int? = null

    @get:JsonProperty("active")
    @set:JsonProperty("active")
    @JsonProperty("active")
    var active: String? = null

    @get:JsonProperty("publisher")
    @set:JsonProperty("publisher")
    @JsonProperty("publisher")
    var publisher: String? = null

    @get:JsonProperty("lastname")
    @set:JsonProperty("lastname")
    @JsonProperty("lastname")
    var lastname: String? = null

    @get:JsonProperty("telephone")
    @set:JsonProperty("telephone")
    @JsonProperty("telephone")
    var telephone: String? = null

    @get:JsonProperty("position")
    @set:JsonProperty("position")
    @JsonProperty("position")
    var position: String? = null

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