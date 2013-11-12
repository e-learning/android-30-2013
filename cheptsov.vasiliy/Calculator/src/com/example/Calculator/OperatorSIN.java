package com.example.Calculator;

// Operator SIN class
public class OperatorSIN extends Operator {
    // Default class constructor
    public OperatorSIN() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator SIN function
    public String Print() {
        return "sin";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        return new Operand(Math.sin(Math.toRadians(a.GetValue())));
    }


    // Get operator SIN prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsSIN() {
        return true;
    }
}
