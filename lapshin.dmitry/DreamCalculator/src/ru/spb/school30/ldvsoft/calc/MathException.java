package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 07.11.13
 * Time: 21:32
 * Exceprions for errors while calculating
 */
public class MathException extends Exception
{
	String error;
	public MathException(String s)
	{
		error = s;
	}
}
