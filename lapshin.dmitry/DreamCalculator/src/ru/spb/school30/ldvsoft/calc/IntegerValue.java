package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 06.11.13
 * Time: 14:52
 * Integer MathValue
 */
public class IntegerValue implements MathValue
{
	public static final int MAX_VALUE = 10000000;

	private int value;

	public IntegerValue(int aValue)
	{
		value = aValue;
	}

	@Override
	public double getRawValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return Integer.toString(value);
	}
}
