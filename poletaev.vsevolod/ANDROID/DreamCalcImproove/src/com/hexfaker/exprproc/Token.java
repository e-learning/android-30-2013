package com.hexfaker.exprproc;

/**
 * Programmer: Poletaev Vsevolod @ CGSG'12
 * Created: 09.11.13
 * Description: Base class for all tokens
 */
public class Token {
    private String mVision;

    // Constructor
    public Token(String Vision) {
        mVision = Vision;
    }

    @Override
    public final String toString() {
        return mVision;
    }
}
