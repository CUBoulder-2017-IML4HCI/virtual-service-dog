package com.vsd.virtualservicedog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpAdvice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_advice);

        Bundle extras = getIntent().getExtras();
        String answer = extras.getString("Answer");
        final TextView textView = (TextView) findViewById(R.id.adviceText);
        assert answer != null;
        switch (answer) {
            case "1N":
                textView.setText("Q1: No Advice");
                break;
            case "2N":
                textView.setText("Q2: No Advice");
                break;
            case "2Y":
                textView.setText("Q2: Yes Advice");
                break;
        }


        Button okBtn = (Button) findViewById(R.id.okButton);
        okBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getMainActivity();
            }
        });

    }

    private void getMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
