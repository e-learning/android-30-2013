package ru.spb.school30.ldvsoft.calc;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 29.10.13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class Calculator
{
	private final static double Calc(String S)
	{
		while (S.charAt(0) == '(' && S.charAt(S.length() - 1) == ')')
			S = S.substring(1, S.length() - 1);

		return 0;
	}
}
