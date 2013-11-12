package ru.spb.school30.ldvsoft.calc;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 12.11.13
 * Time: 16:54
 * Integration! WooHoo!
 */
public class IntegralHost implements FunctionHost
{

	@Override
	public int getPriority()
	{
		return FUNCTION_PRIORITY;
	}

	@Override
	public MathExpression buildFunction(Vector<MathExpression> params) throws SyntaxErrorException
	{
		if (params.size() != 4)
			throw new SyntaxErrorException(SyntaxErrorException.WRONG_PARAMS_COUNT);
		if (! VarExpression.class.isInstance(params.get(3)))
			throw new SyntaxErrorException(SyntaxErrorException.WRONG_PARAM);
		return new IntegralExpression(params.get(0), params.get(1), params.get(2), ((VarExpression)params.get(3)).var);
	}
}

class IntegralExpression implements MathExpression
{
	private static int ITERATIONS = 1000000;

	private MathExpression lowerBound;
	private MathExpression upperBound;
	private MathExpression expression;
	private char variable;

	public IntegralExpression(MathExpression aLower, MathExpression aUpper, MathExpression expr, char var)
	{
		lowerBound = aLower;
		upperBound = aUpper;
		expression = expr;
		variable = var;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		MathValue leftValue  = lowerBound.calculate();
		MathValue rightValue = upperBound.calculate();
		double left  = leftValue.getRawValue();
		double right = rightValue.getRawValue();
		double sum = 0.0;
		double dx = (right - left) / ITERATIONS;
		Variables.setVariable(variable, leftValue);
		double last = expression.calculate().getRawValue();
		for (int i = 1; i <= ITERATIONS; ++i)
		{
			double x = (left * (ITERATIONS - i) + right * i) / ITERATIONS;
			Variables.setVariable(variable, new DoubleValue(x));
			double next = expression.calculate().getRawValue();
			sum += (next + last) / 2.0 * dx;
			last = next;
		}

		return MathExpressionBuilder.buildNumber(sum);
	}

	@Override
	public String write()
	{
		return "\\integral(" + lowerBound.write() + "," + upperBound.write()
			+ "," + expression.write() + "," + variable + ")";
	}
}
