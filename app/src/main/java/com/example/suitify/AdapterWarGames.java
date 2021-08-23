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

public class AdapterWarGames extends RecyclerView.Adapter<AdapterWarGames.warGamesHelper> {
    ArrayList<WarGamesClass> lists;
    Context myContext;
    private PieChart pieChart;

    public AdapterWarGames(Context context, ArrayList<WarGamesClass> lists) {
        this.lists = lists;
        this.myContext = context;
    }


    @NonNull
    @Override
    public warGamesHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.itemswargames, parent, false);
        pieChart = view.findViewById(R.id.pieChartWarGames);
        return new warGamesHelper(view);
    }



    @Override
    public void onBindViewHolder(@NonNull warGamesHelper holder, int position) {


        //String variables to represent the data for games
        String getMyGameName, getMyGameSize, getMyGameYear;


        //Reads the getter methods from Model class
        WarGamesClass warGames = new WarGamesClass();
        warGames = lists.get(position);

        holder.GameName.setText(warGames.getGameName());
        holder.GameSize.setText(warGames.getGameSize());
        holder.GamesYear.setText(warGames.getGameYear());


        //Linking the string variables
        getMyGameName = warGames.getGameName();
        getMyGameSize = warGames.getGameSize();
        getMyGameYear = warGames.getGameYear();


        //Pie chart
        //Pie Chart making

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("War Game");
        pieChart.setCenterTextSize(8);
        pieChart.getDescription().setEnabled(false);


        //Setting up the design of the chart
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setEnabled(true);


        //Handling the pie chart


        ArrayList<PieEntry> entries = new ArrayList<>();


        entries.add(new PieEntry(0.42f, getMyGameName));
        entries.add(new PieEntry(0.25f, getMyGameSize));
        entries.add(new PieEntry(0.33f, getMyGameYear));


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Category");
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);

    }


    @Override
    public int getItemCount() {

        return lists.size();
    }

    //Creating inner class
    public static class warGamesHelper extends RecyclerView.ViewHolder {

        TextView GameName, GameSize, GamesYear;

        public warGamesHelper(@NonNull View itemView) {

            super(itemView);

            //Accessing the ID of the text view
            GameName = itemView.findViewById(R.id.txtVwGameName);
            GameSize = itemView.findViewById(R.id.txtVwGameSize);
            GamesYear = itemView.findViewById(R.id.txtVwGameYear);


            //Links the variables for the war games


        }
    }


}
