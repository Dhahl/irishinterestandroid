package com.irishinterest.irishinterest.webservice

import retrofit2.http.GET
import io.reactivex.Flowable
import com.irishinterest.irishinterest.webservice.CountByLetter
import com.irishinterest.irishinterest.webservice.AuthorDetails
import com.irishinterest.irishinterest.webservice.BookDetails
import retrofit2.http.Query

interface WebService {
    // AUTHORS
    @GET("request.php?apiKey=testApiKey&value=authors&type=getAll&offset=0")
    fun authors(): Flowable<List<Author>>

    @GET("request.php?apiKey=testApiKey&value=authors&type=searchByName")
    fun authorsSearchByName(
        @Query("searchBy") name: String?
    ): Flowable<List<Author>>

    @GET("request.php?apiKey=testApiKey&value=authors&type=byLastNameStartsWith")
    fun authorsByLetter(
        @Query("startsWith") letter: String?
    ): Flowable<List<Author>>

    @GET("request.php?apiKey=testApiKey&value=authors&type=count")
    fun authorsCount(): Flowable<Int>

    @GET("request.php?apiKey=testApiKey&value=authors&type=abcCount")
    fun authorsAtoZCount(): Flowable<List<CountByLetter>>

    @GET("request.php?apiKey=testApiKey&value=authors&type=detailsById")
    fun authorDetails(
        @Query("authorId") authorId: Int?
    ): Flowable<AuthorDetails>

    @GET("request.php?apiKey=testApiKey&value=authors&type=detailsById")
    fun authorsByBookIds(
        @Query("ids") bookIds: String?
    ): Flowable<Map<String, List<Author>>>

    // CATEGORIES
    @GET("request.php?apiKey=testApiKey&value=categories")
    fun categories(): Flowable<List<Category>>

    // BOOKS
    @GET("request.php?apiKey=testApiKey&value=books&type=searchByName")
    fun booksSearchByName(
        @Query("searchBy") name: String?
    ): Flowable<List<Book>>

    @GET("request.php?apiKey=testApiKey&value=books&type=byAuthorID")
    fun booksByAuthorID(
        @Query("authorID") authorID: Int?,
        @Query("offset") offset: Int?
    ): Flowable<List<Book>>

    @GET("request.php?apiKey=testApiKey&value=books&type=byCategory")
    fun booksByCategoryId(
        @Query("authorID") categoryId: Int?,
        @Query("offset") offset: Int?
    ): Flowable<List<Book>>

    @GET("request.php?apiKey=testApiKey&value=books&type=getLatest2")
    fun booksLatest(
        @Query("offset") offset: Int?
    ): Flowable<List<Book>>

    @GET("request.php?apiKey=testApiKey&value=books&type=getPublished")
    fun booksPublished(
        @Query("offset") offset: Int?
    ): Flowable<List<Book>>

    @GET("request.php?apiKey=testApiKey&value=books&type=getComingSoon")
    fun booksComingSoon(
        @Query("offset") offset: Int?
    ): Flowable<List<Book>>

    // BOOK DETAILS
    @GET("request.php?apiKey=testApiKey&value=books&type=getById2")
    fun bookDetails(
        @Query("bookId") bookID: Int?
    ): Flowable<BookDetails>
}