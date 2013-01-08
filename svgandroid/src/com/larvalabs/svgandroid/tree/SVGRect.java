package com.larvalabs.svgandroid.tree;

import android.graphics.Canvas;
import android.graphics.RectF;

public class SVGRect extends Node {
    private float x;
    private float y;
    private float width;
    private float height;

    public SVGRect(String id) {
        super(id);
    }

    public void setRect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, boolean forceVisibility){
        if(!isVisible() && !forceVisibility) return;

        canvas.save();
        if(matrix != null) canvas.concat(matrix);

        if(fillPaint != null) canvas.drawRect(x, y, x + width, y + height, fillPaint);
        if(strokePaint != null) canvas.drawRect(x, y, x + width, y + height, strokePaint);

        canvas.restore();
    }

    @Override
    public RectF measure() {
        bounds = new RectF();
        bounds.set(x, y, x+width, y+height);
        return bounds;
    }
}
