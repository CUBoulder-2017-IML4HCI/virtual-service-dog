package com.vsd.virtualservicedog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class DoneActivity extends ActionBarActivity {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    public GraphicalView graphicalView;
    static XYSeries series = new XYSeries("heart rate");
    Button restart;
    double total=0;
    double heartrate;
    double shaking;

    //sets all of the options for the renderer
    private XYMultipleSeriesRenderer getDemoRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(HeartRateMonitor.CHART_AXIS_TITLE_SIZE);
        renderer.setChartTitleTextSize(HeartRateMonitor.CHART_TITLE_SIZE);
        renderer.setLabelsTextSize(HeartRateMonitor.CHART_LABELS_TEXT_SIZE);
        renderer.setLegendTextSize(HeartRateMonitor.CHART_LEGEND_TEXT_SIZE);
        renderer.setPointSize(HeartRateMonitor.CHART_POINT_SIZE);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setMargins(new int[]{20, 20, 15, 0});
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.RED);
        r.setPointStyle(PointStyle.SQUARE);
        r.setFillBelowLine(true);
        r.setFillBelowLineColor(Color.WHITE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);
        return renderer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donelayout);
        int i = 0;
        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartTest();
            }
        });
        TextView tv = (TextView) findViewById(R.id.tv);
        Bundle b = getIntent().getExtras().getBundle("rates");

        double[] rates = b.getDoubleArray("ratesbundle");
        for (double r : rates) {
            series.add(i, r);
            i++;
            total+=r;
        }
        tv.setText("Average: " + (int)(total / i));

        Bundle xb = getIntent().getExtras().getBundle("X");
        float x = xb.getFloat("DeltaBundle");

        TextView xDelta = (TextView) findViewById(R.id.sensor);
        xDelta.setText("X: "+ String.valueOf(x));

        dataset.addSeries(series);
        graphicalView = ChartFactory.getScatterChartView(this, dataset, getDemoRenderer());
        LinearLayout layout = (LinearLayout) findViewById(R.id.done_graphview);
        layout.addView(graphicalView);

        PanicDetection panicDetection;
        Context context = getApplicationContext();
        panicDetection  = PanicDetection.getInstance(context);
        shaking = x;
        heartrate = (double)(int)(total/i);
        double[] values = new double[]{heartrate, x};
        Instance instance = new DenseInstance(values);
        Object result = panicDetection.classifydata(instance);
        if(result.toString().equals("yes")) {
            getHelp();
        }
    }

    private void restartTest() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), HeartRateMonitor.class);
        startActivity(intent);
        this.finish();
    }

    public void getHelp() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        String msg = String.valueOf(heartrate);
        String shake = String.valueOf(shaking);
        intent.putExtra("HR", msg);
        intent.putExtra("Shaking", shake);
        startActivity(intent);
    }

}

