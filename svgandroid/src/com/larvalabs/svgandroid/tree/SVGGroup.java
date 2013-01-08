package com.larvalabs.svgandroid.tree;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

public class SVGGroup extends Node {
    private ArrayList<Node> children;

    public SVGGroup(){
        super();
        children = new ArrayList<Node>();
    }

    public SVGGroup(String id){
        super(id);
        children = new ArrayList<Node>();
    }

    public void addChild(Node child){
        child.setParent(this);
        children.add(child);
    }

    public ArrayList<Node> getChildren(){
        return children;
    }

    @Override
    public Node find(String query){
        if(super.find(query) != null) return this;

        for(Node node : children){
            Node result = node.find(query);
            if(result != null){
                return result;
            }
        }

        return null;
    }

    public ArrayList<Node> findAllStartingWith(String query){
        ArrayList<Node> results = new ArrayList<Node>();

        if(id != null && id.startsWith(query))
            results.add(this);

        for(Node node : children){
            results.addAll(node.findAllStartingWith(query));
        }

        return results;
    }

    @Override
    public void draw(Canvas canvas, boolean forceVisibility){
        if(!isVisible() && !forceVisibility) return;

        for(Node child : getChildren()){
            child.draw(canvas, forceVisibility);
        }
    }

    @Override
    public RectF measure() {
        bounds = new RectF();

        for(Node child : getChildren()){
            bounds.union(child.measure());
        }

        return bounds;
    }
}
