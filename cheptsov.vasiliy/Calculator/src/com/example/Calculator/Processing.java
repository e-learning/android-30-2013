package com.example.Calculator;

import java.util.Stack;
import java.util.Vector;

// Processing calculator class
public class Processing {
    // Source tokens queue, made in Scanner
    private BaseTokQueue SrcQueue;
    // Destination tokens queue, made in Parser
    private BaseTokQueue DstQueue;
    // Parser operands stack
    private Stack<BaseToken> OperandsStack;
    // Parser operators stack;
    private Stack<BaseToken> OperatorsStack;

    // Default class constructor
    public Processing() {
        SrcQueue = new BaseTokQueue();
        DstQueue = new BaseTokQueue();
        OperandsStack = new Stack<BaseToken>();
        OperatorsStack = new Stack<BaseToken>();
    }

    // Scanner calculator function
    public boolean Scanner(Vector<String> Str) {
        if (Str.isEmpty())
            return false;
        for (int i = 0; i < Str.size(); i++)
            if (Str.get(i).equals("x")) {
                if (!SrcQueue.Put(new Operand(OPERAND_TYPE.VARIABLE)))
                    return false;
            } else if (Str.get(i).equals("/")) {
                if (!SrcQueue.Put(new OperatorDiv()))
                    return false;
            } else if (Str.get(i).equals("*")) {
                if (!SrcQueue.Put(new OperatorMul()))
                    return false;
            } else if (Str.get(i).equals("-")) {
                if (!SrcQueue.Put(new OperatorSub()))
                    return false;
            } else if (Str.get(i).equals("+")) {
                if (!SrcQueue.Put(new OperatorAdd()))
                    return false;
            } else if (Str.get(i).equals("+/-")) {
                if (!SrcQueue.Put(new Operand(-1)) || !SrcQueue.Put(new OperatorMul()))
                    return false;
            } else if (Str.get(i).equals("abs")) {
                if (!SrcQueue.Put(new OperatorABS()))
                    return false;
            } else if (Str.get(i).equals("sin")) {
                if (!SrcQueue.Put(new OperatorSIN()))
                    return false;
            } else if (Str.get(i).equals("cos")) {
                if (!SrcQueue.Put(new OperatorCOS()))
                    return false;
            } else if (Str.get(i).equals("tan")) {
                if (!SrcQueue.Put(new OperatorTAN()))
                    return false;
            } else if (Str.get(i).equals("cat")) {
                if (!SrcQueue.Put(new OperatorCAT()))
                    return false;
            } else if (Str.get(i).equals("fact")) {
                if (!SrcQueue.Put(new OperatorFact()))
                    return false;
            } else if (Str.get(i).equals("sqrt")) {
                if (!SrcQueue.Put(new OperatorSQRT()))
                    return false;
            } else if (Str.get(i).equals("(")) {
                if (!SrcQueue.Put(new OperatorOpenSc()))
                    return false;
            } else if (Str.get(i).equals(")")) {
                if (!SrcQueue.Put(new OperatorCloseSc()))
                    return false;
            } else {
                double res = 0;
                double denum = 1;
                boolean WasPoint = false;

                while (i < Str.size() && Str.get(i).length() == 1) {
                    if (Str.get(i).equals("."))
                        WasPoint = true;
                    else if (Character.isDigit(Str.get(i).charAt(0))){
                        if (!WasPoint) {
                            res *= 10;
                            res += Str.get(i).toCharArray()[0] - '0';
                        } else
                            res += (Str.get(i).toCharArray()[0] - '0') / (denum *= 10);
                    }
                    else {
                        i--;
                        break;
                    }
                    i++;
                }
                if (!SrcQueue.Put(new Operand(res)))
                    return false;
            }
        return true;
    }

    // Drop operators function
    private void DropOperators(int Prior) {
        while (!OperatorsStack.empty() && ((Operator)OperatorsStack.peek()).GetPrior() >= Prior)
            OperandsStack.push(OperatorsStack.pop());
    }

