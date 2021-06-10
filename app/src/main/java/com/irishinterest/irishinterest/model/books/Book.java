package com.irishinterest.irishinterest.model.books;

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
        "title",
        "author",
        "authorid",
        "publisher",
        "publisherurl",
        "genre",
        "categoryid",
        "area",
        "synopsis",
        "id",
        "lastupdated",
        "published",
        "image",
        "isbn",
        "linktext",
        "linkurl",
        "vendor",
        "vendorurl",
        "hardback",
        "paperback",
        "ebook",
        "audio",
        "pages",
        "isbn13",
        "asin",
        "language",
        "user_id",
        "updated_by",
        "size",
        "editorschoice",
        "narrative",
        "UK_ASIN",
        "US_ASIN"
})
public class Book extends Response {
        @JsonProperty("title")
        private String title;
        @JsonProperty("author")
        private String author;
        @JsonProperty("authorid")
        private Integer authorid;
        @JsonProperty("publisher")
        private String publisher;
        @JsonProperty("publisherurl")
        private String publisherurl;
        @JsonProperty("genre")
        private String genre;
        @JsonProperty("categoryid")
        private Integer categoryid;
        @JsonProperty("area")
        private String area;
        @JsonProperty("synopsis")
        private String synopsis;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("lastupdated")
        private String lastupdated;
        @JsonProperty("published")
        private String published;
        @JsonProperty("image")
        private String image;
        @JsonProperty("isbn")
        private String isbn;
        @JsonProperty("linktext")
        private String linktext;
        @JsonProperty("linkurl")
        private String linkurl;
        @JsonProperty("vendor")
        private String vendor;
        @JsonProperty("vendorurl")
        private String vendorurl;
        @JsonProperty("hardback")
        private Integer hardback;
        @JsonProperty("paperback")
        private Integer paperback;
        @JsonProperty("ebook")
        private Integer ebook;
        @JsonProperty("audio")
        private Integer audio;
        @JsonProperty("pages")
        private Integer pages;
        @JsonProperty("isbn13")
        private String isbn13;
        @JsonProperty("asin")
        private Object asin;
        @JsonProperty("language")
        private String language;
        @JsonProperty("user_id")
        private String userId;
        @JsonProperty("updated_by")
        private String updatedBy;
        @JsonProperty("size")
        private Object size;
        @JsonProperty("editorschoice")
        private String editorschoice;
        @JsonProperty("narrative")
        private String narrative;
        @JsonProperty("UK_ASIN")
        private String ukAsin;
        @JsonProperty("US_ASIN")
        private String usAsin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("author")
        public String getAuthor() {
            return author;
        }

        @JsonProperty("author")
        public void setAuthor(String author) {
            this.author = author;
        }

        @JsonProperty("authorid")
        public Integer getAuthorid() {
            return authorid;
        }

        @JsonProperty("authorid")
        public void setAuthorid(Integer authorid) {
            this.authorid = authorid;
        }

        @JsonProperty("publisher")
        public String getPublisher() {
            return publisher;
        }

        @JsonProperty("publisher")
        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        @JsonProperty("publisherurl")
        public String getPublisherurl() {
            return publisherurl;
        }

        @JsonProperty("publisherurl")
        public void setPublisherurl(String publisherurl) {
            this.publisherurl = publisherurl;
        }

        @JsonProperty("genre")
        public String getGenre() {
            return genre;
        }

        @JsonProperty("genre")
        public void setGenre(String genre) {
            this.genre = genre;
        }

        @JsonProperty("categoryid")
        public Integer getCategoryid() {
            return categoryid;
        }

        @JsonProperty("categoryid")
        public void setCategoryid(Integer categoryid) {
            this.categoryid = categoryid;
        }

        @JsonProperty("area")
        public String getArea() {
            return area;
        }

        @JsonProperty("area")
        public void setArea(String area) {
            this.area = area;
        }

        @JsonProperty("synopsis")
        public String getSynopsis() {
            return synopsis;
        }

        @JsonProperty("synopsis")
        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("lastupdated")
        public String getLastupdated() {
            return lastupdated;
        }

        @JsonProperty("lastupdated")
        public void setLastupdated(String lastupdated) {
            this.lastupdated = lastupdated;
        }

        @JsonProperty("published")
        public String getPublished() {
            return published;
        }

        @JsonProperty("published")
        public void setPublished(String published) {
            this.published = published;
        }

        @JsonProperty("image")
        public String getImage() {
            return image;
        }

        @JsonProperty("image")
        public void setImage(String image) {
            this.image = image;
        }

        @JsonProperty("isbn")
        public String getIsbn() {
            return isbn;
        }

        @JsonProperty("UK_ASIN")
        public String getUkAsin() {
            return ukAsin;
        }

        @JsonProperty("UK_ASIN")
        public void setUkAsin(String ukAsin) {
            this.ukAsin = ukAsin;
        }

        @JsonProperty("US_ASIN")
        public String getUsAsin() {
            return usAsin;
        }

