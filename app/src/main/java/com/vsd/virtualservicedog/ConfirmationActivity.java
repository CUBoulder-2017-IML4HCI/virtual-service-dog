package com.vsd.virtualservicedog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        String message = "You seem stressed. Are you feeling okay?";

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

        Bundle extras = getIntent().getExtras();
        String answer = extras.getString("HR");
        String shaking = extras.getString("Shaking");
        final double heartrate = Double.parseDouble(answer);
        final double shakiness = Double.parseDouble(shaking);

        Button noButton = (Button) findViewById(R.id.nobtn);
        Button yesButton = (Button) findViewById(R.id.yesbtn);
        // Set a click listener for the text view
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                PanicDetection panicDetection  = PanicDetection.getInstance(context);
                panicDetection.addToTraining(heartrate, shakiness, "yes");
                getBreath();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                PanicDetection panicDetection  = PanicDetection.getInstance(context);
                panicDetection.addToTraining(heartrate, shakiness, "no");
                getMainActivity();
            }
        });
    }

    private void getMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getBreath() {
        Intent intent = new Intent(this, BreathActivity.class);
        startActivity(intent);
    }

}
