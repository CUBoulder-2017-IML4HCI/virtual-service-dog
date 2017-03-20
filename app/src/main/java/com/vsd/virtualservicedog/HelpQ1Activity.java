package com.vsd.virtualservicedog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpQ1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_question);
        Button yesBtn = (Button) findViewById(R.id.yesButton);
        Button noBtn = (Button) findViewById(R.id.noButton);
        final TextView textView = (TextView) findViewById(R.id.questionText);
        textView.setText("Do you know what is troubling you?");
        yesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getYes();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getNo();
            }
        });

    }

    private void getYes() {
        Intent intent = new Intent(this, HelpQ2Activity.class);
        startActivity(intent);
    }

    private void getNo() {
        Intent intent = new Intent(this, HelpAdvice.class);
        String msg = "1N";
        intent.putExtra("Answer", msg);
        startActivity(intent);
    }
}
