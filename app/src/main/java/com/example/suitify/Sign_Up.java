package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {

    EditText mail, password, password2;
    Database database;
    private ProgressBar pleaseWait;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        database = new Database(this);
        pleaseWait = findViewById(R.id.loading);
        mAuth = FirebaseAuth.getInstance();

        mail = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        password2 = findViewById(R.id.etConfirmPassword);

    }

    public void signup(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Signing up...");
        progressDialog.show();
        try {


            String registerEmailString = mail.getText().toString().trim(),
                    registerPassword = password.getText().toString().trim(),
                    comfirmPassword = password2.getText().toString().trim();


            if (!Patterns.EMAIL_ADDRESS.matcher(registerEmailString).matches()) {
                progressDialog.dismiss();
                mail.setError("A validEmail is required");
                mail.requestFocus();
            }
            if (registerPassword.isEmpty() || comfirmPassword.isEmpty()) {
                progressDialog.dismiss();
                password.setError("Password is required");
                password.requestFocus();
                password2.setError("Password is required");
                password2.requestFocus();

            } else if (password.length() < 4) {
                progressDialog.dismiss();
                Toast.makeText(this, "Password must be 4 characters", Toast.LENGTH_SHORT).show();

            } else
                //Checks for matching password.
                //If the provided passwords do match the user will now be registered.
                if (registerPassword.equals(comfirmPassword)) {

                    pleaseWait.setVisibility(View.VISIBLE);

                    //Passing the email and password strings to Firebase method.
                    mAuth.createUserWithEmailAndPassword(registerEmailString, registerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Checks if the tasks is completed
                            if (task.isSuccessful()) {


                                saveUser save = new saveUser(registerEmailString, registerPassword);
                                FirebaseDatabase.getInstance().getReference("Register").child(FirebaseAuth.
                                        getInstance().getCurrentUser().getUid()).setValue(save).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(Sign_Up.this, "Sign up successful", Toast.LENGTH_SHORT).show();

                                            pleaseWait.setVisibility(View.GONE);//
                                            Intent bac = new Intent(Sign_Up.this, Sign_In.class);
                                            startActivity(bac);

                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(Sign_Up.this, "Email already exist error", Toast.LENGTH_SHORT).show();
                                            pleaseWait.setVisibility(View.GONE);

                                        }
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Sign_Up.this, "Error try again", Toast.LENGTH_SHORT).show();
                                pleaseWait.setVisibility(View.GONE);
                            }
                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Passwords do not match!!!", Toast.LENGTH_SHORT).show();
                }
        } catch (Exception msg) {
            progressDialog.dismiss();
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    public void signin(View view) {
        Intent bac = new Intent(this, Sign_In.class);
        startActivity(bac);
    }


    //Goes back to login
    public void back(View view) {
        Intent bac = new Intent(this, Sign_In.class);
        startActivity(bac);
    }


}