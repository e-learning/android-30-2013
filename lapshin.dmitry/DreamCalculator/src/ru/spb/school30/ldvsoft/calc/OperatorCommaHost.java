package ru.spb.school30.ldvsoft.calc;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 07.11.13
 * Time: 22:01
 * Special expression for function params separated by commas
 */

public class OperatorCommaHost implements FunctionHost
{
	@Override
	public int getPriority()
	{
		return 0;
	}

	@Override
	public MathExpression buildFunction(Vector<MathExpression> params) throws SyntaxErrorException
	{
		return new ParamsExpression(params);
	}
}

class ParamsExpression implements MathExpression
{
	private Vector<MathExpression> params;

	public ParamsExpression()
	{
		params = new Vector<MathExpression>();
	}

	public ParamsExpression add(MathExpression expr)
	{
		if (ParamsExpression.class.isInstance(expr))
			params.addAll(((ParamsExpression)expr).getParams());
		else
			params.add(expr);
		return this;
	}

	public ParamsExpression(Vector<MathExpression> vector)
	{
		params = vector;
	}

	public Vector<MathExpression> getParams()
	{
		return params;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		throw new MathException("By unknown reason, there is ParamsExpression in final expression.");
	}

	@Override
	public String write()
	{
		String res = "__PARAMS_EXPRESSION__(";
		for (MathExpression param: params)
			res += param.write() + ";";
		return res;
	}
}
