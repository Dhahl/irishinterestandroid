package com.irishinterest.irishinterest;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsAndConditionsActivity extends AppCompatActivity {

    private TextView termsAndConditionsContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_conditions_activity);

        termsAndConditionsContent = findViewById(R.id.termsAndConditionsContent);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        getTermsAndConditions();
    }

    private void getTermsAndConditions(){
        SingletonAPI.getInstance().getApiInterface().getTermsAndConditions("termsAndConditions", "testApiKey", "testTkn").enqueue(new Callback<DefaultServerResponse>() {
            @Override
            public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                if(response.code() == 200){
                    termsAndConditionsContent.setText(response.body().getResponse());
                } else {
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Oops, something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

}
