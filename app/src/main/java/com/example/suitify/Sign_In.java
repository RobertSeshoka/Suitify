package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_In extends AppCompatActivity {

    EditText myemail, mypassword;
    private FirebaseAuth mAuth;
    Database mydatebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        myemail = findViewById(R.id.etUserEmail);
        mypassword = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();

        mydatebase = new Database(this);
    }

    public void signin(View view) {
        try {

            //Progress dialog
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Signing in...");
            progressDialog.show();

            String emailHere = myemail.getText().toString();
            String passwordHere = mypassword.getText().toString();
            Boolean validate = mydatebase.checkEmailPasswordOfUser(emailHere, passwordHere);


            String loginEmail = myemail.getText().toString();
            String loginPassword = mypassword.getText().toString();

            if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()) {
                myemail.setError("A valid email is required");
                myemail.requestFocus();
            }

            if (loginEmail.equals("") || loginPassword.equals("")) {
                progressDialog.dismiss();
                myemail.setError("Email is required");
                myemail.requestFocus();

                mypassword.setError("Password is required");
                mypassword.requestFocus();

            } else {


                mAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Checks if the login details

                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            //If the user email has been verified the user will login
                            //NB: Make sure you enter an existing email in order verify
                            if (firebaseUser.isEmailVerified()) {

                                Intent proceed = new Intent(Sign_In.this, Dashboard.class);
                                startActivity(proceed);
                                finish();

                            }

                            //The if the email is not verified the user will have to check their email to verify the email
                            else {
                                progressDialog.dismiss();
                                firebaseUser.sendEmailVerification();//Sends the email verification link to the email
                                Toast.makeText(Sign_In.this, "Please check you email inbox and verify email.", Toast.LENGTH_LONG).show();

                            }


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Sign_In.this, "Invalid account or user has not registered", Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }

        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void signup(View view) {

        Intent newActivity = new Intent(this, Sign_Up.class);
        startActivity(newActivity);
    }

    //Opens activity for changing password
    public void changePassword(View view) {
        Intent changeP = new Intent(Sign_In.this, ChangePassword.class);
        startActivity(changeP);
    }

}