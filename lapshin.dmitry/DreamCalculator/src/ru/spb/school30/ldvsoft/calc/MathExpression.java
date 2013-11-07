package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:07
 * Base interface for expressions
 */
public interface MathExpression
{
	//Calculates value of expression
	public MathValue calculate() throws MathException;
	//Writes out expression. Should build from it's own structure, not from expression in constructor
	public String write();
}
