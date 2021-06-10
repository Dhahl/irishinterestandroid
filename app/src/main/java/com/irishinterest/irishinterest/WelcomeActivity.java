package com.irishinterest.irishinterest;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(v -> finish());
    }

    //@Override
    //public void onWindowFocusChanged(boolean hasFocus) {
    //    super.onWindowFocusChanged(hasFocus);
    //    if (hasFocus) {
    //        getWindow().getDecorView().setSystemUiVisibility(
    //                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    //                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    //                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    //                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    //                        | View.SYSTEM_UI_FLAG_FULLSCREEN
    //                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    //    }
    //}
}
