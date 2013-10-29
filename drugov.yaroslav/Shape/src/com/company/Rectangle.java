package com.company;

import com.sun.javafx.geom.Vec2d;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 01.10.13
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class Rectangle extends Shape{
    private double l, r, b, t;

    Rectangle( double left, double right, double bottom, double top ) {
        l = left;
        r = right;
        b = bottom;
        t = top;
        super.Set(GetCenter());
    }

    public double GetArea() {
        return Math.abs((r - l) * (t - b));
    }

    public Vec2d GetCenter() {
        return new Vec2d((l + r) / 2,  (t + b) / 2);
    }

}
