package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:59
 * The very basic expression - just a number
 */
public class NumberExpression implements MathExpression
{
	private double number;

	public NumberExpression(double aNumber)
	{
		number = aNumber;
	}

	@Override
	public MathValue calculate()
	{
		return new MathValue(number);
	}

	@Override
	public String write()
	{
		return Double.toString(number);
	}
}
