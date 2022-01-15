package com.irishinterest.irishinterest.webservice;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {


    // AUTHORS

    @GET("?value=authors&type=getAll&offset=0")
    Flowable<List<Author>> authors();

    @GET("?value=authors&type=searchByName")
    Flowable<List<Author>> authorsSearchByName(
            @Query("searchBy") String name
    );

    @GET("?value=authors&type=byLastNameStartsWith")
    Flowable<List<Author>> authorsByLetter(
            @Query("startsWith") String letter
    );

    @GET("?value=authors&type=count")
    Flowable<Integer> authorsCount();

    @GET("?value=authors&type=abcCount")
    Flowable<List<CountByLetter>> authorsAtoZCount();

    @GET("?value=authors&type=detailsById")
    Flowable<AuthorDetails> authorDetails(
            @Query("authorId") Integer authorId
    );

    @GET("?value=authors&type=detailsById")
    Flowable<Map<String, List<Author>>> authorsByBookIds(
            @Query("ids") String bookIds
    );


    // CATEGORIES

    @GET("?value=categories")
    Flowable<List<Category>> categories();


    // BOOKS

    @GET("?value=books&type=searchByName")
    Flowable<List<Book>> booksSearchByName(
            @Query("searchBy") String name
    );

    @GET("?value=books&type=byAuthorID")
    Flowable<List<Book>> booksByAuthorID(
            @Query("authorID") Integer authorID,
            @Query("offset") Integer offset
    );

    @GET("?value=books&type=byCategory")
    Flowable<List<Book>> booksByCategoryId(
            @Query("authorID") Integer categoryId,
            @Query("offset") Integer offset
    );

    @GET("?value=books&type=getLatest2")
    Flowable<List<Book>> booksLatest(
            @Query("offset") Integer offset
    );

    @GET("?value=books&type=getPublished")
    Flowable<List<Book>> booksPublished(
            @Query("offset") Integer offset
    );

    @GET("?value=books&type=getComingSoon")
    Flowable<List<Book>> booksComingSoon(
            @Query("offset") Integer offset
    );


    // BOOK DETAILS

    @GET("?value=books&type=getById2")
    Flowable<BookDetails> bookDetails(
            @Query("bookId") Integer bookID
    );
}
