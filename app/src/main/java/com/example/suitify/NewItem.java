package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class NewItem extends AppCompatActivity {

    TextView textView;
    EditText gameName, numberOfGames, categoryGame;

    private FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebase.getReference().child("Action Games new item");

    private FirebaseDatabase firebaseWar = FirebaseDatabase.getInstance();
    private DatabaseReference referenceWar = firebaseWar.getReference().child("War Games new item");

    private FirebaseDatabase firebaseRacing = FirebaseDatabase.getInstance();
    private DatabaseReference referenceRacing = firebaseRacing.getReference().child("Racing Games new item");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);


        //Gets the xml by ID
        textView = findViewById(R.id.text_view_selected);


        gameName = findViewById(R.id.addnewItem);
        numberOfGames = findViewById(R.id.addHowManyGames);
        categoryGame = findViewById(R.id.gameCategory);


        Button buttonApply = findViewById(R.id.btnAApply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyButton();

            }
        });
    }


    public void applyButton() {

        try {
            int number = Integer.parseInt(numberOfGames.getText().toString());
            String ctgry1 = categoryGame.getText().toString();
            String ctgry2 = categoryGame.getText().toString();
            String ctgry3 = categoryGame.getText().toString();
            String ctgry4 = categoryGame.getText().toString();
              /*
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();


               */
            //Checking for empty fields when the button is clicked
            if (gameName.getText().toString().isEmpty()) {
                gameName.setError("Provide input");
                gameName.requestFocus();
            }
            if (numberOfGames.getText().toString().isEmpty()) {
                numberOfGames.setError("Provide input");
                numberOfGames.requestFocus();

            }
            if (numberOfGames.getText().toString().isEmpty()) {
                numberOfGames.setError("Provide input");
                numberOfGames.requestFocus();

            } else
                //Checking the input number if is higher than 10
                if (number > 10) {
                    Toast.makeText(NewItem.this, "Number of games should not be higher than 10", Toast.LENGTH_LONG).show();
                } else
                    //Saving the game by the entered category
                    if (ctgry1.equals("Action")) {
                        saveInActionGameCategory();

                    } else if (ctgry2.equals("War")) {
                        saveInWarGameCategory();
                    } else if (ctgry3.equals("Racing")) {
                        saveInRacingGameCategory();
                    } else {

                        Toast.makeText(NewItem.this, "Invalid category", Toast.LENGTH_LONG).show();

                    }


        } catch (Exception e) {
            Toast.makeText(NewItem.this, "Please enter only numbers for the number of games", Toast.LENGTH_LONG).show();

        }


    }


    private void saveInActionGameCategory() {
        //Progress dialog
        final ProgressDialog save = new ProgressDialog(this);
        save.setTitle("Saving game...");
        save.show();

        HashMap<String, String> actionGame = new HashMap<>();

        String category = categoryGame.getText().toString(), name = gameName.getText().toString(),
                numberGames = numberOfGames.getText().toString();

        actionGame.put("category", category);
        actionGame.put("name", name);
        actionGame.put("gamesaved", numberGames);

        reference.push().setValue(actionGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                save.dismiss();
                Toast.makeText(NewItem.this, "Game Saved to action games category!", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void saveInWarGameCategory() {
        //Progress dialog
        final ProgressDialog save = new ProgressDialog(this);
        save.setTitle("Saving game...");
        save.show();

        HashMap<String, String> actionGame = new HashMap<>();

        String category = categoryGame.getText().toString(), name = gameName.getText().toString(),
                numberGames = numberOfGames.getText().toString();

        actionGame.put("category", category);
        actionGame.put("name", name);
        actionGame.put("gamesaved", numberGames);

        referenceWar.push().setValue(actionGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                save.dismiss();
                Toast.makeText(NewItem.this, "Game Saved to War games category", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void saveInRacingGameCategory() {
        //Progress dialog
        final ProgressDialog save = new ProgressDialog(this);
        save.setTitle("Saving game...");
        save.show();

        HashMap<String, String> actionGame = new HashMap<>();

        String category = categoryGame.getText().toString(), name = gameName.getText().toString(),
                numberGames = numberOfGames.getText().toString();

        actionGame.put("category", category);
        actionGame.put("name", name);
        actionGame.put("gamesaved", numberGames);

        referenceRacing.push().setValue(actionGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                save.dismiss();
                Toast.makeText(NewItem.this, "Game Saved to Racing games category", Toast.LENGTH_SHORT).show();

            }
        });
    }


}