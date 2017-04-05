package com.vsd.virtualservicedog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView heartrateTextView;

    Handler heartrateHandler = new Handler();
    Runnable heartrateRunnable = new Runnable() {

        @Override
        public void run() {
            Random rand = new Random();
            int counter = rand.nextInt((80 - 60) + 1) + 60;
            heartrateTextView.setText(String.format("Heart Rate: %d", counter));
            heartrateHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mybtn = (Button) findViewById(R.id.helpbtn);
        mybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getHelp();
            }
        });

        heartrateTextView = (TextView) findViewById(R.id.textView2);

        Button switchBtn = (Button) findViewById(R.id.switchbtn);
        switchBtn.setText("Monitor");
        switchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                heartRateGenerator(v);
            }
        });

    }

    /** Called when the user taps the Help button */
    public void getHelp() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        startActivity(intent);
    }

    public void heartRateGenerator(View v){
        Button switchBtn = (Button) v;
        if (switchBtn.getText().equals("Turn off")) {
            heartrateHandler.removeCallbacks(heartrateRunnable);
            switchBtn.setText("Monitor");
        } else {
            heartrateHandler.postDelayed(heartrateRunnable, 0);
            switchBtn.setText("Turn off");
        }
    }
}
