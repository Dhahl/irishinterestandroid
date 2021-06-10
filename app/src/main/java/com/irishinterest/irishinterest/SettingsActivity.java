package com.irishinterest.irishinterest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private Button saveButton;
    private CheckBox enableAutoLoginCheckBox;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        saveButton = findViewById(R.id.saveSettings);
        enableAutoLoginCheckBox = findViewById(R.id.autoLoginCheckbox);
        context = this;

        getSavedSettings();

        saveButton.setOnClickListener(v -> {
            saveSettings();
            finish();
        });
    }

    private void saveSettings() {
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.enable_auto_login_setting), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(context.getString(R.string.enable_auto_login_setting), enableAutoLoginCheckBox.isChecked());
        editor.apply();
    }

    private void getSavedSettings(){
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.enable_auto_login_setting), Context.MODE_PRIVATE);
        enableAutoLoginCheckBox.setChecked(sp.getBoolean(context.getString(R.string.enable_auto_login_setting),true));
    }
}
