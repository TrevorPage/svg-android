package com.larvalabs.svgandroid.tree;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;

public class SVGPath extends Node {
    protected Path path;
    protected PathMeasure pathMeasure;

    public SVGPath(String id){
        super(id);
        path = new Path();
    }

    public SVGPath(){
        this(null);
    }

    public void setPath(Path path){
        this.path = path;
    }

    public Path getPath(){
        return path;
    }

    @Override
    public void draw(Canvas canvas, boolean forceVisibility){
        if(!isVisible() && !forceVisibility) return;

        canvas.save();

        if(matrix != null) canvas.concat(matrix);
        if(fillPaint != null) canvas.drawPath(path, fillPaint);
        if(strokePaint != null) canvas.drawPath(path, strokePaint);

        canvas.restore();
    }

    @Override
    public RectF measure() {
        bounds = new RectF();
        path.computeBounds(bounds, true);
        return bounds;
    }

    private PathMeasure getPathMeasure(){
        if(pathMeasure == null)
            pathMeasure = new PathMeasure(path, false);
        return pathMeasure;
    }

    public float[] getStartingPoint(){
        float[] coordinates = {0f, 0f};
        getPathMeasure().getPosTan(0, coordinates, null);
        return coordinates;
    }

    public float[] getEndingPoint(){
        float[] coordinates = {0f, 0f};
        PathMeasure pm = getPathMeasure();
        pm.getPosTan(pm.getLength(), coordinates, null);
        return coordinates;
    }

    public float getLength(){
        return getPathMeasure().getLength();
    }
}
