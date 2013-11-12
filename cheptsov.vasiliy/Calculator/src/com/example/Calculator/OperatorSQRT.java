package com.example.Calculator;

// Operator SQRT class
public class OperatorSQRT extends Operator {
    // Default class constructor
    public OperatorSQRT() {
        super(OPERATOR_TYPE.ONE_OPERAND);
    }

    // Print operator SQRT function
    public String Print() {
        return "sqrt";
    }

    // Evaluation function
    public Operand Eval(Operand a) {
        return new Operand(Math.sqrt(a.GetValue()));
    }

    // Get operator SQRT prior function
    public int GetPrior() {
        return 4;
    }

    // Get operator ID function
    public boolean IsSQRT() {
        return true;
    }
}
