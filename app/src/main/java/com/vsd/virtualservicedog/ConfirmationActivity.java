package com.vsd.virtualservicedog;

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
        String message = "You seems stressed. Are you doing okay?";

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

        Button mButton = (Button) findViewById(R.id.nobtn);
        Button yesButton = (Button) findViewById(R.id.yesbtn);
        // Set a click listener for the text view
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getBreath();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
