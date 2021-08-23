package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class ActionGames extends AppCompatActivity {

    EditText gameName, gameSize, gameYear;
    Database database;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Uri myURi;

    ImageView imageView;
    private FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebase.getReference().child("Action Games");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_games);


        //Assigning the variables
        gameName = findViewById(R.id.etActionGameName);
        gameSize = findViewById(R.id.etActionGameSize);
        gameYear = findViewById(R.id.etActionGameYear);

        //Instentiating the firebase storage instanse
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        database = new Database(this);
        imageView = findViewById(R.id.addActionGame);
    }


    public void saveGame(View view) {
        //Checks empty input
        if (gameSize.getText().toString().trim().isEmpty() || gameName.getText().toString().trim().isEmpty() ||
                gameYear.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Provide all input", Toast.LENGTH_SHORT).show();

        } else {
            // database.insertToActionTable(gameName.getText().toString(), gameSize.getText().toString(), gameYear.getText().toString());
            //Toast.makeText(this, "Added to Action games!", Toast.LENGTH_SHORT).show();

            savingActionGames();//Invokes the method
        }
    }


    //Method to save the action game to Firebase realtime database
    public void savingActionGames() {


        //Progress dialog
        final ProgressDialog save = new ProgressDialog(this);
        save.setTitle("Saving game...");
        save.show();

        HashMap<String, String> actionGame = new HashMap<>();

        String name = gameName.getText().toString(), size = gameSize.getText().toString(),
                year = gameYear.getText().toString();

        actionGame.put("GameName", name);
        actionGame.put("GameSize", size);
        actionGame.put("GameYear", year);

        reference.push().setValue(actionGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                save.dismiss();
                Toast.makeText(ActionGames.this, "Game Saved!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private static final int PICK_IMAGE = 1;

    //Adds picture by the camera
    public void addActionPicture(View view) {
        Intent cameraOn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraOn, 0);

        //Opens the gallery to select a photo
        //  Intent intent = new Intent();
        //  intent.setType("images/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(intent.createChooser(intent, "Select image"), PICK_IMAGE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Gets the image
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        myURi = data.getData();
        imageView.setImageURI(data.getData());


        //Uploads the image
        //if (requestCode == 1 && requestCode == RESULT_OK && data != null && data.getData() != null) {
        //     myURi = data.getData();
        //      imageView.setImageURI(data.getData());

        //}


    }

    public void uploadPic() {

        //Progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading picture...");
        progressDialog.show();


        //Tried to upload a picture from Gallery but it didn't want to work
        final String myKey = UUID.randomUUID().toString();
// Create a reference to the image
        StorageReference image = storageReference.child("images/" + myKey);

        image.putFile(myURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Picture uploaded", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Picture not uploaded", Snackbar.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double percentage = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressDialog.setMessage("Percentage: " + percentage + "%");
            }
        });

// Create a reference to 'images/'
        StorageReference mountainImagesRef = storageReference.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
        //   image.getName().equals(mountainImagesRef.getName());    // true
        // image.getPath().equals(mountainImagesRef.getPath());    // false

        //

    }


    public void saveImage(View view) {
        //Invoking the method that will save the image to fiebase   database
        uploadPic();

    }
}