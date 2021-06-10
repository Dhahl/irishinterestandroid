package com.irishinterest.irishinterest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.irishinterest.irishinterest.model.user.ReviewResponse;
import com.irishinterest.irishinterest.model.user.ReviewsObj;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsActivity extends AppCompatActivity {

    private List<ReviewsObj> reviewsObjList;
    private LinearLayout parent;
    private Button postReview;
    private Integer bookID;
    private TextView noReviewsYet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_reviews_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        parent = findViewById(R.id.reviewParent);
        noReviewsYet = findViewById(R.id.noReviewsYet);

        final Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String type = extras.getString("type");
            Integer id = extras.getInt("id");
            bookID = id;
            getReviewsAndComments(type, id);
        }

        postReview = findViewById(R.id.postReviewButton);

        postReview.setOnClickListener(v ->{
            Intent intent = new Intent(this, PostReviewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", bookID);
            startActivity(intent);
        });
    }

    private void getReviewsAndComments(String type, Integer id){
        if(type.equals("byBookId")) {
            SingletonAPI.getInstance().getApiInterface().getReviewsByBookId("reviews", "testApiKey", "testTkn", id, "reviewByBookId").enqueue(new Callback<ReviewResponse>() {
                @Override
                public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                    if(response.code() == 200){
                        reviewsObjList = response.body().getResponse();
                        createUi();
                    } else {
                        Toast.makeText(getApplicationContext(), "Oops, something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ReviewResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
                }
            });
        } else if(type.equals("byUserId")){
            //TODO
            //SingletonAPI.getInstance().getApiInterface().getReviewsByUserId("reviewByBookId", "testApiKey", "testToken")
        }
    }

    private void getComments(Integer reviewId){
        //TODO
    }

    private void createUi(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(ReviewsObj review : reviewsObjList){
                    noReviewsYet.setVisibility(View.GONE);
                    // Author name and date
                    TextView reviewAuthor = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    reviewAuthor.setText(review.getAuthorName() + " - " + review.getDate());
                    reviewAuthor.setLayoutParams(layoutParams);
                    reviewAuthor.setTextSize(16);
                    reviewAuthor.setTypeface(null, Typeface.BOLD);
                    int paddingDp = 10;
                    float density = getApplicationContext().getResources().getDisplayMetrics().density;
                    int paddingPixel = (int)(paddingDp * density);
                    reviewAuthor.setPadding(paddingPixel, 0, 0, 0);
                    parent.addView(reviewAuthor);

                    //Review text
                    TextView reviewText = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams layoutParamsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    reviewText.setText(review.getReviewText());
                    reviewText.setLayoutParams(layoutParams);
                    reviewText.setTextSize(13);
                    reviewText.setPadding(paddingPixel, 0, paddingPixel, 0);
                    reviewText.setLayoutParams(layoutParamsText);
                    parent.addView(reviewText);

                }
            }
        });
    }
}
