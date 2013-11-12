package ru.spb.school30.ldvsoft.calc;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 12.11.13
 * Time: 16:42
 * Braces expression. Actually, it is function "\( params )", that will be used in MathIO mode to represent
 * braces beautifully
 */
public class OperatorBracesHost implements FunctionHost
{
	@Override
	public int getPriority()
	{
		return FUNCTION_PRIORITY;
	}

	@Override
	public MathExpression buildFunction(Vector<MathExpression> params) throws SyntaxErrorException
	{
		return new BracesExpression(params.get(0));
	}
}

class BracesExpression implements MathExpression
{
	MathExpression inside;

	public BracesExpression(MathExpression expression)
	{
		inside = expression;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		return inside.calculate();
	}

	@Override
	public String write()
	{
		return "\\(" + inside.write() + ")";
	}
}
