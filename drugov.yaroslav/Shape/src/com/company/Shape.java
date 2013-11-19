package com.company;

import com.sun.javafx.geom.Vec2d;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 01.10.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class Shape {
    private Vec2d c;

    public void Set( Vec2d center ) {
        c = center;
    }

    public double GetArea() {
        return 0;
    }

    public Vec2d GetCenter() {
        return c;
    }

    public void PrintArea() {
        System.out.println("Area = " + GetArea());
    }

    public void PrintCenter() {
        System.out.println("Center position y = " + c.y);
        System.out.println("Center position x = " + c.x);
    }
}
