package com.irishinterest.irishinterest.fragments.authors.helper;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.network.api.helper.ApiResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorDataSource extends PageKeyedDataSource<Integer, Author> {

    //the size of a page that we want
    public static int PAGE_SIZE = 30;

    //we will start from the first page which is 0
    private static int FIRST_PAGE = 0;

    private Module module;
    private Object object;

    public AuthorDataSource(Module module, Object object) {
        this.module = module;
        this.object = object;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Author> callback) {
        if(module == Module.AUTHORS) {
            SingletonAPI.getInstance().getApiInterface()
                    .getAllAuthors("authors", "testApiKey", "testTkn", "getAll", String.valueOf(FIRST_PAGE))
                    .enqueue(new Callback<ApiResponse<Author>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (response.body() != null) {
                                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();
                                callback.onResult(authors, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if(module == Module.AUTHOR_SEARCH){
            String query = (String) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getAuthorsBySearch("authors", "testApiKey", "testTkn", "byName", String.valueOf(FIRST_PAGE), query)
                    .enqueue(new Callback<ApiResponse<Author>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (response.body() != null) {
                                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();
                                callback.onResult(authors, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Author> callback) {
        if(module == Module.AUTHORS) {
            Integer categoryId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getAllAuthors("authors", "testApiKey", "testTkn", "getAll", String.valueOf(params.key))
                    .enqueue(new Callback<ApiResponse<Author>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();
                                callback.onResult(authors, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if(module == Module.AUTHOR_SEARCH){
            String query = (String) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getAuthorsBySearch("authors", "testApiKey", "testTkn", "byName", String.valueOf(params.key), query)
                    .enqueue(new Callback<ApiResponse<Author>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();
                                callback.onResult(authors, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Author> callback) {
        if(module == Module.AUTHORS) {
            SingletonAPI.getInstance().getApiInterface()
                    .getAllAuthors("authors", "testApiKey", "testTkn", "getAll", String.valueOf(params.key))
                    .enqueue(new Callback<ApiResponse<Author>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();

                                Integer key = authors.size() == 30 ? params.key + 30 : null;
                                callback.onResult(authors, key);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if(module == Module.AUTHOR_SEARCH){
            String query = (String)  object;
            SingletonAPI.getInstance().getApiInterface()
                    .getAuthorsBySearch("authors", "testApiKey", "testTkn", "byName", String.valueOf(params.key), query)
                    .enqueue(new Callback<ApiResponse<Author>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Author> apiResponse = (ApiResponse<Author>) response.body();
                                ArrayList<Author> authors = (ArrayList<Author>) apiResponse.getResponse();

                                Integer key = authors.size() == 30 ? params.key + 30 : null;
                                callback.onResult(authors, key);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        }
    }
}
