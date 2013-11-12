package com.example.Calculator;

import java.util.Vector;

public class BaseTokQueue {
    // Queue data
    protected Vector<BaseToken> Q;
    // Pointer to queue head
    protected int Head;

    // Default class constructor
    public BaseTokQueue() {
        Head = 0;
        Q = new Vector<BaseToken>();
    }

    // Copy class constructor
    public BaseTokQueue(BaseTokQueue NewQueue) {
        Q = new Vector<BaseToken>(NewQueue.Q);
        Head = NewQueue.Head;
    }

    // Check is queue empty function
    public boolean empty() {
        return (Head >= Q.size());
    }

    // Put new element to queue function
    public boolean Put(BaseToken Tok) {
        return Q.add(Tok);
    }

    // Get element from queue head function
    public BaseToken Get() {
        return Q.get(Head++);
    }

    // Clear queue function
    public void Clear() {
        Head = 0;
        Q.clear();
    }

    // Set all variables current value
    public void SetAllVariables(double NewValue) {
        if (Q.isEmpty())
            return;
        Vector<BaseToken> NewQ = new Vector<BaseToken>();
        BaseToken Tok;
        for (int i = 0; i < Q.size(); i++) {
            Tok = Q.get(i);
            if (Tok.GetTokenId() == TOKEN_ID.TOKEN_OPERAND && ((Operand)Tok).OperandType == OPERAND_TYPE.VARIABLE)
                ((Operand)Tok).SetValue(NewValue);
            NewQ.add(Tok);
        }
        Q = NewQ;
    }
}
