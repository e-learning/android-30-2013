package com.example.Calculator;

// Operator CloseSc class
public class OperatorCloseSc extends Operator {
    // Default class constructor
    public OperatorCloseSc() {
        super();
    }

    // Print operator function
    public String Print() {
        return ")";
    }

    // Get operator CloseSc prior function
    public int GetPrior() {
        return 1;
    }

    // Get operator ID function
    public boolean IsCloseSc() {
        return true;
    }
}
