package com.larvalabs.svgandroid.tree;

import android.graphics.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Node {
    protected String id;
    protected SVGGroup parent;
    protected Paint fillPaint;
    protected Paint strokePaint;
    protected Matrix matrix;
    protected boolean visible;
    protected RectF bounds;

    public Node(){ }

    public Node(String id){
        this.id = id;
    }

    public boolean isRoot(){
        return parent != null;
    }

    public SVGGroup getParent(){
        return parent;
    }

    public void setParent(SVGGroup parent){
        this.parent = parent;
    }

    public Node find(String query){
        if(id != null && id.equals(query)) return this;
        else return null;
    }

    public ArrayList<Node> findAllStartingWith(String query){
        ArrayList<Node> result = new ArrayList<Node>();

        if(id != null && id.startsWith(query))
            result.add(this);

        return result;
    }

    /* Getters */

    public String getId(){
        return id;
    }

    public Paint getFillPaint(){
        return fillPaint;
    }

    public Paint getStrokePaint(){
        return strokePaint;
    }

    public Matrix getMatrix(){
        return matrix;
    }

    public boolean isVisible(){
        return visible;
    }

    /* Setters */

    public void setId(String id){
        this.id = id;
    }

    public void setMatrix(Matrix matrix){
        this.matrix = matrix;
    }

    public void setFillPaint(Paint fillPaint){
        this.fillPaint = fillPaint;
    }

    public void setStrokePaint(Paint strokePaint){
        this.strokePaint = strokePaint;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }

    public RectF getBounds() {
        if(bounds == null)
            measure();

        return bounds;
    }

    public abstract void draw(Canvas canvas, boolean forceVisibility);
    public abstract RectF measure();

    /* Picture Generation
     * These methods allow to generate a picture object from any node of the DOM Tree
     */
    public Picture createPicture() {
        return createPicture(false, this);
    }

    public Picture createPicture(boolean forceVisibility, String... nodeNames) {
        return createPicture(forceVisibility, Arrays.asList(nodeNames));
    }

    public Picture createPicture(boolean forceVisibility, List<String> nodeNames) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(String nodeName : nodeNames){
            Node node = find(nodeName);
            if(node != null){
                nodes.add(node);
            }
        }

        return createPicture(forceVisibility, nodes);
    }

    public Picture createPicture(boolean forceVisibility, Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        return createPicture(forceVisibility, nodes);
    }

    public Picture createPicture(boolean forceVisibility, ArrayList<Node> nodes) {
        bounds = null; //force measure. is this really necessary?

        Picture picture = new Picture();
        Canvas canvas = picture.beginRecording((int) Math.ceil(getBounds().width()), (int) Math.ceil(getBounds().height()));

        for(Node node : nodes)
            node.draw(canvas, forceVisibility);

        picture.endRecording();
        return picture;
    }
}
