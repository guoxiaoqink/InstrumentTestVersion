package com.example.tu4.utils;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import static com.prolificinteractive.materialcalendarview.spans.DotSpan.DEFAULT_RADIUS;

/**
 * Span to draw a dot centered under a section of text
 */
public class CalendarSpan implements LineBackgroundSpan {

    /**
     * Default radius used
     */
//    public static final float DEFAULT_RADIUS = 3;

    private final float radius;
    private final int color;

    /**
     * Create a span to draw a dot using default radius and color
     *
     * @see #CalendarSpan(float, int)
     * @see #
     */
    public CalendarSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color = 0;
    }

    /**
     * Create a span to draw a dot using a specified color
     *
     * @param color color of the dot
     * @see #CalendarSpan(float, int)
     * @see #
     */
    public CalendarSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color = color;
    }

    /**
     * Create a span to draw a dot using a specified radius
     *
     * @param radius radius for the dot
     * @see #CalendarSpan(float, int)
     */
    public CalendarSpan(float radius) {
        this.radius = radius;
        this.color = 0;
    }

    /**
     * Create a span to draw a dot using a specified radius and color
     *
     * @param radius radius for the dot
     * @param color  color of the dot
     */
    public CalendarSpan(float radius, int color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        //canvas.drawCircle((left + right) / 2, bottom + radius, radius, paint);
        canvas.drawLine((left + right)/3,bottom,(left + right)/3*2,bottom,paint);
        canvas.drawLine((left + right)/3+1,bottom,(left + right)/3*2+1,bottom,paint);
        canvas.drawLine((left + right)/3+2,bottom,(left + right)/3*2+2,bottom,paint);
        canvas.drawLine((left + right)/3+3,bottom,(left + right)/3*2+3,bottom,paint);
        paint.setColor(oldColor);
    }
}