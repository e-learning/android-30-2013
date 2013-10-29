package com.company;

import com.sun.javafx.geom.Vec2d;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 01.10.13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class Triangle extends Shape {
    private Vec2d v1, v2, v3;

    Triangle( Vec2d vertex1, Vec2d vertex2, Vec2d vertex3 ) {
        v1 = vertex1;
        v2 = vertex2;
        v3 = vertex3;
        super.Set(GetCenter());
    }

    public double GetArea() {
        double a, b, c, p;
        a = Math.sqrt((v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y));
        b = Math.sqrt((v1.x - v3.x) * (v1.x - v3.x) + (v1.y - v3.y) * (v1.y - v3.y));
        c = Math.sqrt((v3.x - v2.x) * (v3.x - v2.x) + (v3.y - v2.y) * (v3.y - v2.y));
        p = (a + b + c) / 2;

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public Vec2d GetCenter() {
        return new Vec2d((v1.x + v2.x + v3.x) / 3,  (v1.y + v2.y + v3.y) / 3);
    }
}
