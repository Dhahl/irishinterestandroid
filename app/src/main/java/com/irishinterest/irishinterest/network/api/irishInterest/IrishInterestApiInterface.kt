package com.irishinterest.irishinterest.network.api.irishInterest

import com.irishinterest.irishinterest.network.api.helper.ApiResponse
import com.irishinterest.irishinterest.model.authors.Author
import com.irishinterest.irishinterest.model.books.Book
import com.irishinterest.irishinterest.model.categories.Category
import com.irishinterest.irishinterest.model.user.UserLoginRequest
import com.irishinterest.irishinterest.model.user.DefaultServerResponse
import com.irishinterest.irishinterest.model.user.ReviewResponse
import com.irishinterest.irishinterest.model.user.UserGetFavouritesRequest
import com.irishinterest.irishinterest.model.user.UserAddRemoveFavouriteBook
import com.irishinterest.irishinterest.model.user.ContactUsMessage
import com.irishinterest.irishinterest.model.user.UserRegisterMessage
import com.irishinterest.irishinterest.model.misc.GetAllBooksResponse
import retrofit2.Call
import retrofit2.http.*

interface IrishInterestApiInterface {
    @GET("request.php")
    fun getBooks(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?
    ): Call<ApiResponse<Book?>?>?

    @GET("request.php")
    fun getBooksByAuthor(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?,
        @Query("authorId") categoryId: String?
    ): Call<ApiResponse<Book?>?>?

    @GET("request.php")
    fun getBooksByCategory(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?,
        @Query("categoryId") categoryId: String?
    ): Call<ApiResponse<Book?>?>?

    @GET("request.php")
    fun getAllCategories(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?
    ): Call<ApiResponse<Category?>?>?

    @GET("request.php")
    fun getAllAuthors(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?
    ): Call<ApiResponse<Author?>?>?

    @GET("request.php")
    fun getAuthorById(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("authorId") authorId: Int?
    ): Call<ApiResponse<Author?>?>?

    @GET("request.php")
    fun getBooksBySearch(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?,
        @Query("search") categoryId: String?
    ): Call<ApiResponse<Book?>?>?

    @GET("request.php")
    fun getBooksTopSearches(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?
    ): Call<ApiResponse<Book?>?>?

    @GET("request.php")
    fun getBooksComingSoon(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?
    ): Call<ApiResponse<Book?>?>?

    @GET("request.php")
    fun getAuthorsBySearch(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?,
        @Query("offset") offset: String?,
        @Query("query") query: String?
    ): Call<ApiResponse<Author?>?>?

    @Headers("Content-Type: application/json", "Connection: keep-alive")
    @POST("request.php")
    fun loginUser( /*@Field("action") String action, @Field("apiKey") String apiKey, @Field("token") String token,*/
        @Body body: UserLoginRequest?
    ): Call<DefaultServerResponse?>?

    @POST("request.php")
    fun logoutUser( /*@Field("action") String action, @Field("apiKey") String apiKey, @Field("token") String token,*/
        @Body body: String?
    ): Call<DefaultServerResponse?>?

    @POST("request.php")
    fun postReview( /*@Field("action") String action, @Field("apiKey") String apiKey, @Field("token") String token,*/
        @Body body: String?
    ): Call<DefaultServerResponse?>?

    @GET("request.php")
    fun getReviewsByUserId(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("userId") userId: String?,
        @Query("type") type: String?
    ): Call<DefaultServerResponse?>?

    @GET("request.php")
    fun getReviewsByBookId(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("bookId") bookId: Int?,
        @Query("type") type: String?
    ): Call<ReviewResponse?>?

    @GET("request.php")
    fun getCommentsByUserId(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("userId") userId: String?,
        @Query("type") type: String?
    ): Call<DefaultServerResponse?>?

    @GET("request.php")
    fun getCommentsByBookId(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("bookId") bookId: String?,
        @Query("type") type: String?
    ): Call<DefaultServerResponse?>?

    @POST("request.php")
    fun getFavouriteBooks(@Body body: UserGetFavouritesRequest?): Call<ApiResponse<Book?>?>?

    @POST("request.php")
    fun addFavouriteBook(@Body body: UserAddRemoveFavouriteBook?): Call<DefaultServerResponse?>?

    @POST("request.php")
    fun removeFavouriteBook(@Body body: UserAddRemoveFavouriteBook?): Call<DefaultServerResponse?>?

    @POST("request.php")
    fun contactUs(@Body contactUsMessage: ContactUsMessage?): Call<DefaultServerResponse?>?

    @POST("request.php")
    fun registerUser(@Body userRegisterMessage: UserRegisterMessage?): Call<DefaultServerResponse?>?

    @GET("request.php")
    fun getPrivacyPolicy(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?
    ): Call<DefaultServerResponse?>?

    @GET("request.php")
    fun getTermsAndConditions(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?
    ): Call<DefaultServerResponse?>?

    @GET("request.php")
    fun getNumberOfAllAvailableBooks(
        @Query("value") values: String?,
        @Query("apiKey") apiKey: String?,
        @Query("token") token: String?,
        @Query("type") type: String?
    ): Call<GetAllBooksResponse?>?
}