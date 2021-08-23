package com.example.suitify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterWarGameNewItem extends RecyclerView.Adapter<AdapterWarGameNewItem.gamesHelper> {
    ArrayList<WarGameNewItem> lists;
    Context myContext;


    public AdapterWarGameNewItem(Context context, ArrayList<WarGameNewItem> lists) {
        this.lists = lists;
        this.myContext = context;
    }


    @NonNull
    @Override
    public gamesHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.chartwargames, parent, false);


        return new gamesHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gamesHelper holder, int position) {

        //Reads the getter methods from Model class
        WarGameNewItem warGameNewItem = new WarGameNewItem();
        warGameNewItem = lists.get(position);
        
        holder.name.setText(warGameNewItem.getName());
        holder.category.setText(warGameNewItem.getCategory());
        holder.gamesaved.setText(warGameNewItem.getGamesaved());


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
            category = itemView.findViewById(R.id.txtWarGameCategory);
            name = itemView.findViewById(R.id.newGameNameWar);
            gamesaved = itemView.findViewById(R.id.txtWarGameWishList);

        }
    }


}
