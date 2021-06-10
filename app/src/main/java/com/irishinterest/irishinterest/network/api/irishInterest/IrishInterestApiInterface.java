package com.irishinterest.irishinterest.network.api.irishInterest;

import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.model.categories.Category;
import com.irishinterest.irishinterest.model.misc.GetAllBooksResponse;
import com.irishinterest.irishinterest.model.user.ContactUsMessage;
import com.irishinterest.irishinterest.model.user.ReviewResponse;
import com.irishinterest.irishinterest.model.user.UserAddRemoveFavouriteBook;
import com.irishinterest.irishinterest.model.user.UserGetFavouritesRequest;
import com.irishinterest.irishinterest.model.user.UserLoginRequest;
import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.model.user.UserRegisterMessage;
import com.irishinterest.irishinterest.network.api.helper.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IrishInterestApiInterface {
    @GET("request.php")
    Call<ApiResponse<Book>> getBooks(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset);

    @GET("request.php")
    Call<ApiResponse<Book>> getBooksByAuthor(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset, @Query("authorId") String categoryId);

    @GET("request.php")
    Call<ApiResponse<Book>> getBooksByCategory(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset, @Query("categoryId") String categoryId);

    @GET("request.php")
    Call<ApiResponse<Category>> getAllCategories(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token);

    @GET("request.php")
    Call<ApiResponse<Author>> getAllAuthors(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset);

    @GET("request.php")
    Call<ApiResponse<Author>> getAuthorById(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("authorId") Integer authorId);

    @GET("request.php")
    Call<ApiResponse<Book>> getBooksBySearch(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset, @Query("search") String categoryId);

    @GET("request.php")
    Call<ApiResponse<Book>> getBooksTopSearches(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type);

    @GET("request.php")
    Call<ApiResponse<Book>> getBooksComingSoon(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset);

    @GET("request.php")
    Call<ApiResponse<Author>> getAuthorsBySearch(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type, @Query("offset") String offset, @Query("query") String query);

    @Headers({"Content-Type: application/json", "Connection: keep-alive"})
    @POST("request.php")
    Call<DefaultServerResponse> loginUser(/*@Field("action") String action, @Field("apiKey") String apiKey, @Field("token") String token,*/ @Body UserLoginRequest body);

    @POST("request.php")
    Call<DefaultServerResponse> logoutUser(/*@Field("action") String action, @Field("apiKey") String apiKey, @Field("token") String token,*/ @Body() String body);

    @POST("request.php")
    Call<DefaultServerResponse> postReview(/*@Field("action") String action, @Field("apiKey") String apiKey, @Field("token") String token,*/ @Body() String body);

    @GET("request.php")
    Call<DefaultServerResponse> getReviewsByUserId(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token,  @Query("userId") String userId,  @Query("type") String type);

    @GET("request.php")
    Call<ReviewResponse> getReviewsByBookId(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("bookId") Integer bookId, @Query("type") String type);

    @GET("request.php")
    Call<DefaultServerResponse> getCommentsByUserId(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token,  @Query("userId") String userId,  @Query("type") String type);

    @GET("request.php")
    Call<DefaultServerResponse> getCommentsByBookId(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token,  @Query("bookId") String bookId,  @Query("type") String type);

    @POST("request.php")
    Call<ApiResponse<Book>> getFavouriteBooks(@Body UserGetFavouritesRequest body);

    @POST("request.php")
    Call<DefaultServerResponse> addFavouriteBook(@Body UserAddRemoveFavouriteBook body);

    @POST("request.php")
    Call<DefaultServerResponse> removeFavouriteBook(@Body UserAddRemoveFavouriteBook body);

    @POST("request.php")
    Call<DefaultServerResponse> contactUs(@Body ContactUsMessage contactUsMessage);

    @POST("request.php")
    Call<DefaultServerResponse> registerUser(@Body UserRegisterMessage userRegisterMessage);

    @GET("request.php")
    Call<DefaultServerResponse> getPrivacyPolicy(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token);

    @GET("request.php")
    Call<DefaultServerResponse> getTermsAndConditions(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token);

    @GET("request.php")
    Call<GetAllBooksResponse> getNumberOfAllAvailableBooks(@Query("value") String values, @Query("apiKey") String apiKey, @Query("token") String token, @Query("type") String type);

}
