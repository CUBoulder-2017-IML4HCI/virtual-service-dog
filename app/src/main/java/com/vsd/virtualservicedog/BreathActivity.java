package com.vsd.virtualservicedog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BreathActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breath);

        Button mButton = (Button) findViewById(R.id.readybutton);

        // Set a click listener for the text view
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getNextStep();
            }
        });
    }

    private void getNextStep() {
        Intent intent = new Intent(this, HelpQ1Activity.class);
        startActivity(intent);
    }
}
