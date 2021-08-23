package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RacingGames extends AppCompatActivity {


    EditText gameName, gameSize, gameYear;
    Database database;
    ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racing_games);


        //Assigning the variables
        gameName = findViewById(R.id.etGameName);
        gameSize = findViewById(R.id.etGameSize);
        gameYear = findViewById(R.id.etGameYear);

        view = findViewById(R.id.addPicRacig);
        database = new Database(this);
    }


    //Saving the racing game
    public void saveGame(View view) {
        //Checks empty input
        if (gameSize.getText().toString().trim().isEmpty() || gameName.getText().toString().trim().isEmpty() ||
                gameYear.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Provide all input", Toast.LENGTH_SHORT).show();
        } else {
            //Writing tot the database

            // database.inserRacingTable(gameName.getText().toString(), gameSize.getText().toString(), gameYear.getText().toString());
            //Toast.makeText(this, "Added to Racing Games!", Toast.LENGTH_SHORT).show();
            savingRacingGames();

        }
    }

    private FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebase.getReference().child("Racing Games");

    //Method to save the racing game to Firebase realtime database
    public void savingRacingGames() {
        //Progress dialog
        final ProgressDialog save = new ProgressDialog(this);
        save.setTitle("Saving game...");
        save.show();

        HashMap<String, String> racingGame = new HashMap<>();

        String name = gameName.getText().toString(), size = gameSize.getText().toString(),
                year = gameYear.getText().toString();

        racingGame.put("GameName", name);
        racingGame.put("GameSize", size);
        racingGame.put("GameYear", year);

        reference.push().setValue(racingGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                save.dismiss();
                Toast.makeText(RacingGames.this, "Game Saved!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void addRacingPicture(View view) {
        Intent cameraOn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        startActivityForResult(cameraOn, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //gets the data
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        view.setImageBitmap(bitmap);
    }

}