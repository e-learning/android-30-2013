package com.example.DreamCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 05.11.13
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
public class Token {
    boolean mIsOperand;

    // Constructor
    Token(boolean IsOperand) {
        mIsOperand = IsOperand;
    }

    // Default constructor
    Token() {
        mIsOperand = false;
    }

    @Override
    // Convert to string
    public String toString() {
        return "'Base Token'";
    }

    public boolean isOperator() {
        return !mIsOperand;
    }

    public int getNo() {
        return -2;
    }

    public boolean isOperand() {
        return mIsOperand;
    }

    // Create token from string function
    public static Token CreateFromString(String Str) {
        if (Character.isDigit(Str.charAt(0)))
            return new Operand(Double.parseDouble(Str));
        else if (Str.equals("Pi"))
            return new Operand(Math.PI);
        else if (Str.equals("e"))
            return new Operand(Math.E);
        else if (Str.equals("x"))
            return new Variable();
        else
            return Operator.CreateFromString(Str);
    }
}
