package com.company;

import com.sun.javafx.geom.Vec2d;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 01.10.13
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class Circle extends Shape{
    private Vec2d c;
    private double r;

    Circle( Vec2d center, double  radius) {
        c = center;
        r = radius;
        super.Set(GetCenter());
    }

    public double GetArea() {
        return Math.PI * r * r;
    }

    public Vec2d GetCenter() {
        return c;
    }

}
