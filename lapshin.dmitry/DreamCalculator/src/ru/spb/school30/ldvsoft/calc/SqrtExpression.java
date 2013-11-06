package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 05.11.13
 * Time: 14:14
 * Square root MathExpression
 */
public class SqrtExpression implements MathExpression
{
	MathExpression expression;

	public SqrtExpression(MathExpression aExpression)
	{
		expression = aExpression;
	}

	@Override
	public MathValue calculate()
	{
		MathValue exprValue = expression.calculate();
		if (exprValue.getRawValue() < 0.0)
			return null;
		return new DoubleValue(Math.sqrt(exprValue.getRawValue()));
	}

	@Override
	public String write()
	{
		return "\\sqrt(" + expression.write() + ")";
	}
}
