package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:24
 * Base class for values of expressions. This type only have double value.
 */
public class MathValue
{
	double rawValue;
	public MathValue(double aRawValue)
	{
		rawValue = aRawValue;
	}

	//Returns raw value of expression
	public double getRawValue()
	{
		return rawValue;
	}
}
