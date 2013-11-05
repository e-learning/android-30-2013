package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 23:13
 * [TEST]
 */
public class MathExpressionBuilderTest
{
	public void testBuildExpression() throws Exception
	{
		MathExpression expr;

		expr = MathExpressionBuilder.buildExpression("1+2+3");
		assert (expr.calculate().rawValue == 6.0);

		expr = MathExpressionBuilder.buildExpression("1*(2+3*4+5)*6");
		assert (expr.calculate().rawValue == 114.0);

		expr = MathExpressionBuilder.buildExpression("\\sqrt(4)+\\sqrt(9)");
		assert (expr.calculate().rawValue == 5.0);
	}
}
