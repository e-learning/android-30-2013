package com.example.DreamCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 06.11.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public interface InfixOperator {
    // Evaluation function
    public Operand Evaluate(Operand A, Operand B) throws Operator.InvalidArguementException;
}
