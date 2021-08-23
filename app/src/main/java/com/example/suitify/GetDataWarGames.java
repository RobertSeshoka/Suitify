package com.example.suitify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetDataWarGames extends AppCompatActivity {
    private RecyclerView recyclerViewWarGms;

    private AdapterWarGames adapterWarGms;
    private ArrayList<WarGamesClass> arrayListWarGms;

    //Accessing the War Game by the reference r child name
    private FirebaseDatabase firebaseDatabaseWarGms = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReferenceWarGms = firebaseDatabaseWarGms.getReference().child("War Games");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_war_games);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("War Games loading...");
        progressDialog.show();


        //Setting up the recycler view

        recyclerViewWarGms = findViewById(R.id.getDataWarGames);
        recyclerViewWarGms.setHasFixedSize(true);

        arrayListWarGms = new ArrayList<>();
        adapterWarGms = new AdapterWarGames(this, arrayListWarGms);



        recyclerViewWarGms.setAdapter(adapterWarGms);
        recyclerViewWarGms.setLayoutManager(new LinearLayoutManager(this));


        databaseReferenceWarGms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WarGamesClass modelWarGms = dataSnapshot.getValue(WarGamesClass.class);
                    arrayListWarGms.add(modelWarGms);
                }

                progressDialog.dismiss();
                adapterWarGms.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
                Toast.makeText(GetDataWarGames.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewWarGms.startLayoutAnimation();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}