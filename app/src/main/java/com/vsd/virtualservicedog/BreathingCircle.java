package com.vsd.virtualservicedog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by shadybug on 4/11/17.
 */

public class BreathingCircle extends View {

    // CONSTRUCTOR
    public BreathingCircle(Context context) {
        super(context);
        setFocusable(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.CYAN);
        Paint p = new Paint();
        // smooths
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(4.5f);
        // opacity
        //p.setAlpha(0x80); //
        canvas.drawCircle(50, 50, 30, p);
    }

}