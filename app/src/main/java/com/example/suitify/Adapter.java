package com.example.suitify;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
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

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolderClass> {

    ArrayList<RacingGamesClass> lists;
    Context myContext;
    private PieChart pieChart;



    public Adapter(Context context, ArrayList<RacingGamesClass> lists) {
        this.lists = lists;
        this.myContext = context;
    }


    @NonNull
    @Override
    public viewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.items, parent, false);
        pieChart = view.findViewById(R.id.pieChartRacingGames);
        return new viewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderClass holder, int position) {
//String variables to represent the data for games
        String getMyGameName, getMyGameSize, getMyGameYear;


        //Reads the getter methods from Model class
        RacingGamesClass rcngGames = lists.get(position);

        holder.GameName.setText(rcngGames.getGameName());
        holder.GameSize.setText(rcngGames.getGameSize());
        holder.GamesYear.setText(rcngGames.getGameYear());

        //Linking the string variables
        getMyGameName = rcngGames.getGameName();
        getMyGameSize = rcngGames.getGameSize();
        getMyGameYear = rcngGames.getGameYear();


        //Pie chart
        //Pie Chart making

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Racing Game");
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

        entries.add(new PieEntry(0.41f, getMyGameName));
        entries.add(new PieEntry(0.30f, getMyGameSize));
        entries.add(new PieEntry(0.29f, getMyGameYear));


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
    public static class viewHolderClass extends RecyclerView.ViewHolder {

        TextView GameName, GameSize, GamesYear;

        public viewHolderClass(@NonNull View itemView) {

            super(itemView);

            //Accessing the ID of the text view
            GameName = itemView.findViewById(R.id.txtVwGameName);
            GameSize = itemView.findViewById(R.id.txtVwGameSize);
            GamesYear = itemView.findViewById(R.id.txtVwGameYear);


            //Links the variables for the war games


        }
    }


}
