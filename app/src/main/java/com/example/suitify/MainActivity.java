package com.example.suitify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView pic1, pic2, pic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pic1 = findViewById(R.id.image1);
        pic2 = findViewById(R.id.image2);
        pic3 = findViewById(R.id.image3);


        final Intent openAuction = new Intent(this,Sign_In.class);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transition);
         pic1.startAnimation(myanim);
         pic2.startAnimation(myanim);
         pic3.startAnimation(myanim);

        Thread timer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(4000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(openAuction);
                    finish();
                }
            }

        };
        timer.start();

    }



}