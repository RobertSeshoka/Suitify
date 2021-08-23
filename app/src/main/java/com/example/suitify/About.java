package com.example.suitify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }



    //Goes to Settings activity
    public void back(View view) {
        Intent back = new Intent(About.this, Settings.class);
        startActivity(back);
    }
}