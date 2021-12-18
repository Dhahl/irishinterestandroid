package com.irishinterest.irishinterest.network.api.irishInterest;

import android.content.Context;
import android.util.Log;

import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.model.authors.AuthorValues;
import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.model.books.BookValues;
import com.irishinterest.irishinterest.model.categories.Category;
import com.irishinterest.irishinterest.model.categories.CategoryValues;
import com.irishinterest.irishinterest.model.user.UserLoginRequest;
import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.model.user.UserLogoutResponse;
import com.irishinterest.irishinterest.model.user.UserRegistrationResponse;
import com.irishinterest.irishinterest.network.api.helper.ApiResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Action;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiObserver;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.InternalAction;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.ScrollAction;
import com.irishinterest.irishinterest.network.helper.ApiConstants;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.irishinterest.irishinterest.network.api.irishInterest.observer.Module.BOOK_DETAILED;
import static com.irishinterest.irishinterest.network.api.irishInterest.observer.Module.CATEGORIES;

public class IrishInterestAPI implements Observer {

    private static final String BASE_URL = ApiConstants.API_URL.getKey();
    private static Retrofit retrofit;
    private IrishInterestApiInterface apiInterface;
    private final GuiObserver guiObserver;

    public IrishInterestAPI(GuiObserver guiObserver, Context context) {
        this.guiObserver = guiObserver;
        initialize();
    }

    private void initialize() {
        ((GuiToModel) guiObserver).registerObserver(Module.BOOK, this);
        ((GuiToModel) guiObserver).registerObserver(CATEGORIES, this);
        ((GuiToModel) guiObserver).registerObserver(Module.USER, this);
        ((GuiToModel) guiObserver).registerObserver(Module.MAIN_SCREEN, this);
        ((GuiToModel) guiObserver).registerObserver(BOOK_DETAILED, this);

        retrofit = getRetrofitClient();
        apiInterface = retrofit.create(IrishInterestApiInterface.class);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GuiMessage) {
            GuiMessage guiMessage = (GuiMessage) arg;
            if (guiMessage.getInternalAction().equals(InternalAction.REQUEST)) {
                switch (guiMessage.getModule()) {
                    case BOOK: {
                        //TODO:ALL BOOK REQUESTS
                    }
                    case USER: {
                        //TODO: USER REQUESTS
                        switch (guiMessage.getAction()) {
                            case LOGIN: {
                                //Login the user with the provided credentials
                                Call call = apiInterface.loginUser((UserLoginRequest) guiMessage.getDataList().get(0));
                                call.enqueue(loginUserCallback());
                                break;
                            }
                            case LOGOUT: {
                                //Logout the user with the provided credentials
                                Call call = apiInterface.logoutUser((String) guiMessage.getDataList().get(0));
                                call.enqueue(logoutUserCallback());
                                break;
                            }
                            case POST_REVIEW: {
                                //POST A REVIEW
                                Call call = apiInterface.postReview((String) guiMessage.getDataList().get(0));
                                call.enqueue(postReview());
                                break;
                            }
                            default: {
                                //TODO: this needs to be implemented on server
                                break;
                            }
                        }
                    }
                    case MAIN_SCREEN: {
                        if (guiMessage.getAction().equals(Action.INIT)) {
                            //We have to fetch latest books.
                            Call call = apiInterface.getBooks("books", "testApiKey", "testTkn", "getLatest", String.valueOf(0));
                            call.enqueue(latestBooksCallback(ScrollAction.SCROLL_DOWN));
                        } else if(guiMessage.getAction().equals(Action.ADD)){
                            Call call = apiInterface.getBooks("books", "testApiKey", "testTkn", "getLatest", String.valueOf(guiMessage.getDataList().get(0)));
                            call.enqueue(latestBooksCallback(guiMessage.getScrollAction()));
                        }
                        break;
                    }
                    case CATEGORIES: {
                        if (guiMessage.getAction().equals(Action.INIT)) {
                            //We have to fetch latest books.
                            Call call = apiInterface.getAllCategories("categories", "testApiKey", "testTkn");
                            call.enqueue(allCategoriesCallback());
                        }
                    }
                    case BOOK_DETAILED: {
                    }
                    case AUTHORS: {
                        //We fetch all authors + offset
                        if(guiMessage.getAction().equals(Action.INIT)){
                            Call call = apiInterface.getAllAuthors("authors", "testApiKey", "testTkn", "getAll", String.valueOf(0));
                            call.enqueue(allAuthorsCallback());
                        } else if(guiMessage.getAction().equals(Action.ADD)){
                            Call call = apiInterface.getAllAuthors("authors", "testApiKey", "testTkn", "getAll", String.valueOf(guiMessage.getDataList().get(0)));
                            call.enqueue(allAuthorsCallback());
                        } else if(guiMessage.getAction().equals(Action.MODIFY)){
                            //TODO
                        }
                        break;
                    }
                }
            }
        }
    }

    private static Retrofit getRetrofitClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        }
        return retrofit;
    }

    /**
     * CALLBACKS
     */

    private Callback latestBooksCallback(final ScrollAction scrollAction){

        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                guiObserver.publishMessage(new GuiMessage(Module.MAIN_SCREEN, Action.INIT, InternalAction.RESPONSE, scrollAction, new BookValues(books)));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("RESPONSE", t.toString());
            }
        };
    }

    private Callback allCategoriesCallback(){

        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse<Category> apiResponse = (ApiResponse<Category>) response.body();
                ArrayList<Category> categories = (ArrayList<Category>) apiResponse.getResponse();
                guiObserver.publishMessage(new GuiMessage(Module.CATEGORIES, Action.INIT, InternalAction.RESPONSE, new CategoryValues(categories)));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }

    private Callback allAuthorsCallback(){

        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();
                guiObserver.publishMessage(new GuiMessage(Module.AUTHORS, Action.INIT, InternalAction.RESPONSE, new AuthorValues(authors)));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }

    private Callback loginUserCallback(){
        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse<DefaultServerResponse> apiResponse = (ApiResponse<DefaultServerResponse>) response.body();
                //TODO
                guiObserver.publishMessage(new GuiMessage(Module.USER, Action.LOGIN, InternalAction.RESPONSE));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }

    private Callback logoutUserCallback(){
        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse<UserLogoutResponse> apiResponse = (ApiResponse<UserLogoutResponse>) response.body();
                //TODO
                guiObserver.publishMessage(new GuiMessage(Module.USER, Action.LOGOUT, InternalAction.RESPONSE));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }

    private Callback registerUserCallback(final Module module){
        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse<UserRegistrationResponse> apiResponse = (ApiResponse<UserRegistrationResponse>) response.body();
                //TODO
                guiObserver.publishMessage(new GuiMessage(module, Action.REGISTER, InternalAction.RESPONSE));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }

    private Callback postReview(){
        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
              //TODO

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }

    private Callback getReviews(final Module module){
        return new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //TODO
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
    }
}
