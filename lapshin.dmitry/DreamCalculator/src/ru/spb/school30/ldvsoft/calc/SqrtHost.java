package ru.spb.school30.ldvsoft.calc;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 05.11.13
 * Time: 14:14
 * Square root MathExpression
 */

public class SqrtHost implements FunctionHost
{
	@Override
	public int getPriority()
	{
		return 1;
	}

	@Override
	public MathExpression buildFunction(Vector<MathExpression> params) throws SyntaxErrorException
	{
		if (params.size() != 1)
			throw new SyntaxErrorException("Square root can have only 1 parameter.");
		if (Calculator.getMode() == Calculator.Mode.REAL)
			return new SqrtExpression(params.get(0));
		throw new SyntaxErrorException("Square root has bad parameter.");
	}
}

class SqrtExpression implements MathExpression
{
	MathExpression expression;

	public SqrtExpression(MathExpression aExpression)
	{
		expression = aExpression;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		MathValue exprValue = expression.calculate();
		if (exprValue.getRawValue() < 0.0)
			throw new MathException("Can't take square root of negative number.");
		return new DoubleValue(Math.sqrt(exprValue.getRawValue()));
	}

	@Override
	public String write()
	{
		return "\\sqrt(" + expression.write() + ")";
	}
}
