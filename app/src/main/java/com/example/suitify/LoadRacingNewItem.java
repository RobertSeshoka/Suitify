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

public class LoadRacingNewItem extends AppCompatActivity {

    private RecyclerView recyclerView;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Racing Games new item");


    //Creating instance of Adaptor class
    private AdapterRacingGameNewItem adapter;


    private ArrayList<RacingGameNewItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_racing_new_item);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Racing Games new item loading");
        progressDialog.show();

        recyclerView = findViewById(R.id.loadRacingGame);
        recyclerView.setHasFixedSize(true);

        arrayList = new ArrayList<>();
        adapter = new AdapterRacingGameNewItem(this, arrayList);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RacingGameNewItem model = dataSnapshot.getValue(RacingGameNewItem.class);
                    arrayList.add(model);
                }

                progressDialog.dismiss();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoadRacingNewItem.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
