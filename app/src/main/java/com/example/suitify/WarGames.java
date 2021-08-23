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

public class WarGames extends AppCompatActivity {


    //Edit texts

    EditText gameName, gameSize, gameYear;
    Database database;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_games);

        //Assigning the variables
        gameName = findViewById(R.id.etWarGameName);
        gameSize = findViewById(R.id.etWarGameSize);
        gameYear = findViewById(R.id.etWarGameYear);


        imageView = findViewById(R.id.addPicWar);
        database = new Database(this);



    }


    public void saveGame(View view) {
        //Checks empty input
        if (gameSize.getText().toString().trim().isEmpty() || gameName.getText().toString().trim().isEmpty() ||
                gameYear.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Provide all input", Toast.LENGTH_SHORT).show();
        } else {


            // database.insertWarTable(gameName.getText().toString(), gameSize.getText().toString(), gameYear.getText().toString());
            //Toast.makeText(this, "Added to War games!", Toast.LENGTH_SHORT).show();
//Invokes the method that saves the game
            savingWarGames();
        }
    }
    private FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebase.getReference().child("War Games");
    //Method to save the war game to Firebase realtime database
    public void savingWarGames() {
        //Progress dialog
        final ProgressDialog save = new ProgressDialog(this);
        save.setTitle("Saving game...");
        save.show();

        HashMap<String, String> warGame = new HashMap<>();

        String name = gameName.getText().toString(), size = gameSize.getText().toString(),
                year = gameYear.getText().toString();

        warGame.put("GameName", name);
        warGame.put("GameSize", size);
        warGame.put("GameYear", year);

        reference.push().setValue(warGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                save.dismiss();
                Toast.makeText(WarGames.this, "Game Saved!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void addGamePicture(View view) {
        Intent cameraOn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraOn, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //gets the data
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}