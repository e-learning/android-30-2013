package com.example.Calculator;

// Operator div class
public class OperatorDiv extends Operator {
    // Default class constructor
    public OperatorDiv() {
        super(OPERATOR_TYPE.TWO_OPERAND);
    }

    // Print operator div function
    public String Print() {
        return "/";
    }

    // Evaluation function
    public Operand Eval(Operand a, Operand b) {
        if (b.GetValue() == 0)
            throw new ArithmeticException();
        return new Operand(a.GetValue() / b.GetValue());
    }

    // Get operator prior function
    public int GetPrior() {
        return 3;
    }

    // Get operator ID function
    public boolean IsDiv() {
        return true;
    }
}
