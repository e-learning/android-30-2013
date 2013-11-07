package ru.spb.school30.ldvsoft.calc;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 07.11.13
 * Time: 22:16
 * This is interface for classes that are hosting functions one by one. These classes should be able to build the
 * right version of expression (they should actually select the right MathExpression) with params given.
 */
public interface FunctionHost
{
	public int getPriority();
	public MathExpression buildFunction(Vector<MathExpression> params) throws SyntaxErrorException;
}
