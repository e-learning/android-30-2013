package com.example.DreamCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 05.11.13
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class Operator extends Token {
    public static class InvalidArguementException extends Exception {
        private String mDescr, mFName;

        public InvalidArguementException(String FnName, String Descr) {
            mDescr = Descr;
            mFName = FnName;
        }

        @Override
        public String toString() {
            return "Inalid arguement at function \"" + mFName + "\". It should be:" + mDescr;
        }
    }


    private int mPriority;
    private boolean mIsPrefix;
    private boolean mIsInfix;
    private int mNo;

    public Operator(int P, int no) {
        mPriority = P;
        mIsInfix = mIsPrefix = false;
        mNo = no;
    }

    public Operator(int P, boolean IsPrefix, int no) {
        mPriority = P;
        mIsInfix = !IsPrefix;
        mIsPrefix = IsPrefix;
        mNo = no;
    }

    // Returns true if this operator do nothing
    public boolean isLazy() {
        return !(mIsInfix || mIsPrefix);
    }

    public boolean isPrefix() {
        return mIsPrefix;
    }

    public boolean isInfix() {
        return mIsInfix;
    }

    @Override
    // Convert to string
    public String toString() {
        return "Base Operator";
    }

    public int priority() {
        return mPriority;
    }

    @Override
    public int getNo() {
        return mNo;
    }

    // Adding operator class
    private static class OperatorAdd extends Operator implements InfixOperator {
        public OperatorAdd() {
            super(0, false, 2);
        }

        @Override
        // Evaluate operator function
        public Operand Evaluate(Operand OperandA, Operand OperandB) throws InvalidArguementException {
            return new Operand(OperandA.getValue() + OperandB.getValue());
        }

        @Override
        // Convert to string
        public String toString() {
            return "+";
        }
    }

    // Open bracket operator class
    private static class OperatorOpBrack extends Operator {
        public OperatorOpBrack() {
            super(0, false, 0);
        }

        @Override
        public String toString() {
            return "(";
        }
    }

    // Close bracket operator class
    private static class OperatorClBrack extends Operator {
        public OperatorClBrack() {
            super(0, 1);
        }

        @Override
        public String toString() {
            return ")";
        }
    }

    // Product operator class
    private static class OperatorProd extends Operator implements InfixOperator {
        public OperatorProd() {
            super(1, false, 3);
        }

        @Override
        public Operand Evaluate(Operand A, Operand B) throws InvalidArguementException {
            return new Operand(A.getValue() * B.getValue());
        }

        @Override
        public String toString() {
            return "*";
        }
    }

    // Devide operator class
    private static class OperatorDev extends Operator implements InfixOperator {
        public OperatorDev() {
            super(1, false, 4);
        }

        @Override
        public Operand Evaluate(Operand A, Operand B) throws InvalidArguementException {
            return new Operand(A.getValue() / B.getValue());
        }

        @Override
        public String toString() {
            return "/";
        }
    }

    // Subscribe operator class
    private static class OperatorSub extends Operator implements InfixOperator {
        public OperatorSub() {
            super(1, false, 5);
        }

        @Override
        public Operand Evaluate(Operand A, Operand B) throws InvalidArguementException {
            return new Operand(A.getValue() - B.getValue());
        }

        @Override
        public String toString() {
            return "-";
        }
    }

    // Power operator class
    private static class OperatorPow extends Operator implements InfixOperator {
        public OperatorPow() {
            super(3, false, 6);
        }

        @Override
        public Operand Evaluate(Operand A, Operand B) throws InvalidArguementException {
            return new Operand(Math.pow(A.getValue(), B.getValue()));
        }

        @Override
        public String toString() {
            return "^";
        }
    }

    // Sin operator class
    private static class OperatorSin extends Operator implements PrefixOperator {
        public OperatorSin() {
            super(2, true, 7);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.sin(A.getValue()));
        }

        @Override
        public String toString() {
            return "sin ";
        }
    }

    private static class OperatorCos extends Operator implements PrefixOperator {
        public OperatorCos() {
            super(3, true, 8);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.cos(A.getValue()));
        }

        @Override
        public String toString() {
            return "cos ";
        }
    }

    private static class OperatorTan extends Operator implements PrefixOperator {
        public OperatorTan() {
            super(3, true, 9);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.tan(A.getValue()));
        }

        @Override
        public String toString() {
            return "tan ";
        }
    }

    private static class OperatorAsin extends Operator implements PrefixOperator {
        public OperatorAsin() {
            super(3, true, 10);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            double V = A.getValue();
            if (V < -1 || V > 1)
                throw new InvalidArguementException(this.toString(), "Should be from -1 to 1 (include)");
            return new Operand(Math.asin(V));
        }

        @Override
        public String toString() {
            return "asin ";
        }
    }

    private static class OperatorAcos extends Operator implements PrefixOperator {
        public OperatorAcos() {
            super(3, true, 11);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            double V = A.getValue();
            if (V < -1 || V > 1)
                throw new InvalidArguementException(this.toString(), "Should be from -1 to 1 (include)");
            return new Operand(Math.acos(V));
        }

        @Override
        public String toString() {
            return "arcsin ";
        }
    }

    private static class OperatorAtan extends Operator implements PrefixOperator {
        public OperatorAtan() {
            super(3, true, 12);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            double V = A.getValue();
            if (V < -1 || V > 1)
                throw new InvalidArguementException(this.toString(), "Should be from -1 to 1 (include)");
            return new Operand(Math.atan(V));
        }

        @Override
        public String toString() {
            return "arctan ";
        }
    }

    private static class OperatorAbs extends Operator implements PrefixOperator {
        public OperatorAbs() {
            super(3, true, 13);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.abs(A.getValue()));
        }

        @Override
        public String toString() {
            return "abs ";
        }
    }

    private static class OperatorFact extends Operator implements PrefixOperator {
        public OperatorFact() {
            super(3, true, 14);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            int max = (int) Math.floor(A.getValue());
            double res = 1;
            for (int i = 2; i <= max; i++)
                res *= i;
            return new Operand(res);
        }

        @Override
        public String toString() {
            return "fact ";
        }
    }

    private static class OperatorSqrt extends Operator implements PrefixOperator {
        public OperatorSqrt() {
            super(3, true, 14);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.sqrt(A.getValue()));
        }

        @Override
        public String toString() {
            return "sqrt ";
        }
    }

    private static class OperatorToRad extends Operator implements PrefixOperator {
        public OperatorToRad() {
            super(3, true, 14);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.toRadians(A.getValue()));
        }

        @Override
        public String toString() {
            return "to_rad ";
        }
    }

    private static class OperatorFloor extends Operator implements PrefixOperator {
        public OperatorFloor() {
            super(3, true, 14);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            return new Operand(Math.floor(A.getValue()));
        }

        @Override
        public String toString() {
            return "floor ";
        }
    }

    private static class OperatorLn extends Operator implements PrefixOperator {
        public OperatorLn() {
            super(3, true, 14);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            if (A.getValue() < 0)
                throw new InvalidArguementException(this.toString(), "Should be greater or equals than 0");
            return new Operand(Math.log(A.getValue()));
        }

        @Override
        public String toString() {
            return "ln ";
        }
    }

    private static class OperatorLog extends Operator implements PrefixOperator {
        public OperatorLog() {
            super(3, true, 14);
        }

        @Override
        public Operand Evaluate(Operand A) throws InvalidArguementException {
            if (A.getValue() < 0)
                throw new InvalidArguementException(this.toString(), "Should be greater or equals than 0");
            return new Operand(Math.log10(A.getValue()));
        }

        @Override
        public String toString() {
            return "log ";
        }
    }

    // Create operator from function
    public static Operator CreateFromString(String Str) {
        char Sym = Str.charAt(0);
        if (Sym == '+')
            return new OperatorAdd();
        else if (Sym == '-')
            return new OperatorSub();
        else if (Sym == '*')
            return new OperatorProd();
        else if (Sym == '/')
            return new OperatorDev();
        else if (Sym == '^')
            return new OperatorPow();
        else if (Sym == '(')
            return new OperatorOpBrack();
        else if (Sym == ')')
            return new OperatorClBrack();
        else if (Str.equals("cos"))
            return new OperatorCos();
        else if (Str.equals("sin"))
            return new OperatorSin();
        else if (Str.equals("tan"))
            return new OperatorTan();
        else if (Str.equals("arcsin"))
            return new OperatorAsin();
        else if (Str.equals("arccos"))
            return new OperatorAcos();
        else if (Str.equals("arctan"))
            return new OperatorAtan();
        else if (Str.equals("abs"))
            return new OperatorAbs();
        else if (Str.equals("fact"))
            return new OperatorFact();
        else if (Str.equals("to_rad"))
            return new OperatorToRad();
        else if (Str.equals("ln"))
            return new OperatorLn();
        else if (Str.equals("log"))
            return new OperatorLog();
        return new Operator(0, -1);
    }
}