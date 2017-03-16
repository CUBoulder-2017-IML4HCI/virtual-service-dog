package com.vsd.virtualservicedog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "This is a test";
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
    }

    /** Called when the user taps the Help button */
    public void getHelp() {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
}
