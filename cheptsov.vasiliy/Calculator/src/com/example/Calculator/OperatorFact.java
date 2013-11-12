package com.example.Calculator;

// Operator Fact class
public class OperatorFact extends Operator {
    // Default class constructor
    public OperatorFact() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator Fact function
    public String Print() {
        return "!";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        if (a.GetValue() <= 0)
            return new Operand(0);

        double res = 1;
        for (int i = 1; i <= a.GetValue(); i++)
            res *= i;

        return new Operand(res);
    }

    // Get operator Fact prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsFact() {
        return true;
    }
}
