package com.larvalabs.svgandroid.tree;

import android.graphics.Canvas;
import android.graphics.RectF;

public class SVGEllipse extends Node {
    private RectF rect;

    public SVGEllipse(String id) {
        super(id);
    }

    public void setEllipse(RectF rect){
        this.rect = rect;
    }

    @Override
    public void draw(Canvas canvas, boolean forceVisibility){
        if(!isVisible() && !forceVisibility) return;

        canvas.save();
        if(matrix != null) canvas.concat(matrix);

        if(fillPaint != null) canvas.drawOval(rect, fillPaint);
        if(strokePaint != null) canvas.drawOval(rect, strokePaint);

        canvas.restore();
    }

    @Override
    public RectF measure() {
        bounds = new RectF(rect);
        return bounds;
    }
}
