package com.example.DreamCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 06.11.13
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public interface PrefixOperator {
    public Operand Evaluate(Operand O) throws Operator.InvalidArguementException;
}
