package com.hexfaker.calc;

/**
 * Programmer: Poletaev Vsevolod @ CGSG'12
 * Created: 08.11.13
 * Description: Base token class
 */
public class Token {
    static final int UID_UNINITILISED = 0;  // Unitialised UID indicator

    private double mUID;    // Universal ID
    private String mVision; // String which is printed at toString() method

    // Default constructor
    public Token() {
        mUID = UID_UNINITILISED;
        mVision = "UninitilisedToken";
    }

    // Constructor
    public Token(double UID, String Vision) {
        mUID = UID;
        mVision = Vision;
    }

    public double getUID() throws ExprEvaluatorException {
        if (mUID == UID_UNINITILISED)
            throw new ExprEvaluatorException("Unitialised Token", "Unitillised token");
        return mUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Token)
            if (((Token) o).mUID == mUID)
                return true;
        return false;
    }

    @Override
    public final String toString() {
        return mVision;
    }
}
