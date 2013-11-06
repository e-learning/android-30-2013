package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:40
 * MathExpression for operator *
 */
public class MultiplyExpression implements MathExpression
{
	MathExpression left, right;

	public MultiplyExpression(MathExpression aLeft, MathExpression aRight)
	{
		left = aLeft;
		right = aRight;
	}

	@Override
	public MathValue calculate()
	{
		MathValue lValue = left.calculate();
		MathValue rValue = right.calculate();
		//Stupid, just for now
		return new DoubleValue(lValue.getRawValue() * rValue.getRawValue());
	}

	@Override
	public String write()
	{
		return left.write() + "*" + right.write();
	}
}
