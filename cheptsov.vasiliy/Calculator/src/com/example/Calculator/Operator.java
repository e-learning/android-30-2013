package com.example.Calculator;

public class Operator extends BaseToken {
    // Type operator
    public OPERATOR_TYPE OperatorType;

    // Default class constructor
    public Operator() {
        super(TOKEN_ID.TOKEN_OPERATOR);
        OperatorType = OPERATOR_TYPE.ONE_OPERAND;
    }

    // User class constructor
    public Operator(OPERATOR_TYPE Type) {
        super(TOKEN_ID.TOKEN_OPERATOR);
        OperatorType = Type;
    }

    // Print operator function
    public String Print() {
        return "Unknown operator";
    }

    // Evaluation function
    public Operand Eval(Operand a, Operand b) throws ArithmeticException {
        return new Operand(1);
    }

    // Evaluation function
    public Operand Eval(Operand a) throws  ArithmeticException {
        return new Operand(1);
    }

    // Get operator mul prior function
    public int GetPrior() {
        return -1;
    }

    // Get operators ID functions
    public boolean IsDiv() {
        return false;
    }
    public boolean IsMul() {
        return false;
    }
    public boolean IsSub() {
        return false;
    }
    public boolean IsAdd() {
        return false;
    }
    public boolean IsABS() {
        return false;
    }
    public boolean IsSIN () {
        return false;
    }
    public boolean IsCOS() {
        return false;
    }
    public boolean IsTAN() {
        return false;
    }
    public boolean IsCAT() {
        return false;
    }
    public boolean IsFact() {
        return false;
    }
    public boolean IsSQRT() {
        return false;
    }
    public boolean IsOpenSc() {
        return false;
    }
    public boolean IsCloseSc() {
        return false;
    }
}
