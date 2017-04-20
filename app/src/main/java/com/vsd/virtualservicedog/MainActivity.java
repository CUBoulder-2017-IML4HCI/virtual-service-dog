package com.vsd.virtualservicedog;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.util.Random;

import static com.vsd.virtualservicedog.R.id.switchbtn;

public class MainActivity extends AppCompatActivity {
    final private int MY_PERMISSIONS_REQUEST_CAMERA = 123;
    final private int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 124;

    TextView heartrateTextView;
    TextView predictionText;
    PanicDetection panicDetection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mybtn = (Button) findViewById(R.id.helpbtn);
        mybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getBreath();
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
        Context context = getApplicationContext();
        panicDetection  = PanicDetection.getInstance(context);

        // asks for camera/file permission if we don't already have any
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            callPermission();
        }
    }

    /** Called when the user taps the Help button */
    private void getBreath() {
        Intent intent = new Intent(this, BreathActivity.class);
        startActivity(intent);
    }

    public void getRecord(View v){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            callPermission();
        }
        else {
            Intent intent;
            Button switchBtn = (Button) v;
            if (switchBtn.getText().equals("Turn off")) {
                switchBtn.setText("Monitor");
            } else {
                switchBtn.setText("Turn off");
                intent = new Intent(this, HeartRateMonitor.class);
                startActivity(intent);
            }
        }
    }

    // there's probably a neater way to do all the boilerplate here but
    // I didn't want to spend too long refining things when it works
    public void callPermission() {
        // shows an explanation if they've previously refused permission
        // then tries again
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA) ||
            ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionsDialog pd = new PermissionsDialog();
            pd.setActivity(this);
            pd.show(getFragmentManager(), "dialog");

        } else {
            // the actual request
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

}

