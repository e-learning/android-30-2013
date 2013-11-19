package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 07.11.13
 * Time: 21:31
 * This empty expression is
 */
public class EmptyExpression implements MathExpression
{
	private static EmptyExpression link = null;

	public static MathExpression getInstance()
	{
		if (link == null)
			link = new EmptyExpression();
		return link;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		throw new MathException("Expression is not complete!");
	}

	@Override
	public String write()
	{
		return "";
	}
}
