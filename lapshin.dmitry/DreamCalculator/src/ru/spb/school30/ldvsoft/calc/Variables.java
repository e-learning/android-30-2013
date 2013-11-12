package ru.spb.school30.ldvsoft.calc;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 12.11.13
 * Time: 17:20
 * Class that contains all the variables
 */
public class Variables
{
	private static Map<Character, MathValue> variables = new TreeMap<Character, MathValue>();
	static
	{
		variables.put('x', new DoubleValue(0.0));
	}

	public static MathValue getVariable(char var)
	{
		return variables.get(var);
	}

	public static void setVariable(char var, MathValue value)
	{
		variables.put(var, value);
	}

	public static boolean isVariable(char c)
	{
		return variables.containsKey(c);
	}

}
