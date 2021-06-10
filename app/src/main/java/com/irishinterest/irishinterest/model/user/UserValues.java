package com.irishinterest.irishinterest.model.user;

import com.irishinterest.irishinterest.network.api.irishInterest.observer.Data;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
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
})
public class UserValues extends Data {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("pw")
    private String pw;
    @JsonProperty("real_name")
    private String realName;
    @JsonProperty("extra_info")
    private String extraInfo;
    @JsonProperty("email")
    private String email;
    @JsonProperty("tmp_mail")
    private String tmpMail;
    @JsonProperty("access_level")
    private Integer accessLevel;
    @JsonProperty("active")
    private String active;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("position")
    private String position;
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

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("pw")
    public String getPw() {
        return pw;
    }

    @JsonProperty("pw")
    public void setPw(String pw) {
        this.pw = pw;
    }

    @JsonProperty("real_name")
    public String getRealName() {
        return realName;
    }

    @JsonProperty("real_name")
    public void setRealName(String realName) {
        this.realName = realName;
    }

    @JsonProperty("extra_info")
    public String getExtraInfo() {
        return extraInfo;
    }

    @JsonProperty("extra_info")
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("tmp_mail")
    public String getTmpMail() {
        return tmpMail;
    }

    @JsonProperty("tmp_mail")
    public void setTmpMail(String tmpMail) {
        this.tmpMail = tmpMail;
    }

    @JsonProperty("access_level")
    public Integer getAccessLevel() {
        return accessLevel;
    }

    @JsonProperty("access_level")
    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    @JsonProperty("active")
    public String getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(String active) {
        this.active = active;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("telephone")
    public String getTelephone() {
        return telephone;
    }

    @JsonProperty("telephone")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
