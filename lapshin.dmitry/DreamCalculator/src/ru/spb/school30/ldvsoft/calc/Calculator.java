package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 29.10.13
 * Time: 19:28
 * Class that calculates the value of expression
 */
public class Calculator
{
	public static double Calc(String S) throws Exception
	{
		while (S.charAt(0) == '(' && S.charAt(S.length() - 1) == ')')
			S = S.substring(1, S.length() - 1);

		if (S.charAt(0) == '(' || S.charAt(S.length() - 1) == ')')
			throw new Exception("Syntax error");

		int pos;

		pos = S.indexOf('^');
		if (pos != -1)
			return Math.pow(Calc(S.substring(0, pos)), Calc(S.substring(pos + 1, S.length())));

		pos = S.indexOf('+');
		if (pos != -1)
			return Calc(S.substring(0, pos)) + Calc(S.substring(pos + 1, S.length()));
		pos = S.indexOf('-');
		if (pos != -1)
			return Calc(S.substring(0, pos)) - Calc(S.substring(pos + 1, S.length()));

		pos = S.indexOf('*');
		if (pos != -1)
			return Calc(S.substring(0, pos)) * Calc(S.substring(pos + 1, S.length()));
		pos = S.indexOf('/');
		if (pos != -1)
			return Calc(S.substring(0, pos)) / Calc(S.substring(pos + 1, S.length()));

		return Double.parseDouble(S);
	}
}
