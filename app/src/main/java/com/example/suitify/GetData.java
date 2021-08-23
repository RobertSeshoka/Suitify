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

public class GetData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Racing Games loading...");
        progressDialog.show();

        recyclerView = findViewById(R.id.getData);
        recyclerView.setHasFixedSize(true);

        arrayList = new ArrayList<>();
        adapter = new Adapter(this, arrayList);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RacingGamesClass model = dataSnapshot.getValue(RacingGamesClass.class);
                    arrayList.add(model);
                }

                progressDialog.dismiss();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GetData.this, error.getMessage(), Toast.LENGTH_LONG).show();
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


    private RecyclerView recyclerView;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Racing Games");


    //Creating instance of Adaptor class
    private Adapter adapter;


    private ArrayList<RacingGamesClass> arrayList;


}