package com.vsd.virtualservicedog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class BreathActivity extends AppCompatActivity {
    int circleHeight = 100;
    int circleWidth = 100;

    // created so that the animation will repeat
    Handler h = new Handler();
    int delay = 10000;

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

        ShapeDrawable sd = new ShapeDrawable(new OvalShape());
        sd.setIntrinsicHeight(circleHeight);
        sd.setIntrinsicWidth(circleWidth);
        sd.getPaint().setColor(Color.parseColor("#c090ff"));

        final ImageView circleImage = (ImageView) findViewById(R.id.circle);
        circleImage.setBackground(sd);

        final AppCompatActivity activity = this;

        Animation scale = AnimationUtils.loadAnimation(activity, R.anim.breathe_circle);
        circleImage.startAnimation(scale);

        h.postDelayed(new Runnable(){
            public void run(){
                Animation scale = AnimationUtils.loadAnimation(activity, R.anim.breathe_circle);
                circleImage.startAnimation(scale);
                h.postDelayed(this, delay);
            }
        }, delay);
    }

    private void getNextStep() {
        Intent intent = new Intent(this, HelpQ1Activity.class);
        startActivity(intent);
    }
}
