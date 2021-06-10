package com.irishinterest.irishinterest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.irishinterest.irishinterest.helper.UserController;
import com.irishinterest.irishinterest.model.user.UserValues;

public class MyProfileActivity extends AppCompatActivity {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView companyTextView;
    private TextView telephoneTextView;
    private TextView positionTextView;
    private TextView emailTextView;
    private Button myReviewsButton;
    private Button myCommentsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        firstNameTextView = findViewById(R.id.firstNameField);
        lastNameTextView = findViewById(R.id.lastNameField);
        companyTextView = findViewById(R.id.companyNameField);
        telephoneTextView = findViewById(R.id.telephoneField);
        positionTextView = findViewById(R.id.positionField);
        emailTextView = findViewById(R.id.emailField);
        myReviewsButton = findViewById(R.id.myReviewsButton);
        myCommentsButton = findViewById(R.id.myComments);

        UserValues user = UserController.getUser(getApplicationContext());

        if(user != null) {
            firstNameTextView.setText(user.getRealName() == null || user.getRealName().isEmpty() ? "-" : user.getRealName());
            lastNameTextView.setText(user.getLastname() == null || user.getLastname().isEmpty() ? "-" : user.getLastname());
            companyTextView.setText(user.getPublisher() == null || user.getPublisher().isEmpty() ? "-" : user.getPublisher());
            telephoneTextView.setText(user.getTelephone() == null || user.getTelephone().isEmpty() ? "-" : user.getTelephone());
            positionTextView.setText(user.getPosition() == null || user.getPosition().isEmpty() ? "-" : user.getPosition());
            emailTextView.setText(user.getEmail() == null || user.getEmail().isEmpty() ? "-" : user.getEmail());
        }
        myReviewsButton.setOnClickListener(v -> {
            //TODO -
        });

        myCommentsButton.setOnClickListener(v -> {
            //TODO -
        });
    }
}
