package com.irishinterest.irishinterest.fragments.mainScreen.helper.endlessScroll;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.model.user.UserGetFavouritesRequest;
import com.irishinterest.irishinterest.model.user.UserGetFavouritesRequestEncrypted;
import com.irishinterest.irishinterest.network.api.helper.ApiResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookDataSource extends PageKeyedDataSource<Integer, Book> {

    //the size of a page that we want
    public static int PAGE_SIZE = 30;

    //we will start from the first page which is 0
    private static int FIRST_PAGE = 0;

    private Module module;
    private Object object;

    public BookDataSource(Module module, Object object) {
        this.module = module;
        this.object = object;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Book> callback) {
        if (module == Module.MAIN_SCREEN) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooks("books", "testApiKey", "testTkn", "getLatest", String.valueOf(FIRST_PAGE))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.CATEGORIES) {
            Integer categoryId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksByCategory("books", "testApiKey", "testTkn", "byCategory", String.valueOf(FIRST_PAGE), String.valueOf(categoryId))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.AUTHORS) {
            Integer authorsId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksByAuthor("books", "testApiKey", "testTkn", "byAuthor", String.valueOf(FIRST_PAGE), String.valueOf(authorsId))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_SEARCH) {
            String search = (String) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksBySearch("books", "testApiKey", "testTkn", "bySearch", String.valueOf(FIRST_PAGE), search)
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_TOP_SEARCHED) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksTopSearches("books", "testApiKey", "testTkn", "topSearched")
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_COMING_SOON) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksComingSoon("books", "testApiKey", "testTkn", "comingSoon", String.valueOf(FIRST_PAGE))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.FAVOURITES) {
            Integer userId = (Integer) object;
            UserGetFavouritesRequestEncrypted enc = new UserGetFavouritesRequestEncrypted();
            enc.setOffset(FIRST_PAGE);
            enc.setUserId(userId);
            UserGetFavouritesRequest request = new UserGetFavouritesRequest();
            request.setAction("getFavourites");
            request.setApiKey("testApiKey");
            request.setToken("testToken");
            request.setTest(true);
            request.setEnc(enc);
            SingletonAPI.getInstance().getApiInterface()
                    .getFavouriteBooks(request)
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, null, FIRST_PAGE + 30);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {
        if (module == Module.MAIN_SCREEN) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooks("books", "testApiKey", "testTkn", "getLatest", String.valueOf(params.key))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.CATEGORIES) {
            Integer categoryId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksByCategory("books", "testApiKey", "testTkn", "byCategory", String.valueOf(params.key), String.valueOf(categoryId))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.AUTHORS) {
            Integer authorsId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksByAuthor("books", "testApiKey", "testTkn", "byAuthor", String.valueOf(params.key), String.valueOf(authorsId))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_SEARCH) {
            String search = (String) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksBySearch("books", "testApiKey", "testTkn", "bySearch", String.valueOf(params.key), search)
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_TOP_SEARCHED) {
            //SingletonAPI.getInstance().getApiInterface()
            //        .getBooksTopSearches("books", "testApiKey", "testTkn", "topSearched")
            //        .enqueue(new Callback<ApiResponse<Book>>() {
            //            @Override
            //            public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
            //                Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
            //                Log.d("PARAM KEY ", params.key.toString());
            //                if (response.body() != null) {
            //                    ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
            //                    ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
            //                    callback.onResult(books, adjacentKey);
            //                }
            //            }
//
            //            @Override
            //            public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
            //                Log.d("RESPONSE", t.toString());
            //            }
            //        });
        } else if (module == Module.BOOK_COMING_SOON) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksComingSoon("books", "testApiKey", "testTkn", "comingSoon", String.valueOf(params.key))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.FAVOURITES) {

            Integer userId = (Integer) object;
            UserGetFavouritesRequestEncrypted enc = new UserGetFavouritesRequestEncrypted();
            enc.setOffset(params.key);
            enc.setUserId(userId);
            UserGetFavouritesRequest request = new UserGetFavouritesRequest();
            request.setAction("getFavourites");
            request.setApiKey("testApiKey");
            request.setToken("testToken");
            request.setTest(true);
            request.setEnc(enc);

            SingletonAPI.getInstance().getApiInterface()
                    .getFavouriteBooks(request)
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            Integer adjacentKey = (params.key >= 30) ? params.key - 30 : null;
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
                                callback.onResult(books, adjacentKey);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {
        if (module == Module.MAIN_SCREEN) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooks("books", "testApiKey", "testTkn", "getLatest", String.valueOf(params.key))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();

                                Integer key = books.size() == 30 ? params.key + 30 : null;
                                callback.onResult(books, key);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.CATEGORIES) {
            Integer categoryId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksByCategory("books", "testApiKey", "testTkn", "byCategory", String.valueOf(params.key), String.valueOf(categoryId))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();

                                Integer key = books.size() == 30 ? params.key + 30 : null;
                                callback.onResult(books, key);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.AUTHORS) {
            Integer authorsId = (Integer) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksByAuthor("books", "testApiKey", "testTkn", "byAuthor", String.valueOf(params.key), String.valueOf(authorsId))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.d("PARAM KEY ", params.key.toString());
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();

                                Integer key = books.size() == 30 ? params.key + 30 : null;
                                callback.onResult(books, key);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_SEARCH) {
            String search = (String) object;
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksBySearch("books", "testApiKey", "testTkn", "bySearch", String.valueOf(params.key), search)
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();

                                Integer key = books.size() == 30 ? params.key + 30 : null;
                                callback.onResult(books, key);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.BOOK_TOP_SEARCHED) {
            //SingletonAPI.getInstance().getApiInterface()
            //        .getBooksTopSearches("books", "testApiKey", "testTkn", "topSearched")
            //        .enqueue(new Callback<ApiResponse<Book>>() {
            //            @Override
            //            public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
            //                if (response.body() != null) {
            //                    ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
            //                    ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();
//
            //                    Integer key = books.size() == 30 ? params.key + 30 : null;
            //                    callback.onResult(books, key);
            //                }
            //            }
//
            //            @Override
            //            public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
            //                Log.d("RESPONSE", t.toString());
            //            }
            //        });
        } else if (module == Module.BOOK_COMING_SOON) {
            SingletonAPI.getInstance().getApiInterface()
                    .getBooksComingSoon("books", "testApiKey", "testTkn", "comingSoon", String.valueOf(params.key))
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();

                                Integer key = books.size() == 30 ? params.key + 30 : null;
                                callback.onResult(books, key);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        } else if (module == Module.FAVOURITES) {
            Integer userId = (Integer) object;
            UserGetFavouritesRequestEncrypted enc = new UserGetFavouritesRequestEncrypted();
            enc.setOffset(params.key);
            enc.setUserId(userId);
            UserGetFavouritesRequest request = new UserGetFavouritesRequest();
            request.setAction("getFavourites");
            request.setApiKey("testApiKey");
            request.setToken("testToken");
            request.setTest(true);
            request.setEnc(enc);

            SingletonAPI.getInstance().getApiInterface()
                    .getFavouriteBooks(request)
                    .enqueue(new Callback<ApiResponse<Book>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<Book>> call, Response<ApiResponse<Book>> response) {
                            if (response.body() != null) {
                                ApiResponse<Book> apiResponse = (ApiResponse<Book>) response.body();
                                ArrayList<Book> books = (ArrayList<Book>) apiResponse.getResponse();

                                Integer key = books.size() == 30 ? params.key + 30 : null;
                                callback.onResult(books, key);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<Book>> call, Throwable t) {
                            Log.d("RESPONSE", t.toString());
                        }
                    });
        }
    }
}
