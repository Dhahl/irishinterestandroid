package com.irishinterest.irishinterest.fragments.mainScreen;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.irishinterest.irishinterest.BookCoverFullScreenPopup;
import com.irishinterest.irishinterest.BooksActivity;
import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.ReviewsActivity;
import com.irishinterest.irishinterest.helper.DoubleClickListener;
import com.irishinterest.irishinterest.helper.UserController;
import com.irishinterest.irishinterest.model.authors.Author;
import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.model.user.UserAddRemoveFavoriteBookEncrypted;
import com.irishinterest.irishinterest.model.user.UserAddRemoveFavouriteBook;
import com.irishinterest.irishinterest.model.user.UserValues;
import com.irishinterest.irishinterest.network.api.helper.ApiResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Locale.US;

public class BookDetailsActivity extends AppCompatActivity {

    private ImageButton heartRedImage;
    private ImageButton infoButton;
    private ImageView heartImage;
    private ImageView bookcover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookPublisher;
    private TextView bookIsbn;
    private TextView bookPages;
    private TextView bookPublishDate;
    private TextView bookAvailableAs;
    private TextView bookCategory;
    private TextView bookDescription;
    private ImageButton buyAtAmazon;
    private Button reviews;
    private String isbn13;
    private int bookID;

    private ImageButton shareFacebook;
    private ImageButton shareTwitter;
    private ImageButton youtube;
    private String amazonLink = "";

