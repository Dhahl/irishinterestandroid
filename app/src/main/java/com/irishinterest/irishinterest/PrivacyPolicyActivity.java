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

public class PrivacyPolicyActivity extends AppCompatActivity {

    private TextView privacyPolicyContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy_activity);

        privacyPolicyContent = findViewById(R.id.privacyPolicyContent);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        getPrivacyPolicy();
    }

    private void getPrivacyPolicy(){
        SingletonAPI.getInstance().getApiInterface().getPrivacyPolicy("privacyPolicy", "testApiKey", "testTkn").enqueue(new Callback<DefaultServerResponse>() {
            @Override
            public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                if(response.code() == 200){
                    privacyPolicyContent.setText(response.body().getResponse());
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
