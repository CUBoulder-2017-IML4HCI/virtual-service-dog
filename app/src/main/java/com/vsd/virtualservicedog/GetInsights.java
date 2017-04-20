package com.vsd.virtualservicedog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetInsights extends AppCompatActivity {
    private String FILE_NAME = "virtual-service-dog-data-test.txt";
    TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_insights);

        data = (TextView)findViewById(R.id.data);
        data.setText("");
        try {
            FileInputStream is = getApplicationContext().openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                data.append(line+"\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
