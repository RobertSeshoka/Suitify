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

public class LoadWarGameNewItem extends AppCompatActivity {
    private RecyclerView recyclerView;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("War Games new item");


    //Creating instance of Adaptor class
    private AdapterWarGameNewItem adapter;


    private ArrayList<WarGameNewItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_war_game_new_item);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("War Games new item loading");
        progressDialog.show();

        recyclerView = findViewById(R.id.loadWarGame);
        recyclerView.setHasFixedSize(true);

        arrayList = new ArrayList<>();
        adapter = new AdapterWarGameNewItem(this, arrayList);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WarGameNewItem model = dataSnapshot.getValue(WarGameNewItem.class);
                    arrayList.add(model);
                }

                progressDialog.dismiss();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoadWarGameNewItem.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.startLayoutAnimation();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}