package com.irishinterest.irishinterest;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.model.user.UserRegisterMessage;
import com.irishinterest.irishinterest.model.user.UserRegisterMessageEncrypted;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("FieldCanBeLocal")
public class RegisterActivity extends AppCompatActivity {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView companyTextView;
    private TextView telephoneTextView;
    private TextView positionTextView;
    private TextView emailTextView;
    private TextView confirmEmailTextView;
    private TextView passwordTextField;
    private TextView confirmPasswordTextField;
    private String registerAs;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        firstNameTextView = findViewById(R.id.firstName);
        lastNameTextView = findViewById(R.id.lastName);
        companyTextView = findViewById(R.id.company);
        telephoneTextView = findViewById(R.id.telephone);
        positionTextView = findViewById(R.id.position);
        emailTextView = findViewById(R.id.emailAddress);
        confirmEmailTextView = findViewById(R.id.confirmEmailAddress);
        passwordTextField = findViewById(R.id.password);
        confirmPasswordTextField = findViewById(R.id.confirmPassword);
        submit = findViewById(R.id.submitRegistration);

        submit.setOnClickListener(v -> {
            String firstName = firstNameTextView.getText().toString();
            String lastName = lastNameTextView.getText().toString();
            String company = companyTextView.getText().toString();
            String telephone = telephoneTextView.getText().toString();
            String position = positionTextView.getText().toString();
            String email = emailTextView.getText().toString();
            String confirmEmail = confirmEmailTextView.getText().toString();
            String password = passwordTextField.getText().toString();
            String confirmPassword = confirmPasswordTextField.getText().toString();

            if (firstName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "First name field is empty.", Toast.LENGTH_LONG).show();
            } else if (lastName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Last name field is empty.", Toast.LENGTH_LONG).show();
            } else if (position.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Position field is empty.", Toast.LENGTH_LONG).show();
            } else if (email.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Email field is empty.", Toast.LENGTH_LONG).show();
            } else if (confirmEmail.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Confirm email field is empty.", Toast.LENGTH_LONG).show();
            } else if (!confirmEmail.equals(email)) {
                Toast.makeText(getApplicationContext(), "Confirm email and email do not match.", Toast.LENGTH_LONG).show();
            } else if (password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Password field is empty.", Toast.LENGTH_LONG).show();
            } else if (confirmPassword.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Confirm password field is empty.", Toast.LENGTH_LONG).show();
            } else if (registerAs.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Register as not selected.", Toast.LENGTH_LONG).show();
            } else if (!confirmPassword.equals(password)) {
                Toast.makeText(getApplicationContext(), "Confirm password and password do not match.", Toast.LENGTH_LONG).show();
            } else if (!isEmailValid(email)) {
                Toast.makeText(getApplicationContext(), "Email address is not valid.", Toast.LENGTH_LONG).show();
            } else {
                UserRegisterMessageEncrypted enc = new UserRegisterMessageEncrypted();
                enc.setFirstname(firstName);
                enc.setLastname(lastName);
                enc.setPublishername(company);
                enc.setTelephone(telephone);
                enc.setPosition(position);
                enc.setEmail(email);
                enc.setPassword(password);
                enc.setConfirm(confirmPassword);
                enc.setUsertype(registerAs);
                UserRegisterMessage request = new UserRegisterMessage();
                request.setAction("userRegister");
                request.setApiKey("testApiKey");
                request.setTest(true);
                request.setToken("testTkn");
                request.setEnc(enc);

                SingletonAPI.getInstance().getApiInterface().registerUser(request).enqueue(new Callback<DefaultServerResponse>() {
                    @Override
                    public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                        switch (response.code()) {
                            case 200: {
                                Toast toast = Toast.makeText(getApplicationContext(), "Thank you for registering, you will receive an email shortly with instructions on how to proceed.", Toast.LENGTH_LONG);
                                View view = toast.getView();
                                view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                                TextView text = view.findViewById(android.R.id.message);
                                text.setTextColor(Color.WHITE);
                                toast.show();
                                finish();
                                break;
                            }
                            case 500: {
                                Toast toast = Toast.makeText(getApplicationContext(), "Oops, something went wrong. Please try again later.", Toast.LENGTH_LONG);
                                View view = toast.getView();
                                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                                TextView text = view.findViewById(android.R.id.message);
                                text.setTextColor(Color.WHITE);
                                toast.show();
                                finish();
                                break;
                            }
                            case 400: {
                                Toast toast = Toast.makeText(getApplicationContext(), "User already registered.", Toast.LENGTH_LONG);
                                View view = toast.getView();
                                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                                TextView text = view.findViewById(android.R.id.message);
                                text.setTextColor(Color.WHITE);
                                toast.show();
                                finish();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultServerResponse> call, Throwable t) {
                        Log.e("TEST", "NOOOO");
                    }
                });
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.publisherSelected:
                if (checked) {
                    registerAs = "publisher";
                }
                break;
            case R.id.promoterSelected:
                if (checked) {
                    registerAs = "promoter";
                }
                break;
            case R.id.correspondentSelected:
                if (checked) {
                    registerAs = "correspondent";
                }
                break;
        }
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }
}
