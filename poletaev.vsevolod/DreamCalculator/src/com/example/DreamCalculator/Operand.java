package com.example.DreamCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 05.11.13
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class Operand extends Token {
    private double mVal;  // Numeric value

    // Explicit constructor
    public Operand(double Value) {
        super(true);
        mVal = Value;
    }

    // Explicit constructor
    public Operand() {
        super(true);
        mVal = 0;
    }

    // Get operand value
    public double getValue() {
        return mVal;
    }

    @Override
    // Convert operand to string
    public String toString() {
        return "" + mVal;
    }
}
