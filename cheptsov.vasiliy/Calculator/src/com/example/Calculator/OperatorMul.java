package com.example.Calculator;

// Operator class
public class OperatorMul extends Operator {
    // Default class constructor
    public OperatorMul() {
        super(OPERATOR_TYPE.TWO_OPERAND);
    }

    // Print operator mul function
    public String Print() {
        return "x";
    }

    // Evaluation function
    public Operand Eval(Operand a, Operand b) {
        return new Operand(a.GetValue() * b.GetValue());
    }

    // Get operator mul prior function
    public int GetPrior() {
        return 3;
    }

    // Get operator ID function
    public boolean IsMul() {
        return true;
    }
}
