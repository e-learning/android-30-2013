package com.example.Calculator;

// Operator CAT class
public class OperatorCAT extends Operator {
    // Default class constructor
    public OperatorCAT() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator function
    public String Print() {
        return "cat";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        return new Operand(1 / Math.tan(Math.toRadians(a.GetValue())));
    }

    // Get operator CAT prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsCAT() {
        return true;
    }
}
