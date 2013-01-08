package com.larvalabs.svgandroid.tree;

public class SVGLine extends SVGPath {
    public SVGLine(String id) {
        super(id);
    }

    public void setLine(float x1, float y1, float x2, float y2){
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
    }
}
