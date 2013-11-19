package com.example.Calculator;

// Operator OpenSc class
public class OperatorOpenSc extends Operator {
    // Default class constructor
    public OperatorOpenSc() {
        super();
    }

    // Print operator OpenSc function
    public String Print() {
        return "(";
    }

    // Get operator OpenSc prior function
    public int GetPrior() {
        return 0;
    }

    // Get operator ID function
    public boolean IsOpenSc() {
        return true;
    }
}
