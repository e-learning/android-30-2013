package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 21:24
 * Exception for wrong expressions
 */
public class SyntaxErrorException extends Exception
{
	@Override
	public String getMessage()
	{
		return "Syntax error found while building expression.\n" + super.getMessage();
	}
}
