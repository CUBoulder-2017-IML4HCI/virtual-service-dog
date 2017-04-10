package com.vsd.virtualservicedog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.vsd.virtualservicedog.R.id.switchbtn;

public class MainActivity extends AppCompatActivity {

    TextView heartrateTextView;
    TextView predictionText;
    PanicDetection panicDetection = new PanicDetection();
    Handler heartrateHandler = new Handler();
    int currentheartrate = 0;

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

        Button switchBtn = (Button) findViewById(switchbtn);
        switchBtn.setText("Monitor");
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecord(v);
            }
        });

        heartrateTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double[] values = new double[]{(double)currentheartrate};
                Instance instance = new DenseInstance(values);
                Object result = panicDetection.classifydata(instance);
                predictionText.setText(result.toString());
                //predictionText.setText("on");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /** Called when the user taps the Help button */
    public void getHelp() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
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
