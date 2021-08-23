package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePassword extends AppCompatActivity {


    EditText email;

    Database sqLiteOpenHelper;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        email = findViewById(R.id.etResetPassword);

        sqLiteOpenHelper = new Database(this);
        firebaseAuth = FirebaseAuth.getInstance();


    }



    //Returns to login activity
    public void backLogin(View view) {
        Intent login = new Intent(ChangePassword.this, Sign_In.class);
        startActivity(login);
    }

    public void reset(View view) {
        // Toast.makeText(this, "Available on the next release", Toast.LENGTH_SHORT).show();


        //Reset the user password
        String reset = email.getText().toString();


        if (!Patterns.EMAIL_ADDRESS.matcher(reset).matches()) {
            email.setError("Valid email is required");
            email.requestFocus();

        }
        else {

            //Sending the reset link to the email
            firebaseAuth.sendPasswordResetEmail(reset).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ChangePassword.this, "Check your email and click on the link to reset password", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(ChangePassword.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}