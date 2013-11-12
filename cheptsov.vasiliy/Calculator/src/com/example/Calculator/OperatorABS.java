package com.example.Calculator;

// Operator ABS class
public class OperatorABS extends Operator {
    // Default class constructor
    public OperatorABS() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator function
    public String Print() {
        return "abs";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        return new Operand(Math.abs(a.GetValue()));
    }

    // Get operator ABS prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsABS() {
        return true;
    }
}
