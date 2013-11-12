package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 12.11.13
 * Time: 17:03
 * Expression of variable. Variables class hosts their value
 */
public class VarExpression implements MathExpression
{
	char var;

	public VarExpression(char aVar)
	{
		var = aVar;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		return Variables.getVariable(var);
	}

	@Override
	public String write()
	{
		return Character.toString(var);
	}
}
