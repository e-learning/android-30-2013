package com.example.DreamCalculator;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 05.11.13
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */
public class ExprEvaluator {
    public class SyntaxErrorException extends Exception {
        private String mReason;

        public SyntaxErrorException(String Reason) {
            mReason = Reason;
        }

        @Override
        public String toString() {
            return "Syntax error: " + mReason;
        }
    }

    private Vector<String> mExpr; // Vector of strings of expressions
    private boolean mIsPreviousDigit; // Is previous symbol digit flag
    private List<Token> mTokL;

    public ExprEvaluator() {
        mExpr = new Vector<String>();
        mIsPreviousDigit = false;
        mTokL = new ArrayList<Token>();
    }

    // Add symbol function
    public void addSymbol(String Str) {
        boolean IsDigit = Character.isDigit(Str.charAt(0)) || Str.charAt(0) == '.';
        if (IsDigit && mIsPreviousDigit) {
            mExpr.set(mExpr.size() - 1, mExpr.lastElement() + Str);
        } else
            mExpr.add(Str);
        mIsPreviousDigit = IsDigit;
    }

    // Delete last symbol
    public void deleteLast() {
        if (!mExpr.isEmpty()) {
            if (mIsPreviousDigit) {
                mExpr.set(mExpr.size() - 1, mExpr.lastElement().subSequence(0, mExpr.lastElement().length() - 1).toString());
                if (mExpr.get(mExpr.size() - 1).isEmpty()) {
                    mExpr.remove(mExpr.size() - 1);
                    if (mExpr.isEmpty())
                        mIsPreviousDigit = false;
                }
            } else
                mExpr.remove(mExpr.size() - 1);
        }
    }

    // Convert to string function
    public String toString() {
        String Res = "";

        for (int i = 0; i < mExpr.size(); i++)
            Res += mExpr.get(i);

        return Res;
    }

    // Evaluate result fuction
    public double doEvaluation() throws SyntaxErrorException, Operator.InvalidArguementException {
        scanString();
        Operand Res = evaluateSubList(mTokL);
        return Res.getValue();
    }

    // Evaluate result fuction
    public double doVarEvaluation(double Value) throws SyntaxErrorException, Operator.InvalidArguementException {
        Operand V = new Operand(Value);
        scanString();
        for (int i = 0; i < mTokL.size(); i++)
            if (mTokL.get(i) instanceof Variable)
                mTokL.set(i, V);

        Operand Res = evaluateSubList(mTokL);
        return Res.getValue();
    }

    // Reset all data function
    public void reset() {
        mExpr.removeAllElements();
        mTokL.clear();
        mIsPreviousDigit = false;
    }

    private void scanString() {
        for (int i = 0; i < mExpr.size(); i++)
            mTokL.add(Token.CreateFromString(mExpr.get((i))));

    }

    private int findEldestOperator(List<Token> L) {
        int res = -1, p, maxp = -1;
        Token Op;
        for (int i = 0; i < L.size(); i++) {
            if ((Op = L.get(i)).isOperator())
                if ((p = ((Operator) Op).priority()) > maxp) {
                    maxp = p;
                    res = i;
                }
        }
        return res;
    }

    private Operand evaluateSubList(List<Token> L) throws SyntaxErrorException, Operator.InvalidArguementException {
        int nOp;
        Operand Result;
        Token A, B;
        Token Op = Operator.CreateFromString("("), Cl = Operator.CreateFromString(")");
        int start = -1, end = -1, i = 0, level = 0, j;
        Operator Oper;

        do {
            for (; i < L.size(); i++)
                if (L.get(i).getNo() == Op.getNo()) {
                    start = i;
                    level++;
                    break;
                }

            for (i++; i < L.size() && level > 0; i++)
                if (L.get(i).getNo() == Op.getNo())
                    level++;
                else if (L.get(i).getNo() == Cl.getNo()) {
                    level--;
                    end = i;
                }

            if (start > end || level > 0)
                throw new SyntaxErrorException("Error with brackets");
            else if (start != -1 || end != -1) {
                Result = evaluateSubList(L.subList(start + 1, end));
                j = 0;
                L.remove(start);
                L.remove(start + 1);
            } else
                break;
            start = end = -1;
        } while (true);

        while ((nOp = findEldestOperator(L)) >= 0) {
            Oper = (Operator) L.get(nOp);

            if (Oper.isInfix())
                if (nOp > 0 && nOp + 1 < L.size()) {
                    A = L.get(nOp - 1);
                    B = L.get(nOp + 1);
                    if (!A.isOperand() || !B.isOperand())
                        throw new SyntaxErrorException("");
                    Result = ((InfixOperator) Oper).Evaluate((Operand) A, (Operand) B);
                    i = 0;
                    while (i++ < 3)
                        L.remove(nOp - 1);
                    L.add(nOp - 1, Result);
                } else
                    throw new SyntaxErrorException("Unexpected operator \"" + Op.toString() + "\"");
            else if (Oper.isPrefix())
                if (nOp + 1 < L.size()) {
                    A = L.get(nOp + 1);
                    if (!A.isOperand())
                        throw new SyntaxErrorException("");

                    Result = ((PrefixOperator) Oper).Evaluate((Operand) A);

                    i = 0;
                    while (i++ < 2)
                        L.remove(nOp);
                    L.add(nOp, Result);
                }
        }
        return (Operand) L.get(0);
    }
}
