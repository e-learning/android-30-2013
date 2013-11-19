package com.company;

import com.sun.javafx.geom.Vec2d;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 01.10.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class Square extends Rectangle {
    Square( Vec2d center, double size ) {
        super(center.x - size / 2, center.x + size / 2, center.y - size / 2, center.y + size / 2);
    }
}
