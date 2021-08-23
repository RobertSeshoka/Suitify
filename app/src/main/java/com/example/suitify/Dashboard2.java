package com.example.suitify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
    }



    //Opening the war games activity
    public void warGames(View view)
    {
        Intent newActivity = new Intent(this,WarGames.class);
        startActivity(newActivity);
    }



    //Opening the racing games activity
    public void racingGames(View view)
    {
        Intent newActivity = new Intent(this,RacingGames.class);
        startActivity(newActivity);
    }



    //Opening the action games activity
    public void actionGames(View view)
    {
        Intent newActivity = new Intent(this,ActionGames.class);
        startActivity(newActivity);
    }


    public void viewGames(View view)
    {
        Intent newActivity = new Intent(this,ViewGames.class);
        startActivity(newActivity);
    }
}