    private boolean isFavourite = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detailed_view);

        infoButton = findViewById(R.id.infoButton);
        heartRedImage = findViewById(R.id.heartRedFavouriteImageView);
        heartImage = findViewById(R.id.heartLikeImageView);
        bookcover = findViewById(R.id.bookCover);
        bookTitle = findViewById(R.id.bookTitle);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookPublisher = findViewById(R.id.bookPublisher);
        bookPublishDate = findViewById(R.id.bookPublishedDate);
        bookAvailableAs = findViewById(R.id.bookAvailableAs);
        bookCategory = findViewById(R.id.bookCategory);
        bookDescription = findViewById(R.id.bookDescription);
        buyAtAmazon = findViewById(R.id.bookBuyAtAmazon);
        bookIsbn = findViewById(R.id.bookIsbn);

        shareFacebook = findViewById(R.id.facebookShareButton);
        shareTwitter = findViewById(R.id.twitterShareButton);
        youtube = findViewById(R.id.youtubeShareButton);
        reviews = findViewById(R.id.reviews);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bookTitle.setText(Html.fromHtml(extras.getString("bookTitle"), Html.FROM_HTML_MODE_COMPACT));
            } else {
                bookTitle.setText(Html.fromHtml(extras.getString("bookTitle")));
            }
            bookAuthor.setText(extras.getString("bookAuthor"));
            bookPublisher.setText(extras.getString("bookPublisher"));
            bookPublishDate.setText(extras.getString("bookPublishDate"));
            bookAvailableAs.setText(extras.getString("bookAvailableAs"));
            bookCategory.setText(extras.getString("bookCategory"));


            if(extras.getString("bookIsbn").isEmpty()){
                bookIsbn.setText(extras.getString("isbn13"));
            } else {
                bookIsbn.setText(extras.getString("bookIsbn"));
            }

            String countryAmazon = "UK";

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                String locale = this.getResources().getConfiguration().locale.toLanguageTag();

                if(locale.contains("en_US")){
                    countryAmazon = "US";
                } else {
                    countryAmazon = "UK";
                }
            }

            //TODO
            if(countryAmazon.equals("US")){
                //US amazon link
                amazonLink = "https://www.amazon.com/dp/" + extras.getString("bookUsAsin") + "/ref=nosim?tag=irishbookdata-20";
            } else {
                //UK amazon link
                amazonLink = "https://www.amazon.co.uk/dp/" + extras.getString("bookUkAsin") + "/ref=nosim?tag=wwwirishinter-21";
            }


            String bookdescription = extras.getString("bookDescription");
            if(bookdescription != null) {
                if (bookdescription.equals("''") || bookdescription.isEmpty()) {
                    bookDescription.setText("Information from publishers pending.");
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        bookDescription.setText(Html.fromHtml(extras.getString("bookDescription"), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        bookDescription.setText(Html.fromHtml(extras.getString("bookDescription")));
                    }

                }
            }else {
                bookDescription.setText("Information from publishers pending.");
            }
            bookID = extras.getInt("bookId");

            UserValues user = UserController.getUser(getApplicationContext());
            if (user != null) {
                UserAddRemoveFavoriteBookEncrypted enc = new UserAddRemoveFavoriteBookEncrypted();
                enc.setBookId(bookID);
                enc.setUserId(user.getId());
                enc.setAdditionalProperty("favouriteCheckup", true);

                UserAddRemoveFavouriteBook request = new UserAddRemoveFavouriteBook();
                request.setAction("addFavourite");
                request.setApiKey("testApiKey");
                request.setIsTest(true);
                request.setToken("testToken");
                request.setEnc(enc);

                SingletonAPI.getInstance().getApiInterface().addFavouriteBook(request).enqueue(new Callback<DefaultServerResponse>() {
                    @Override
                    public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                        if (response.body().getResponse().equals("Book is already in favourites.")) {
                            infoButton.setVisibility(View.INVISIBLE);
                            heartRedImage.setVisibility(View.VISIBLE);
                            isFavourite = true;
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultServerResponse> call, Throwable t) {

                    }
                });
            }

            Integer authorId = extras.getInt("authorId");
            Context context = this;
            SingletonAPI.getInstance().getApiInterface().getAuthorById("authors", "testApiKey", "testTkn", "getById", authorId).enqueue(new Callback<ApiResponse<Author>>() {
                @Override
                public void onResponse(Call<ApiResponse<Author>> call, Response<ApiResponse<Author>> response) {
                    bookAuthor.setOnClickListener(v -> {
                        Intent intent = new Intent(context, BooksActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("type", "authors");
                        intent.putExtra("firstName", response.body().getResponse().get(0).getFirstname().replace("'", ""));
                        intent.putExtra("lastName", response.body().getResponse().get(0).getLastname().replace("'", ""));
                        intent.putExtra("id", String.valueOf(response.body().getResponse().get(0).getId()));
                        intent.putExtra("profile", response.body().getResponse().get(0).getProfile() == null || response.body().getResponse().get(0).getProfile().isEmpty() ? "Author information pending." : response.body().getResponse().get(0).getProfile());
                        intent.putExtra("authorImage", response.body().getResponse().get(0).getImage() == null ? null : response.body().getResponse().get(0).getImage().toString());
                        context.startActivity(intent);
                    });
                }

                @Override
                public void onFailure(Call<ApiResponse<Author>> call, Throwable t) {
                    System.out.println("Failed");
                }
            });


            buyAtAmazon.setOnClickListener(v -> {
                if (amazonLink.isEmpty()) {
                    String titleSearch = extras.getString("bookTitle");
                    titleSearch.replace(" ", "+");
                    String url = "https://www.amazon.co.uk/s?k=" + titleSearch;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(amazonLink));
                    startActivity(browserIntent);
                }

            });

            int bookId = extras.getInt("bookId");
            String link = "irishinterest.ie/book/?id=" + bookId;
            //Share Twitter
            shareTwitter.setOnClickListener(v -> {
                String tweetUrl = "https://twitter.com/intent/tweet?url=" + link;

                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            });


            //Share Facebook
            shareFacebook.setOnClickListener(v -> {
                String url = "https://www.facebook.com/sharer/sharer.php?u=" + link;
                Uri uri = Uri.parse(url);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            });

            Picasso.get().load("https://irishinterest.ie/upload/" + extras.getString("bookImage"))
                    .placeholder(R.drawable.placeholder)
                    .into(bookcover);
        }

        //Youtube
        youtube.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) "https://www.youtube.com/channel/UCBVh-eIxXZEfh_BK9r8wwdQ"));
            startActivity(browserIntent);
        });

        //We start the Reviews activity
        reviews.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("type", "byBookId");
            intent.putExtra("id", bookID);
            startActivity(intent);
        });

        bookcover.setOnLongClickListener(v -> {

            UserValues user = UserController.getUser(getApplicationContext());
            if (user != null) {
                if (!isFavourite) {
                    animateLike(heartImage);
                    addToFavourites();
                } else {
                    animateLike(heartImage);
                    removeFromFavourites();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please login to add the book to favourites.", Toast.LENGTH_LONG).show();
            }
            return true;
        });

        bookcover.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {
                showImagePopup();
            }

            @Override
            public void onDoubleClick(View v) {
                UserValues user = UserController.getUser(getApplicationContext());
                if (user != null) {
                    if (!isFavourite) {
                        animateLike(heartImage);
                        addToFavourites();
                    } else {
                        animateLike(heartImage);
                        removeFromFavourites();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please login to add the book to favourites.", Toast.LENGTH_LONG).show();
                }

            }
        });

        infoButton.setOnClickListener(v -> {
            Toast.makeText(this, "Press and hold book cover to add it to favourites.", Toast.LENGTH_LONG).show();
        });

        heartRedImage.setOnClickListener(v -> {
            if (isFavourite) {
                removeFromFavourites();
            }
        });

    }

    private String getAmazonLink(String isbn13) {
        //TODO
        try {
            URL url = new URL("https://irishinterest.ie/get_amazon_links.php?isbn13=" + isbn13);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void animateLike(final ImageView view) {
        view.setVisibility(View.VISIBLE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        prepareAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        prepareAnimation(alphaAnimation);
        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(700);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    private Animation prepareAnimation(Animation animation) {
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }

    private void addToFavourites() {
        UserValues user = UserController.getUser(getApplicationContext());
        UserAddRemoveFavoriteBookEncrypted enc = new UserAddRemoveFavoriteBookEncrypted();
        enc.setBookId(bookID);
        enc.setUserId(user.getId());
        enc.setAdditionalProperty("favouriteCheckup", false);

        UserAddRemoveFavouriteBook request = new UserAddRemoveFavouriteBook();
        request.setAction("addFavourite");
        request.setApiKey("testApiKey");
        request.setIsTest(true);
        request.setToken("testToken");
        request.setEnc(enc);

        SingletonAPI.getInstance().getApiInterface().addFavouriteBook(request).enqueue(new Callback<DefaultServerResponse>() {
            @Override
            public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                if (response.body().getResponse().equals("Book is already in favourites.")) {
                    heartRedImage.setVisibility(View.VISIBLE);
                    infoButton.setVisibility(View.GONE);
                    isFavourite = true;
                } else if (response.body().getResponse().equals("Success")) {
                    heartRedImage.setVisibility(View.VISIBLE);
                    infoButton.setVisibility(View.GONE);
                    isFavourite = true;
                }
            }

            @Override
            public void onFailure(Call<DefaultServerResponse> call, Throwable t) {

            }
        });
    }

    private void removeFromFavourites() {
        UserValues user = UserController.getUser(getApplicationContext());
        UserAddRemoveFavoriteBookEncrypted enc = new UserAddRemoveFavoriteBookEncrypted();
        enc.setBookId(bookID);
        enc.setUserId(user.getId());

        UserAddRemoveFavouriteBook request = new UserAddRemoveFavouriteBook();
        request.setAction("removeFavourite");
        request.setApiKey("testApiKey");
        request.setIsTest(true);
        request.setToken("testToken");
        request.setEnc(enc);

        SingletonAPI.getInstance().getApiInterface().removeFavouriteBook(request).enqueue(new Callback<DefaultServerResponse>() {
            @Override
            public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                if (response.body().getResponse().equals("Success")) {
                    heartRedImage.setVisibility(View.GONE);
                    infoButton.setVisibility(View.VISIBLE);
                    isFavourite = false;
                }
            }

            @Override
            public void onFailure(Call<DefaultServerResponse> call, Throwable t) {

            }
        });
    }

    private void showImagePopup() {
        View popUpView = LayoutInflater.from(this).inflate(R.layout.book_cover_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popUpView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);

        ImageView bookCoverImage = (ImageView) popUpView.findViewById(R.id.bookCover);
        LinearLayout linearLayout = (LinearLayout) popUpView.findViewById(R.id.mainContainer);

        linearLayout.setOnClickListener(v -> popupWindow.dismiss());
        bookCoverImage.setOnClickListener(v -> popupWindow.dismiss());

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Picasso.get().load("https://irishinterest.ie/upload/" + extras.getString("bookImage"))
                    .placeholder(R.drawable.placeholder)
                    .into(bookCoverImage);
        }


        popupWindow.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
    }

}
