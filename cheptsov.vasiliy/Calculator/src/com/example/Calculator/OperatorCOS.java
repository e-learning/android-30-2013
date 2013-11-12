package com.example.Calculator;

// Operator COS class
public class OperatorCOS extends Operator {
    // Default class constructor
    public OperatorCOS() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator COS function
    public String Print() {
        return "cos";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        return new Operand(Math.cos(Math.toRadians(a.GetValue())));
    }

    // Get operator COS prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsCOS() {
        return true;
    }
}
