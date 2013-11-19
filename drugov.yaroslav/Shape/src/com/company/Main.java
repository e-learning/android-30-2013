package com.company;

import com.sun.javafx.geom.Vec2d;

public class Main {

    public static void main(String[] args) {
        Triangle tr = new Triangle(new Vec2d(0, 0), new Vec2d(1, 0), new Vec2d(0, 1));
        tr.PrintArea();
        tr.PrintCenter();
        Square sq = new Square(new Vec2d(0, 0), 1);
        sq.PrintArea();
        sq.PrintCenter();
    }
}
