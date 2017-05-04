package com.vsd.virtualservicedog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by shadybug on 5/4/17.
 */

class Resources extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources);

        final TextView quiet = (TextView) findViewById(R.id.quiet);
        final TextView playerTwo = (TextView) findViewById(R.id.playerTwo);
        final TextView blah = (TextView) findViewById(R.id.blah);
        final TextView thoughts = (TextView) findViewById(R.id.thoughts);
        final TextView noise = (TextView) findViewById(R.id.noise);
        final TextView cute = (TextView) findViewById(R.id.cute);

        quiet.setText(Html.fromHtml("<a href=\"http://thequietplaceproject.com/thequietplace\">The Quiet Place</a>"));
        quiet.setMovementMethod(LinkMovementMethod.getInstance());
        playerTwo.setText(Html.fromHtml("<a href=\"www.lifeinneon.com/games/Player2.html\">Conflict Management</a>"));
        playerTwo.setMovementMethod(LinkMovementMethod.getInstance());
        blah.setText(Html.fromHtml("<a href=\"http://blahtherapy.com\">Talk to Someone</a>"));
        blah.setMovementMethod(LinkMovementMethod.getInstance());
        thoughts.setText(Html.fromHtml("<a href=\"http://thequietplaceproject.com/thethoughtsroom/\">Write it Down</a>"));
        thoughts.setMovementMethod(LinkMovementMethod.getInstance());
        noise.setText(Html.fromHtml("<a href=\"https://mynoise.net\">Ambient Noise</a>"));
        noise.setMovementMethod(LinkMovementMethod.getInstance());
        cute.setText(Html.fromHtml("<a href=\"http://gruntle.me\">Cute Animals</a>"));
        cute.setMovementMethod(LinkMovementMethod.getInstance());

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
