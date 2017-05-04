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
                textView.setText("That's okay! It happens to the best of us. Remember to keep breathing - in through the nose, and out through the mouth. When you're ready, do something nice for yourself! Maybe make some tea or interact with a pet if you have one. You might also try doing some stretches or writing or typing things down.");
                break;
            case "2N":
                textView.setText("That's okay! Sometimes we know we can't affect the outcomes, but we still worry anyways. The best we can do is try and take care of ourselves. Try doing something to take your mind off of things! Maybe try a puzzle or read a book. Sometimes crafts can help too, even if you're not much of an artist! Try not to focus on the outcome, and instead pay attention to the process. Sometimes we just need to take our minds off of things for a little while.");
                break;
            case "2Y":
                textView.setText("If it's something you can do in small steps, I want you to take ten minutes to work on fixing the problem. Even that may seem daunting, but I know you can make it! Maybe in a little bit you can do another ten minutes, and before you know it you'll be done. If it's something big, work your way up to it. Practice in front of a mirror, write out what you need to do, or do a little Googling. It's important to remember that even if you can't fix things, there's no reason to beat yourself up! Sometimes we overestimate ourselves, we forget, or perhaps something else gets in the way. No matter the reason, mistakes are what make us individuals. You've got this!");
                break;
        }


        Button okBtn = (Button) findViewById(R.id.okButton);
        okBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getResource();
            }
        });

    }

    private void getResource() {
        Intent intent = new Intent(this, Resources.class);
        startActivity(intent);
    }
}
