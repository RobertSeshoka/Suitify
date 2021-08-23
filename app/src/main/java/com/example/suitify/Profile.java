package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView profile, password, werlcomeMessg;
    private FirebaseUser firebaseUser;
    private String myUserID;//This variable will get the user ID from the database

   // private FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profile = findViewById(R.id.profileEmail);
        password = findViewById(R.id.profilePassword);
        werlcomeMessg = findViewById(R.id.welcomeMessage);
        // firebaseAu = FirebaseAuth.getInstance();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Profile loading");
        progressDialog.show();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Register");
        myUserID = firebaseUser.getUid();


        reference.child(myUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                saveUser saveUser = snapshot.getValue(com.example.suitify.saveUser.class);

                if (saveUser != null) {
                    String emailProfile = saveUser.email;
                    String pass = saveUser.password;

                    progressDialog.dismiss();
                    //Display the information of the user
                    werlcomeMessg.setText("Welcome  to your profile");
                    profile.setText(emailProfile);
                    password.setText(pass);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(Profile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}