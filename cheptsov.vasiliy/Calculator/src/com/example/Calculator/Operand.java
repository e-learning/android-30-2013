package com.example.Calculator;

public class Operand extends BaseToken {
    // Operand type
    public OPERAND_TYPE OperandType;

    // Operand value
    private double Value;

    // Default class constructor
    public Operand() {
        super(TOKEN_ID.TOKEN_OPERAND);
        OperandType = OPERAND_TYPE.VALUE;
        Value = 0;
    }

    // User class constructor for double value
    public Operand(double NewValue) {
        super(TOKEN_ID.TOKEN_OPERAND);
        OperandType = OPERAND_TYPE.VALUE;
        Value = NewValue;
    }

    // User class constructor for variable
    public Operand(OPERAND_TYPE NewType) {
        super(TOKEN_ID.TOKEN_OPERAND);
        OperandType = OPERAND_TYPE.VARIABLE;
        Value = 0;
    }

    // Get operand value function
    public double GetValue() {
        return Value;
    }

    // Set operand value function
    public Operand SetValue(double NewValue) {
        Value = NewValue;
        return this;
    }

    // Print operand function
    public String Print() {
        return "" + Value;
    }
}
