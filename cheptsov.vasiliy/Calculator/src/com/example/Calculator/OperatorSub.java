package com.example.Calculator;

// Operator sub class
public class OperatorSub extends Operator {
    // Default class constructor
    public OperatorSub() {
        super(OPERATOR_TYPE.TWO_OPERAND);
    }

    // Print operator sub function
    public String Print() {
        return "-";
    }

    // Evaluation function
    public Operand Eval(Operand a, Operand b) {
        return new Operand(a.GetValue() - b.GetValue());
    }

    // Get operator sub prior function
    public int GetPrior() {
        return 2;
    }

    // Get operator ID function
    public boolean IsSub() {
        return true;
    }
}
