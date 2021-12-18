package com.irishinterest.irishinterest.model.authors

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
    "id",
    "firstname",
    "lastname",
    "dob",
    "profile",
    "contact",
    "address",
    "url",
    "image",
    "createdby",
    "altlink"
)
class Author : Response() {
    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: Int? = null

    @get:JsonProperty("firstname")
    @set:JsonProperty("firstname")
    @JsonProperty("firstname")
    var firstname: String? = null

    @get:JsonProperty("lastname")
    @set:JsonProperty("lastname")
    @JsonProperty("lastname")
    var lastname: String? = null

    @get:JsonProperty("dob")
    @set:JsonProperty("dob")
    @JsonProperty("dob")
    var dob: String? = null

    @get:JsonProperty("profile")
    @set:JsonProperty("profile")
    @JsonProperty("profile")
    var profile: String? = null

    @get:JsonProperty("contact")
    @set:JsonProperty("contact")
    @JsonProperty("contact")
    var contact: String? = null

    @get:JsonProperty("address")
    @set:JsonProperty("address")
    @JsonProperty("address")
    var address: String? = null

    @get:JsonProperty("url")
    @set:JsonProperty("url")
    @JsonProperty("url")
    var url: String? = null

    @get:JsonProperty("image")
    @set:JsonProperty("image")
    @JsonProperty("image")
    var image: Any? = null

    @get:JsonProperty("createdby")
    @set:JsonProperty("createdby")
    @JsonProperty("createdby")
    var createdby: String? = null

    @get:JsonProperty("altlink")
    @set:JsonProperty("altlink")
    @JsonProperty("altlink")
    var altlink: String? = null

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
        if (other !is Author) return false
        val author = other
        return id == author.id &&
                firstname == author.firstname &&
                lastname == author.lastname &&
                dob == author.dob &&
                profile == author.profile &&
                contact == author.contact &&
                address == author.address &&
                url == author.url &&
                image == author.image &&
                createdby == author.createdby &&
                altlink == author.altlink &&
                additionalProperties == author.additionalProperties
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(
            id,
            firstname,
            lastname,
            dob,
            profile,
            contact,
            address,
            url,
            image,
            createdby,
            altlink,
            additionalProperties
        )
    }
}