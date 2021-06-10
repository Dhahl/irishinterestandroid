package com.irishinterest.irishinterest.model.misc;

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
        "NumberOfAllBooks"
})
public class BooksNumberObj {
    @JsonProperty("NumberOfAllBooks")
    private Integer numberOfAllBooks;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NumberOfAllBooks")
    public Integer getNumberOfAllBooks() {
        return numberOfAllBooks;
    }

    @JsonProperty("NumberOfAllBooks")
    public void setNumberOfAllBooks(Integer numberOfAllBooks) {
        this.numberOfAllBooks = numberOfAllBooks;
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
