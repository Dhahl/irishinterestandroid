package com.irishinterest.irishinterest.webservice;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {


    // AUTHORS

    @GET("request.php?apiKey=testApiKey?value=authors&type=getAll&offset=0")
    Flowable<List<Author>> authors(

    );

    @GET("request.php?apiKey=testApiKey?value=authors&type=searchByName")
    Flowable<List<Author>> authorsSearchByName(
            @Query("searchBy") String name
    );

    @GET("request.php?apiKey=testApiKey?value=authors&type=byLastNameStartsWith")
    Flowable<List<Author>> authorsByLetter(
            @Query("startsWith") String letter
    );

    @GET("request.php?apiKey=testApiKey?value=authors&type=count")
    Flowable<Integer> authorsCount();

    @GET("request.php?apiKey=testApiKey?value=authors&type=abcCount")
    Flowable<List<CountByLetter>> authorsAtoZCount();

    @GET("request.php?apiKey=testApiKey?value=authors&type=detailsById")
    Flowable<AuthorDetails> authorDetails(
            @Query("authorId") Integer authorId
    );

    @GET("request.php?apiKey=testApiKey?value=authors&type=detailsById")
    Flowable<Map<String, List<Author>>> authorsByBookIds(
            @Query("ids") String bookIds
    );


    // CATEGORIES

    @GET("request.php?apiKey=testApiKey?value=categories")
    Flowable<List<Category>> categories();


    // BOOKS

    @GET("request.php?apiKey=testApiKey?value=books&type=searchByName")
    Flowable<List<Book>> booksSearchByName(
            @Query("searchBy") String name
    );

    @GET("request.php?apiKey=testApiKey?value=books&type=byAuthorID")
    Flowable<List<Book>> booksByAuthorID(
            @Query("authorID") Integer authorID,
            @Query("offset") Integer offset
    );

    @GET("request.php?apiKey=testApiKey?value=books&type=byCategory")
    Flowable<List<Book>> booksByCategoryId(
            @Query("authorID") Integer categoryId,
            @Query("offset") Integer offset
    );

    @GET("request.php?apiKey=testApiKey?value=books&type=getLatest2")
    Flowable<List<Book>> booksLatest(
            @Query("offset") Integer offset
    );

    @GET("request.php?apiKey=testApiKey?value=books&type=getPublished")
    Flowable<List<Book>> booksPublished(
            @Query("offset") Integer offset
    );

    @GET("request.php?apiKey=testApiKey?value=books&type=getComingSoon")
    Flowable<List<Book>> booksComingSoon(
            @Query("offset") Integer offset
    );


    // BOOK DETAILS

    @GET("request.php?apiKey=testApiKey?value=books&type=getById2")
    Flowable<BookDetails> bookDetails(
            @Query("bookId") Integer bookID
    );
}
