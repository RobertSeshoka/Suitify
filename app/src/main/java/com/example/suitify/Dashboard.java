package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private String myUserID;//This variable will get the user ID from the database

    private TextView welcomeUser;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        
        welcomeUser = findViewById(R.id.message);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Register");
        myUserID = firebaseUser.getUid();


        reference.child(myUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                saveUser saveUser = snapshot.getValue(com.example.suitify.saveUser.class);

                if (saveUser != null) {
                    String emailProfile = saveUser.email;


                    //Display the email of the user
                    welcomeUser.setText("Welcome " + emailProfile );


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Dashboard.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

//

    }


    //Opening the war games activity
    public void warGames(View view) {
        Intent newActivity = new Intent(this, WarGames.class);
        startActivity(newActivity);
    }


    public void viewGames(View view) {
        Intent newActivity = new Intent(this, ViewGames.class);
        startActivity(newActivity);
    }

    public void newItem(View view) {
        Intent newItem = new Intent(this, NewItem.class);
        startActivity(newItem);
    }

    public void category(View view) {
        Intent newActivity = new Intent(this, Dashboard2.class);
        startActivity(newActivity);
    }

    public void settings(View view) {
        Intent newActivity = new Intent(this, Settings.class);
        startActivity(newActivity);
    }

    public void stats(View view) {
        Intent stats = new Intent(this, ViewGames.class);
        startActivity(stats);
    }

    public void profile(View view) {
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }
}