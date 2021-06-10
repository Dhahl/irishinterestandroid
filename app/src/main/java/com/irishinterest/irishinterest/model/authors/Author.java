package com.irishinterest.irishinterest.model.authors;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.irishinterest.irishinterest.network.api.helper.Response;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
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
})
public class Author extends Response {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("profile")
    private String profile;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("address")
    private String address;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private Object image;
    @JsonProperty("createdby")
    private String createdby;
    @JsonProperty("altlink")
    private String altlink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("dob")
    public String getDob() {
        return dob;
    }

    @JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

    @JsonProperty("profile")
    public String getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @JsonProperty("contact")
    public String getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(String contact) {
        this.contact = contact;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("image")
    public Object getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Object image) {
        this.image = image;
    }

    @JsonProperty("createdby")
    public String getCreatedby() {
        return createdby;
    }

    @JsonProperty("createdby")
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @JsonProperty("altlink")
    public String getAltlink() {
        return altlink;
    }

    @JsonProperty("altlink")
    public void setAltlink(String altlink) {
        this.altlink = altlink;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(firstname, author.firstname) &&
                Objects.equals(lastname, author.lastname) &&
                Objects.equals(dob, author.dob) &&
                Objects.equals(profile, author.profile) &&
                Objects.equals(contact, author.contact) &&
                Objects.equals(address, author.address) &&
                Objects.equals(url, author.url) &&
                Objects.equals(image, author.image) &&
                Objects.equals(createdby, author.createdby) &&
                Objects.equals(altlink, author.altlink) &&
                Objects.equals(additionalProperties, author.additionalProperties);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, dob, profile, contact, address, url, image, createdby, altlink, additionalProperties);
    }
}
