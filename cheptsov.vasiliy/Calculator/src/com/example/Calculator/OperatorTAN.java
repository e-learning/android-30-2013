package com.example.Calculator;

// Operator TAN class
public class OperatorTAN extends Operator {
    // Default class constructor
    public OperatorTAN() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator function
    public String Print() {
        return "tan";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        return new Operand(Math.tan(Math.toRadians(a.GetValue())));
    }

    // Get operator TAN prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsTAN() {
        return true;
    }
}
