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

public class AdapterActionGames extends RecyclerView.Adapter<AdapterActionGames.actionGamesHelper> {
    ArrayList<ActionGamesClass> lists;
    Context myContext;
    private PieChart chart;



    public AdapterActionGames(Context context, ArrayList<ActionGamesClass> lists) {
        this.lists = lists;
        this.myContext = context;
    }


    @NonNull
    @Override
    public actionGamesHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.itemsactiongames, parent, false);

        chart = view.findViewById(R.id.pieChartAction);
        return new actionGamesHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull actionGamesHelper holder, int position) {

        //Reads the getter methods from Model class
        ActionGamesClass actionGamesClass = new ActionGamesClass();
        actionGamesClass = lists.get(position);


        //String variables to represent the data for games
        String getMyGameName, getMyGameSize, getMyGameYear;

        holder.GameName.setText(actionGamesClass.getGameName());
        holder.GameSize.setText(actionGamesClass.getGameSize());
        holder.GamesYear.setText(actionGamesClass.getGameYear());


        getMyGameName = actionGamesClass.getGameName();
        getMyGameSize = actionGamesClass.getGameSize();
        getMyGameYear = actionGamesClass.getGameYear();


        //Pie Chart making
        chart.setDrawHoleEnabled(true);
        chart.setUsePercentValues(true);
        chart.setEntryLabelTextSize(12);
        chart.setEntryLabelColor(Color.BLACK);
        chart.setCenterText("Action Game");
        chart.setCenterTextSize(8);
        chart.getDescription().setEnabled(false);


        //Setting up the design of the chart
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setEnabled(true);


        //Handling the pie chart


        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(0.34f, getMyGameName));
        entries.add(new PieEntry(0.25f, getMyGameSize));
        entries.add(new PieEntry(0.41f, getMyGameYear));


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        chart.setData(data);
        chart.invalidate();

        chart.animateY(1400, Easing.EaseInOutQuad);


    }


    @Override
    public int getItemCount() {

        return lists.size();
    }

    //Creating inner class
    public static class actionGamesHelper extends RecyclerView.ViewHolder {

        TextView GameName, GameSize, GamesYear;

        public actionGamesHelper(@NonNull View itemView) {

            super(itemView);

            //Accessing the ID of the text view
            GameName = itemView.findViewById(R.id.txtVwGameName);
            GameSize = itemView.findViewById(R.id.txtVwGameSize);
            GamesYear = itemView.findViewById(R.id.txtVwGameYear);


            //Links the variables for the war games


        }
    }


}
