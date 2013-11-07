package ru.spb.school30.ldvsoft.calc;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:40
 * MathExpression for operator /
 */
public class OperatorDivideHost implements FunctionHost
{
	@Override
	public int getPriority()
	{
		return 2;
	}

	@Override
	public MathExpression buildFunction(Vector<MathExpression> params) throws SyntaxErrorException
	{
		MathExpression op1 = params.get(0);
		MathExpression op2 = params.get(1);
		if (Calculator.getMode() == Calculator.Mode.REAL)
			return new NumberDivideExpression(op1, op2);
		throw new SyntaxErrorException("No known operator / for these params.");
	}
}

class NumberDivideExpression implements MathExpression
{
	MathExpression left, right;

	public NumberDivideExpression(MathExpression aLeft, MathExpression aRight)
	{
		left = aLeft;
		right = aRight;
	}

	@Override
	public MathValue calculate() throws MathException
	{
		MathValue lValue = left.calculate();
		MathValue rValue = right.calculate();
		//Stupid, just for now
		return new DoubleValue(lValue.getRawValue() + rValue.getRawValue());
	}

	@Override
	public String write()
	{
		return left.write() + "/" + right.write();
	}
}
