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
        "bookid",
        "userid",
        "date",
        "rating",
        "recommend",
        "status",
        "reviewText",
        "authorname"
})
public class ReviewsObj {

    @JsonProperty("bookid")
    private Integer bookid;
    @JsonProperty("userid")
    private Integer userid;
    @JsonProperty("date")
    private String date;
    @JsonProperty("rating")
    private Integer rating;
    @JsonProperty("recommend")
    private Integer recommend;
    @JsonProperty("status")
    private String status;
    @JsonProperty("reviewText")
    private String reviewText;
    @JsonProperty("authorname")
    private String authorName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bookid")
    public Integer getBookid() {
        return bookid;
    }

    @JsonProperty("bookid")
    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @JsonProperty("userid")
    public Integer getUserid() {
        return userid;
    }

    @JsonProperty("userid")
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("rating")
    public Integer getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @JsonProperty("recommend")
    public Integer getRecommend() {
        return recommend;
    }

    @JsonProperty("recommend")
    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("reviewText")
    public String getReviewText() {
        return reviewText;
    }

    @JsonProperty("reviewText")
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @JsonProperty("authorname")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("authorname")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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