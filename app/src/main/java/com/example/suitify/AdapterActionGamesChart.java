package com.example.suitify;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AdapterActionGamesChart extends RecyclerView.Adapter<AdapterActionGamesChart.actionGamesHelper> {
    ArrayList<ActionGamesClassChart> lists;
    Context myContext;
    private PieChart chart;


    public AdapterActionGamesChart(Context context, ArrayList<ActionGamesClassChart> lists) {
        this.lists = lists;
        this.myContext = context;
    }


    @NonNull
    @Override
    public actionGamesHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.chartactiongames, parent, false);


        return new actionGamesHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull actionGamesHelper holder, int position) {

        //Reads the getter methods from Model class
        ActionGamesClassChart actionGamesClass = new ActionGamesClassChart();
        actionGamesClass = lists.get(position);


        //String variables to represent the data for games
        String getMyGameName, getMyGameCategory, getMyGameNumber;

        holder.name.setText(actionGamesClass.getName());
        holder.category.setText(actionGamesClass.getCategory());
        holder.gamesaved.setText(actionGamesClass.getGamesaved());



    }


    @Override
    public int getItemCount() {

        return lists.size();
    }

    //Creating inner class
    public static class actionGamesHelper extends RecyclerView.ViewHolder {

        TextView category, name, gamesaved;

        public actionGamesHelper(@NonNull View itemView) {

            super(itemView);

            //Accessing the ID of the text view
            category = itemView.findViewById(R.id.txtActionGameCategory);
            name = itemView.findViewById(R.id.newGameNameAction);
            gamesaved = itemView.findViewById(R.id.txtActionWishList);

        }
    }


}
