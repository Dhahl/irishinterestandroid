package com.irishinterest.irishinterest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.irishinterest.irishinterest.model.user.ContactUsMessage;
import com.irishinterest.irishinterest.model.user.ContactUsMessageEncrypted;
import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText content;
    private Button submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_activity);

        name = findViewById(R.id.fromName);
        email = findViewById(R.id.email);
        content = findViewById(R.id.emailMessage);
        submitButton = findViewById(R.id.submitEmail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());

        submitButton.setOnClickListener(v -> sendMail());

    }

    private void sendMail() {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name field is empty.", Toast.LENGTH_LONG).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email field is empty.", Toast.LENGTH_LONG).show();
        } else if (content.getText().toString().isEmpty()) {
            Toast.makeText(this, "Content field is empty.", Toast.LENGTH_LONG).show();
        } else if (!isEmailValid(email.getText().toString())) {
            Toast.makeText(this, "Email address is not valid.", Toast.LENGTH_LONG).show();
        } else {
            ContactUsMessageEncrypted enc = new ContactUsMessageEncrypted();
            enc.setContactName(name.getText().toString());
            enc.setContactEmail(email.getText().toString());
            enc.setContactMessage(content.getText().toString());
            ContactUsMessage request = new ContactUsMessage();
            request.setAction("contactUs");
            request.setApiKey("testApiKey");
            request.setToken("testTkn");
            request.setIsTest(true);
            request.setEnc(enc);
            SingletonAPI.getInstance().getApiInterface()
                    .contactUs(request).enqueue(new Callback<DefaultServerResponse>() {
                @Override
                public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                    if (response.body().getResponse().equals("Success")) {
                        Toast.makeText(getApplicationContext(), "Message sent successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong, please try again later.", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultServerResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something went wrong, please try again later.", Toast.LENGTH_LONG).show();
                    finish();
                }
            });

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
