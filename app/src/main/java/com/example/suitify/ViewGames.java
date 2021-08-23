package com.example.suitify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewGames extends AppCompatActivity {
    TextView gameName, gameSize, gameYear, gameNameWar, gameSizeWar, gameYearWar, gameNameRacing, gameSizeRacing, gameYearRacing;


    Database sqLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_games);


        //Assign the variables
          /*
        gameName = findViewById(R.id.txtGameName);
        gameSize = findViewById(R.id.txtGameActionSize);
        gameYear = findViewById(R.id.txtGameActionYear);

        gameNameRacing = findViewById(R.id.txtGameNameRacing);
        gameSizeRacing = findViewById(R.id.txtGameSizeRacing);
        gameYearRacing = findViewById(R.id.txtGameSizeRacing);


        gameNameWar = findViewById(R.id.txtGameNameRacing);
        gameSizeWar = findViewById(R.id.txtGameSizeRacing);
        gameYearWar = findViewById(R.id.txtGameYearRacing);


        sqLiteOpenHelper = new Database(this);


        //When the activity is loaded it will automatically show the details of Action games
        try {
            Cursor getDetailsTable = sqLiteOpenHelper.getdataAction();
            if (getDetailsTable.getCount() == 0) {
                Toast.makeText(ViewGames.this, "There is no games yet", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (getDetailsTable.moveToNext()) {


                //Shows the data for action game on an text view
                gameName.setText(getDetailsTable.getString(1));
                gameSize.setText(getDetailsTable.getString(2));
                gameYear.setText(getDetailsTable.getString(3));


            }


        } catch (Exception es) {

            Toast.makeText(ViewGames.this, es.getMessage(), Toast.LENGTH_SHORT).show();
        }


        //Display War games
        try {
            Cursor getDetailsTable = sqLiteOpenHelper.getdataWarGames();
            if (getDetailsTable.getCount() == 0) {
                Toast.makeText(ViewGames.this, "There is no games yet", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (getDetailsTable.moveToNext()) {


                //Shows the data for action game on an text view
                gameNameWar.setText(getDetailsTable.getString(1));
                gameSizeWar.setText(getDetailsTable.getString(2));
                gameYearWar.setText(getDetailsTable.getString(3));


            }


        } catch (Exception es) {

            Toast.makeText(ViewGames.this, es.getMessage(), Toast.LENGTH_SHORT).show();
        }


        //Display racing games
        try {
            Cursor getDetailsTable = sqLiteOpenHelper.getdataRacingGames();
            if (getDetailsTable.getCount() == 0) {
                Toast.makeText(ViewGames.this, "There is no games yet", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (getDetailsTable.moveToNext()) {


                //Shows the data for action game on an text view
                gameNameRacing.setText(getDetailsTable.getString(1));
                gameSizeRacing.setText(getDetailsTable.getString(2));
                gameYearRacing.setText(getDetailsTable.getString(3));


            }

        } catch (Exception es) {

            Toast.makeText(ViewGames.this, es.getMessage(), Toast.LENGTH_SHORT).show();
        }


    */

    }


    public void viewMoreWarGames(View view) {

        //Opens the recycler view to view the games from Firebase realtime database
        Intent openWarGames = new Intent(ViewGames.this, GetDataWarGames.class);
        startActivity(openWarGames);

        /*
        try {
            Cursor getDetailsTable = sqLiteOpenHelper.getdataWarGames();
            if (getDetailsTable.getCount() == 0) {
                Toast.makeText(ViewGames.this, "There is no games yet", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (getDetailsTable.moveToNext()) {


                //Shows all the data for the action game on a dialog
                buffer.append("Game Name :" + getDetailsTable.getString(1) + "\n\n");
                buffer.append("Game Size :" + getDetailsTable.getString(2) + "\n\n");
                buffer.append("Game Year :" + getDetailsTable.getString(3) + "\n");


            }


            //Shows message on a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewGames.this);
            builder.setCancelable(true);
            builder.setTitle("My collection for War Games");
            builder.setMessage(buffer.toString());
            builder.show();
        } catch (Exception es) {

            Toast.makeText(ViewGames.this, es.getMessage(), Toast.LENGTH_SHORT).show();
        }

         */

    }


    public void viewMoreRacingGames(View view) {

        //Opens the recycler view to view the games from Firebase realtime database
        Intent openWindow = new Intent(ViewGames.this, GetData.class);
        startActivity(openWindow);

/*
        try {
            Cursor getDetailsTable = sqLiteOpenHelper.getdataRacingGames();
            if (getDetailsTable.getCount() == 0) {
                Toast.makeText(ViewGames.this, "There is no games yet", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (getDetailsTable.moveToNext()) {

                //Shows all the data for the action game on a dialog
                buffer.append("Game Name :" + getDetailsTable.getString(1) + "\n\n");
                buffer.append("Game Size :" + getDetailsTable.getString(2) + "\n\n");
                buffer.append("Game Year :" + getDetailsTable.getString(3) + "\n");


            }


            //Shows message on a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewGames.this);
            builder.setCancelable(true);
            builder.setTitle("My collection for Racing Games");
            builder.setMessage(buffer.toString());
            builder.show();
        } catch (Exception es) {

            Toast.makeText(ViewGames.this, es.getMessage(), Toast.LENGTH_SHORT).show();
        }
       */


    }


    public void viewMorectionGames(View view) {

        Intent openWindowActionGames = new Intent(ViewGames.this, GetDataActionGaames.class);
        startActivity(openWindowActionGames);
        /*
        try {
            Cursor getDetailsTable = sqLiteOpenHelper.getdataAction();
            if (getDetailsTable.getCount() == 0) {
                Toast.makeText(ViewGames.this, "There is no games yet", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (getDetailsTable.moveToNext()) {

                //Shows all the data for the action game on a dialog
                buffer.append("Game Name :" + getDetailsTable.getString(1) + "\n\n");
                buffer.append("Game Size :" + getDetailsTable.getString(2) + "\n\n");
                buffer.append("Game Year :" + getDetailsTable.getString(3) + "\n");


            }


            //Shows message on a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewGames.this);
            builder.setCancelable(true);
            builder.setTitle("My collection for Action Games");
            builder.setMessage(buffer.toString());
            builder.show();
        } catch (Exception es) {

            Toast.makeText(ViewGames.this, es.getMessage(), Toast.LENGTH_SHORT).show();
        }

         */
    }

    public void viewChartAction(View view) {

        Intent openWindowActionGames = new Intent(ViewGames.this, LoadChartAction.class);
        startActivity(openWindowActionGames);
    }

    public void viewChartWar(View view) {
        Intent open = new Intent(ViewGames.this, LoadWarGameNewItem.class);
        startActivity(open);
    }

    public void viewChartRacing(View view) {
        Intent open = new Intent(ViewGames.this, LoadRacingNewItem.class);
        startActivity(open);
    }
}