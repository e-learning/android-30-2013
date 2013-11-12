package com.example.Calculator;

// Operator add class
public class OperatorAdd extends Operator {
    // Default class constructor
    public OperatorAdd() {
        super(OPERATOR_TYPE.TWO_OPERAND);
    }

    // Print operator add function
    public String Print() {
        return "+";
    }

    // Evaluation function
    public Operand Eval(Operand a, Operand b) {
        return new Operand(a.GetValue() + b.GetValue());
    }

    // Get operator add prior function
    public int GetPrior() {
        return 2;
    }

    // Get operator ID function
    public boolean IsAdd() {
        return true;
    }

}
