package com.irishinterest.irishinterest;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.irishinterest.irishinterest.helper.UserController;
import com.irishinterest.irishinterest.model.user.User;
import com.irishinterest.irishinterest.model.user.UserLoginRequest;
import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.model.user.UserValues;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;
import com.irishinterest.irishinterest.network.helper.ApiConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText userEmail;
    private TextInputEditText userPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        loginButton = findViewById(R.id.loginButton);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        loginButton.setOnClickListener(v -> {
            String email = userEmail.getText().toString();
            String password = userPassword.getText().toString();

            try {
                JSONObject requestBody = new JSONObject();
                JSONObject encrypted = new JSONObject();
                encrypted.put("email", email);
                encrypted.put("password", password);
                encrypted.put("timestamp", System.currentTimeMillis() / 1000L);

                //TODO encryption
                //String secretKey;
                //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //    secretKey = Base64.getEncoder().encodeToString(ApiConstants.SERVER_SIGNATURE.getKey().getBytes());
                //} else {
                //    secretKey = android.util.Base64.encodeToString(ApiConstants.SERVER_SIGNATURE.getKey().getBytes(), android.util.Base64.DEFAULT);
                //}
                //String encoded = Jwts.builder().claim("n")

                requestBody.put("action", "userLogin");
                requestBody.put("apiKey", "testApiKey");
                requestBody.put("token", "testTkn");
                requestBody.put("isTest", true); //This value is only passed during development.
                requestBody.put("enc", encrypted);

                UserLoginRequest loginRequest = new UserLoginRequest();
                loginRequest.setAction("userLogin");
                loginRequest.setApiKey("testApiKey");
                loginRequest.setToken("testTkn");
                loginRequest.setIsTest(true);

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setTimestamp(System.currentTimeMillis() / 1000L);

                loginRequest.setEnc(user);
                SingletonAPI.getInstance().getApiInterface()
                        .loginUser(loginRequest)
                        .enqueue(new Callback<DefaultServerResponse>() {
                            @Override
                            public void onResponse(Call<DefaultServerResponse> call, Response<DefaultServerResponse> response) {
                                if (response.body() != null) {
                                    try {
                                        DefaultServerResponse apiResponse = response.body();
                                        String secretKey;
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            secretKey = Base64.getEncoder().encodeToString(ApiConstants.SERVER_SIGNATURE.getKey().getBytes());
                                        } else {
                                            secretKey = android.util.Base64.encodeToString(ApiConstants.SERVER_SIGNATURE.getKey().getBytes(), android.util.Base64.NO_WRAP);
                                        }
                                        Claims decoded = (Claims)Jwts.parser().setSigningKey(secretKey).parse(apiResponse.getResponse()).getBody();
                                        LinkedHashMap<String, String> user = (LinkedHashMap<String, String>) ((ArrayList)decoded.get("user")).get(0);
                                        UserValues userValues = new ObjectMapper().readValue(new JSONObject(user).toString(), UserValues.class);
                                        if(user.get("Active") != null && user.get("Active").equalsIgnoreCase("y")){
                                            UserController.saveUserData(userValues, getApplicationContext());
                                            UserController.setUserLoggedIn(true, getApplicationContext());
                                            Toast.makeText(getApplicationContext(), "Welcome " + userValues.getRealName(), Toast.LENGTH_LONG).show();
                                            finish();
                                        } else if(user.get("active") != null && user.get("active").equalsIgnoreCase("y")){
                                            UserController.saveUserData(userValues, getApplicationContext());
                                            UserController.setUserLoggedIn(true, getApplicationContext());
                                            Toast.makeText(getApplicationContext(), "Welcome " + userValues.getRealName(), Toast.LENGTH_LONG).show();
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Your account has not yet been activated. Please activate it by clicking on the activation link that was sent to your email upon registering. ", Toast.LENGTH_LONG).show();
                                        }

                                    } catch (JsonParseException e) {
                                        e.printStackTrace();
                                    } catch (JsonMappingException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultServerResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_LONG).show();
                            }
                        });
            } catch (JSONException e) {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });
    }
}
