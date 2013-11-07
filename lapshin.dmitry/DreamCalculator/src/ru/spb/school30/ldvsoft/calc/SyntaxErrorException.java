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
	public static String WRONG_FUNCTION = "Unknown function in expression. How have you done this?";
	private String error;

	public SyntaxErrorException(String s)
	{
		error = s;
	}

	@Override
	public String getMessage()
	{
		return "SYNTAX error: " + error;
	}
}
