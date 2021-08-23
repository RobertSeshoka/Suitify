package com.example.suitify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void back(View view) {
        Intent back = new Intent(Settings.this, Dashboard.class);
        startActivity(back);
    }

    public void logout(View view) {
        Intent back = new Intent(Settings.this, Sign_In.class);
        startActivity(back);
    }


    public void about(View view) {
        Intent about = new Intent(Settings.this, About.class);
        startActivity(about);
    }


    //Opens the activity for change password
    public void changePassword(View view) {
        Intent changeP = new Intent(Settings.this, ChangePassword.class);
        startActivity(changeP);
    }
}