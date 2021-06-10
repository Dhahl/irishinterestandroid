package com.irishinterest.irishinterest.model.user;

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
        "action",
        "apiKey",
        "token",
        "isTest",
        "enc"
})
public class ContactUsMessage {

    @JsonProperty("action")
    private String action;
    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("token")
    private String token;
    @JsonProperty("isTest")
    private Boolean isTest;
    @JsonProperty("enc")
    private ContactUsMessageEncrypted enc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("apiKey")
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty("apiKey")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("isTest")
    public Boolean getIsTest() {
        return isTest;
    }

    @JsonProperty("isTest")
    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }

    @JsonProperty("enc")
    public ContactUsMessageEncrypted getEnc() {
        return enc;
    }

    @JsonProperty("enc")
    public void setEnc(ContactUsMessageEncrypted enc) {
        this.enc = enc;
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