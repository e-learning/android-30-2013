package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:59
 * The very basic expression - just a number
 * This number is hosted as MathValue
 */
public class NumberExpression implements MathExpression
{
	private MathValue value;

	public NumberExpression(MathValue aValue)
	{
		value = aValue;
	}

	@Override
	public MathValue calculate()
	{
		return value;
	}

	@Override
	public String write()
	{
		return value.toString();
	}
}
