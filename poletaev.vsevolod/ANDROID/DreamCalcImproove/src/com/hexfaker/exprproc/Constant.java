package com.hexfaker.exprproc;

/**
 * Programmer: Poletaev Vsevolod @ CGSG'12
 * Created: 09.11.13
 * Description: Constant operand class
 */
public class Constant extends Token implements Operand {
    private double mVal;

    // Constructor
    public Constant(Double Val) {
        super(Val.toString());
        mVal = Val;
    }

    @Override
    // Get value method implementation
    public double getValue() {
        return mVal;
    }
}
