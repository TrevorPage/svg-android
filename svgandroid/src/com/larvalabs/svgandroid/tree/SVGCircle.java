package com.larvalabs.svgandroid.tree;

import android.graphics.Canvas;
import android.graphics.RectF;

public class SVGCircle extends Node {
    private float cx;
    private float cy;
    private float r;

    public SVGCircle(String id) {
        super(id);
    }

    public void setCircle(float cx, float cy, float r){
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public void draw(Canvas canvas, boolean forceVisibility){
        if(!isVisible() && !forceVisibility) return;

        canvas.save();
        if(matrix != null) canvas.concat(matrix);

        if(fillPaint != null) canvas.drawCircle(cx, cy, r, fillPaint);
        if(strokePaint != null) canvas.drawCircle(cx, cy, r, strokePaint);

        canvas.restore();
    }

    @Override
    public RectF measure() {
        bounds = new RectF();
        bounds.set(cx-r, cy-r, cx+r, cy+r);
        return bounds;
    }
}