        @JsonProperty("US_ASIN")
        public void setUsAsin(String usAsin) {
            this.usAsin = usAsin;
        }

        @JsonProperty("isbn")
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        @JsonProperty("linktext")
        public String getLinktext() {
            return linktext;
        }

        @JsonProperty("linktext")
        public void setLinktext(String linktext) {
            this.linktext = linktext;
        }

        @JsonProperty("linkurl")
        public String getLinkurl() {
            return linkurl;
        }

        @JsonProperty("linkurl")
        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        @JsonProperty("vendor")
        public String getVendor() {
            return vendor;
        }

        @JsonProperty("vendor")
        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        @JsonProperty("vendorurl")
        public String getVendorurl() {
            return vendorurl;
        }

        @JsonProperty("vendorurl")
        public void setVendorurl(String vendorurl) {
            this.vendorurl = vendorurl;
        }

        @JsonProperty("hardback")
        public Integer getHardback() {
            return hardback;
        }

        @JsonProperty("hardback")
        public void setHardback(Integer hardback) {
            this.hardback = hardback;
        }

        @JsonProperty("paperback")
        public Integer getPaperback() {
            return paperback;
        }

        @JsonProperty("paperback")
        public void setPaperback(Integer paperback) {
            this.paperback = paperback;
        }

        @JsonProperty("ebook")
        public Integer getEbook() {
            return ebook;
        }

        @JsonProperty("ebook")
        public void setEbook(Integer ebook) {
            this.ebook = ebook;
        }

        @JsonProperty("audio")
        public Integer getAudio() {
            return audio;
        }

        @JsonProperty("audio")
        public void setAudio(Integer audio) {
            this.audio = audio;
        }

        @JsonProperty("pages")
        public Integer getPages() {
            return pages;
        }

        @JsonProperty("pages")
        public void setPages(Integer pages) {
            this.pages = pages;
        }

        @JsonProperty("isbn13")
        public String getIsbn13() {
            return isbn13;
        }

        @JsonProperty("isbn13")
        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        @JsonProperty("asin")
        public Object getAsin() {
            return asin;
        }

        @JsonProperty("asin")
        public void setAsin(Object asin) {
            this.asin = asin;
        }

        @JsonProperty("language")
        public String getLanguage() {
            return language;
        }

        @JsonProperty("language")
        public void setLanguage(String language) {
            this.language = language;
        }

        @JsonProperty("user_id")
        public String getUserId() {
            return userId;
        }

        @JsonProperty("user_id")
        public void setUserId(String userId) {
            this.userId = userId;
        }

        @JsonProperty("updated_by")
        public String getUpdatedBy() {
            return updatedBy;
        }

        @JsonProperty("updated_by")
        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        @JsonProperty("size")
        public Object getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Object size) {
            this.size = size;
        }

        @JsonProperty("editorschoice")
        public String getEditorschoice() {
            return editorschoice;
        }

        @JsonProperty("editorschoice")
        public void setEditorschoice(String editorschoice) {
            this.editorschoice = editorschoice;
        }

        @JsonProperty("narrative")
        public String getNarrative() {
            return narrative;
        }

        @JsonProperty("narrative")
        public void setNarrative(String narrative) {
            this.narrative = narrative;
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
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(authorid, book.authorid) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publisherurl, book.publisherurl) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(categoryid, book.categoryid) &&
                Objects.equals(area, book.area) &&
                Objects.equals(synopsis, book.synopsis) &&
                Objects.equals(id, book.id) &&
                Objects.equals(lastupdated, book.lastupdated) &&
                Objects.equals(published, book.published) &&
                Objects.equals(image, book.image) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(linktext, book.linktext) &&
                Objects.equals(linkurl, book.linkurl) &&
                Objects.equals(vendor, book.vendor) &&
                Objects.equals(vendorurl, book.vendorurl) &&
                Objects.equals(hardback, book.hardback) &&
                Objects.equals(paperback, book.paperback) &&
                Objects.equals(ebook, book.ebook) &&
                Objects.equals(audio, book.audio) &&
                Objects.equals(pages, book.pages) &&
                Objects.equals(isbn13, book.isbn13) &&
                Objects.equals(asin, book.asin) &&
                Objects.equals(language, book.language) &&
                Objects.equals(userId, book.userId) &&
                Objects.equals(updatedBy, book.updatedBy) &&
                Objects.equals(size, book.size) &&
                Objects.equals(editorschoice, book.editorschoice) &&
                Objects.equals(narrative, book.narrative) &&
                Objects.equals(additionalProperties, book.additionalProperties);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(title, author, authorid, publisher, publisherurl, genre, categoryid, area, synopsis, id, lastupdated, published, image, isbn, linktext, linkurl, vendor, vendorurl, hardback, paperback, ebook, audio, pages, isbn13, asin, language, userId, updatedBy, size, editorschoice, narrative, additionalProperties);
    }
}
