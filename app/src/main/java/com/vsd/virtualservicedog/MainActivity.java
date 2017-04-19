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

import static com.vsd.virtualservicedog.R.id.switchbtn;

public class MainActivity extends AppCompatActivity {

    TextView heartrateTextView;
    TextView predictionText;
    PanicDetection panicDetection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mybtn = (Button) findViewById(R.id.helpbtn);
        mybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getBreath();
            }
        });

        Button insightbtn = (Button) findViewById(R.id.insight);
        insightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInsights();
            }
        });

        predictionText = (TextView) findViewById(R.id.prediction);
        heartrateTextView = (TextView) findViewById(R.id.hearrate);

        Button switchBtn = (Button) findViewById(switchbtn);
        switchBtn.setText("Monitor");
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecord(v);
            }
        });
        Context context = getApplicationContext();
        panicDetection  = PanicDetection.getInstance(context);

    }

    private void getInsights() {
        Intent intent = new Intent(this, GetInsights.class);
        startActivity(intent);
    }

    /** Called when the user taps the Help button */
    private void getBreath() {
        Intent intent = new Intent(this, BreathActivity.class);
        startActivity(intent);
    }

    public void getRecord(View v){
        Intent intent;
        Button switchBtn = (Button) v;
        if(switchBtn.getText().equals("Turn off")){
            switchBtn.setText("Monitor");
        }else{
            switchBtn.setText("Turn off");
            intent = new Intent(this, HeartRateMonitor.class);
            startActivity(intent);
        }
    }

}

