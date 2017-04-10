package com.vsd.virtualservicedog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView heartrateTextView;
    TextView predictionText;
    PanicDetection panicDetection;
    Handler heartrateHandler = new Handler();
    int currentheartrate = 0;

    Runnable heartrateRunnable = new Runnable() {
        @Override
        public void run() {
            Random rand = new Random();
            int counter = rand.nextInt((90 - 60) + 1) + 60;
            heartrateTextView.setText(String.format("Heart Rate: %d", counter));
            heartrateHandler.postDelayed(this, 2000);
            currentheartrate = counter;

            double[] values = new double[]{(double)currentheartrate};
            Instance instance = new DenseInstance(values);
            Object result = panicDetection.classifydata(instance);
            if(result.toString().equals("yes")) {
                getHelp();
                heartrateHandler.removeCallbacks(heartrateRunnable);
                Button s = (Button) findViewById(R.id.switchbtn);
                s.setText("MONITOR");
            } else{
                predictionText.setText(result.toString());
            }
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

        predictionText = (TextView) findViewById(R.id.prediction);
        heartrateTextView = (TextView) findViewById(R.id.hearrate);

        Button switchBtn = (Button) findViewById(R.id.switchbtn);
        switchBtn.setText("Monitor");
        switchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                heartRateGenerator(v);
            }
        });
        Context context = getApplicationContext();
        panicDetection  = PanicDetection.getInstance(context);

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
