package com.hexfaker.calc;

/**
 * Programmer: Poletaev Vsevolod @ CGSG'12
 * Created: 08.11.13
 * Description: Base expression evaluator exception class.
 */
public class ExprEvaluatorException extends Exception {
    private final String mDebugStr, mUserStr;

    public ExprEvaluatorException(String DebugStr, String UserStr) {
        mDebugStr = DebugStr;
        mUserStr = UserStr;
    }

    public final String userString() {
        return mUserStr;
    }

    @Override
    public final String toString() {
        return mDebugStr;
    }
}
