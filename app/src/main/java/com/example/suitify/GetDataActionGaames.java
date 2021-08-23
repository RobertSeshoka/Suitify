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

public class GetDataActionGaames extends AppCompatActivity {

    private RecyclerView recyclerViewAction;//For Action Games

    private AdapterActionGames adapterAction;
    private ArrayList<ActionGamesClass> arrayListAction;

    //For Action Games
    private FirebaseDatabase firebaseDatabaseAction = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReferenceAction = firebaseDatabaseAction.getReference().child("Action Games");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_action_gaames);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Action Games loading...");
        progressDialog.show();


        //Setting up the recycler view

        recyclerViewAction = findViewById(R.id.getDataActionGames);
        recyclerViewAction.setHasFixedSize(true);

//For Action Games
        arrayListAction = new ArrayList<>();
        adapterAction = new AdapterActionGames(this, arrayListAction);


//For Action Games
        recyclerViewAction.setAdapter(adapterAction);
        recyclerViewAction.setLayoutManager(new LinearLayoutManager(this));


//For Action Games
        databaseReferenceAction.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ActionGamesClass model = dataSnapshot.getValue(ActionGamesClass.class);
                    arrayListAction.add(model);
                }

                progressDialog.dismiss();
                adapterAction.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
                Toast.makeText(GetDataActionGaames.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAction.startLayoutAnimation();

    }

    @Override
    protected void onStop() {

        super.onStop();
    }

}