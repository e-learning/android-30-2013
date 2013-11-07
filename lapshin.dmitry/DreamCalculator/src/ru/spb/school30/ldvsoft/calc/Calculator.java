package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 29.10.13
 * Time: 19:28
 * Class that calculates everything
 */
public class Calculator
{
	public static enum Mode
	{
		REAL
	}
	private static Mode mode = Mode.REAL;

	public static Mode getMode()
	{
		return mode;
	}

	public static MathValue calculate(String userInput)
	{
		MathExpression expression;
		MathValue value;
		try
		{
			expression = MathExpressionBuilder.buildExpression(userInput);
		}
		catch (SyntaxErrorException e)
		{
			return null;
		}
		try
		{
			value = expression.calculate();
		}
		catch (Exception e)
		{
			return null;
		}
		return value;
	}
}
