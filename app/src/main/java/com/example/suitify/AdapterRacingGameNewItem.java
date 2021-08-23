package com.example.suitify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRacingGameNewItem extends RecyclerView.Adapter<AdapterRacingGameNewItem.gamesHelper> {
    ArrayList<RacingGameNewItem> lists;
    Context myContext;


    public AdapterRacingGameNewItem(Context context, ArrayList<RacingGameNewItem> lists) {
        this.lists = lists;
        this.myContext = context;
    }


    @NonNull
    @Override
    public gamesHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.chartracinggames, parent, false);


        return new gamesHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gamesHelper holder, int position) {

        //Reads the getter methods from Model class
        RacingGameNewItem racingGameNewItem = new RacingGameNewItem();
        racingGameNewItem = lists.get(position);

        holder.name.setText(racingGameNewItem.getName());
        holder.category.setText(racingGameNewItem.getCategory());
        holder.gamesaved.setText(racingGameNewItem.getGamesaved());


    }


    @Override
    public int getItemCount() {

        return lists.size();
    }

    //Creating inner class
    public static class gamesHelper extends RecyclerView.ViewHolder {

        TextView category, name, gamesaved;

        public gamesHelper(@NonNull View itemView) {

            super(itemView);

            //Accessing the ID of the text view
            category = itemView.findViewById(R.id.txtRacingGameCategory);
            name = itemView.findViewById(R.id.newGameNameRacing);
            gamesaved = itemView.findViewById(R.id.txtNumber);

        }
    }


}