    // Parser calculator function
    public boolean Parser() {
        PARSER_STATE state = PARSER_STATE.STATE_WAIT_PREFIX;
        BaseToken Tok = new BaseToken();
        boolean ret = true;

        while (state != PARSER_STATE.STATE_END) {
            if (state == PARSER_STATE.STATE_WAIT_PREFIX || state == PARSER_STATE.STATE_WAIT_SUFFIX)
                if (SrcQueue.empty())
                    if (state == PARSER_STATE.STATE_WAIT_SUFFIX)
                        state = PARSER_STATE.STATE_DONE;
                    else
                        state = PARSER_STATE.STATE_ERROR;
                else
                    Tok = SrcQueue.Get();
            switch (state) {
                case STATE_WAIT_PREFIX:
                    if (Tok.GetTokenId() == TOKEN_ID.TOKEN_OPERAND) {
                        OperandsStack.push(Tok);
                        state = PARSER_STATE.STATE_WAIT_SUFFIX;
                    } else if (((Operator)Tok).IsOpenSc())
                        OperatorsStack.push(Tok);
                    else if (((Operator)Tok).OperatorType == OPERATOR_TYPE.ONE_OPERAND)
                        OperatorsStack.push(Tok);
                    else
                        state = PARSER_STATE.STATE_ERROR;
                    break;
                case STATE_WAIT_SUFFIX:
                    if (Tok.GetTokenId() == TOKEN_ID.TOKEN_OPERATOR && !((Operator)Tok).IsCloseSc()) {
                        DropOperators(((Operator)Tok).GetPrior());
                        state = PARSER_STATE.STATE_WAIT_PREFIX;
                        OperatorsStack.push(Tok);
                    } else if (Tok.GetTokenId() == TOKEN_ID.TOKEN_OPERATOR && ((Operator)Tok).IsCloseSc()) {
                        DropOperators(((Operator)Tok).GetPrior());
                        if (OperatorsStack.empty())
                            state = PARSER_STATE.STATE_ERROR;
                        else if (!((Tok = OperatorsStack.pop()).GetTokenId() == TOKEN_ID.TOKEN_OPERATOR && ((Operator)Tok).IsOpenSc()))
                            state = PARSER_STATE.STATE_ERROR;
                        else if (SrcQueue.empty())
                            state = PARSER_STATE.STATE_DONE;
                    } else
                        state = PARSER_STATE.STATE_ERROR;
                    break;
                case STATE_DONE:
                    DropOperators(-1);
                    if (OperatorsStack.empty()) {
                        while (!OperandsStack.empty())
                            OperatorsStack.push(OperandsStack.pop());
                        while (!OperatorsStack.empty())
                            DstQueue.Put(OperatorsStack.pop());
                        state = PARSER_STATE.STATE_END;
                    } else
                        state = PARSER_STATE.STATE_ERROR;
                    break;
                case STATE_ERROR:
                    OperatorsStack.clear();
                    OperandsStack.clear();
                    DstQueue.Clear();
                    SrcQueue.Clear();
                    ret = false;
                    state = PARSER_STATE.STATE_END;
                    break;
            }
        }
        return ret;
    }

    // Evaluator calculator function
    public double Evaluator() {
        Stack<BaseToken> S = new Stack<BaseToken>();
        BaseTokQueue Q = new BaseTokQueue(DstQueue);
        BaseToken Tok;
        double x;

        while (!Q.empty()) {
            Tok = Q.Get();
            if (Tok.GetTokenId() == TOKEN_ID.TOKEN_OPERAND)
                S.push(Tok);
            else if (((Operator)Tok).OperatorType == OPERATOR_TYPE.ONE_OPERAND)
                S.push(((Operator)Tok).Eval((Operand)(S.pop())));
            else {
                BaseToken b = S.pop();
                S.push(((Operator)Tok).Eval((Operand)(S.pop()), (Operand)b));
            }
        }

        SrcQueue.Clear();
        Q.Clear();
        OperandsStack.clear();
        OperatorsStack.clear();

        return ((Operand)S.pop()).GetValue();
    }

    // Reset class function
    public void Reset() {
        SrcQueue.Clear();
        DstQueue.Clear();
        OperandsStack.clear();
        OperatorsStack.clear();
    }

    // Set all variables current value
    public void SetAllVariables(double NewValue) {
        DstQueue.SetAllVariables(NewValue);
    }
}
