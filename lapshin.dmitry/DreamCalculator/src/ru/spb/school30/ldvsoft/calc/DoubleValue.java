package ru.spb.school30.ldvsoft.calc;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:24
 * Base class for values of expressions. This type only have double value.
 */
public class DoubleValue extends NumberValue
{
	public static final double EPS = 1E-7;

	private static DecimalFormat format = new DecimalFormat("0.########E0");

	double value;
	public DoubleValue(double aRawValue)
	{
		value = aRawValue;
	}

	@Override
	public double getRawValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return format.format(value);
	}
}
