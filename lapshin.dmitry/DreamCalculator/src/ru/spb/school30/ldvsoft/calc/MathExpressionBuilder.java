package ru.spb.school30.ldvsoft.calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:41
 * Builds expression with it's structure from string form.
 */
public class MathExpressionBuilder
{
	private static final TreeMap<String, Integer> OPERATORS = new TreeMap<String, Integer>();
	private static final String NUMBER_PARTS = "0123456789.";
	private static final TreeMap<String, MathExpression> FUNCTIONS = new TreeMap<String, MathExpression>();
	static
	{
		OPERATORS.put("+", 1);
		OPERATORS.put("-", 1);
		OPERATORS.put("*", 2);
		OPERATORS.put("/", 2);

		FUNCTIONS.put("\\sqrt", new SqrtExpression(null));
		OPERATORS.put("\\sqrt", 1);
	}

	private static boolean isNumberPart(char c)
	{
		return NUMBER_PARTS.indexOf(c) != -1;
	}

	private static boolean isDigit(char c)
	{
		return '0' <= c && c <= '9';
	}

	private static Deque<Object> buildShuntingYard(String expressionString) throws SyntaxErrorException
	{
		Deque<String> operatorsStack = new ArrayDeque<String>();
		Deque<Object> expressionOut = new ArrayDeque<Object>();

		int i = 0;
		while (i < expressionString.length())
		{
			if (isNumberPart(expressionString.charAt(i)))
			{
				//Read a number
				double num = 0.0;
				while (i < expressionString.length() && isDigit(expressionString.charAt(i)))
				{
					num = num * 10.0 + (expressionString.charAt(i) - '0');
					++i;
				}
				if (i < expressionString.length() && expressionString.charAt(i) == '.')
				{
					double pow = 1;
					++i;
					while (i < expressionString.length() && isDigit(expressionString.charAt(i)))
					{
						pow /= 10.0;
						num += pow * (expressionString.charAt(i) - '0');
						++i;
					}
				}

				MathValue number;
				if (Math.abs(num) <= IntegerValue.MAX_VALUE && Math.abs(Math.round(num) - num) < DoubleValue.EPS)
					number = new IntegerValue((int)Math.round(num));
				else
					number = new DoubleValue(num);
				expressionOut.addLast(new NumberExpression(number));
			}
			else if (expressionString.charAt(i) == '\\')
			{
				//Read a function
				String function = "\\";
				i++;
				while (i < expressionString.length() && expressionString.charAt(i) != '(')
				{
					function += expressionString.charAt(i);
					i++;
				}
				if (!FUNCTIONS.containsKey(function))
					throw new SyntaxErrorException();
				operatorsStack.addLast(function);
			}
			else if (expressionString.charAt(i) == ',')
			{
				// , between function arguments
				throw new SyntaxErrorException();
			}
			else if (OPERATORS.containsKey(Character.toString(expressionString.charAt(i))))
			{
				// Operator
				while (operatorsStack.size() > 0 && OPERATORS.containsKey(operatorsStack.getLast())
						&& OPERATORS.get(operatorsStack.getLast()) >= OPERATORS.get(
							Character.toString(expressionString.charAt(i))
						))
					expressionOut.addLast(operatorsStack.removeLast());
				operatorsStack.addLast(Character.toString(expressionString.charAt(i)));
				++i;
			}
			else if (expressionString.charAt(i) == '(')
			{
				operatorsStack.addLast(Character.toString(expressionString.charAt(i)));
				++i;
			}
			else if (expressionString.charAt(i) == ')')
			{
				boolean wasOpeningBrace = false;
				while (operatorsStack.size() > 0)
				{
					if (operatorsStack.getLast().equals("("))
					{
						wasOpeningBrace = true;
						break;
					}
					expressionOut.addLast(operatorsStack.getLast());
					operatorsStack.removeLast();
				}
				if (!wasOpeningBrace)
					throw new SyntaxErrorException();
				operatorsStack.removeLast(); //That was a (
				if (operatorsStack.size() > 1 && operatorsStack.getLast().charAt(0) == '\\')
				{
					expressionOut.addLast(operatorsStack.getLast());
					operatorsStack.removeLast();
				}
				++i;
			}
			else
				throw new SyntaxErrorException();
		}

		while (operatorsStack.size() > 0)
		{
			if ("()".indexOf(operatorsStack.getLast().charAt(0)) != -1)
				throw new SyntaxErrorException();
			expressionOut.addLast(operatorsStack.getLast());
			operatorsStack.removeLast();
		}

		return expressionOut;
	}

	private static MathExpression buildExpression(Deque<Object> expressionDeque) throws SyntaxErrorException
	{
		Deque<MathExpression> exprStack = new ArrayDeque<MathExpression>();

		while (expressionDeque.size() > 0)
		{
			Object o = expressionDeque.removeFirst();
			MathExpression newExpr;
			if (MathExpression.class.isInstance(o))
				newExpr = (MathExpression) o;
			else
			{
				MathExpression op1;
				MathExpression op2;
				switch (((String) o).charAt(0))
				{
					case '+':
						if (exprStack.size() < 2)
							throw new SyntaxErrorException();
						op2 = exprStack.removeLast();
						op1 = exprStack.removeLast();
						newExpr = new AddExpression(op1, op2);
						break;
					case '-':
						if (exprStack.size() < 2)
							throw new SyntaxErrorException();
						op2 = exprStack.removeLast();
						op1 = exprStack.removeLast();
						newExpr = new SubstractExpression(op1, op2);
						break;
					case '*':
						if (exprStack.size() < 2)
							throw new SyntaxErrorException();
						op2 = exprStack.removeLast();
						op1 = exprStack.removeLast();
						newExpr = new MultiplyExpression(op1, op2);
						break;
					case '/':
						if (exprStack.size() < 2)
							throw new SyntaxErrorException();
						op2 = exprStack.removeLast();
						op1 = exprStack.removeLast();
						newExpr = new DivideExpression(op1, op2);
						break;
					case '\\':
						if (o.equals("\\sqrt"))
						{
							if (exprStack.size() < 1)
								throw new SyntaxErrorException();
							newExpr = new SqrtExpression(exprStack.removeLast());
							break;
						}
						throw new SyntaxErrorException();
					default:
						//Strange, just to be sure
						throw new SyntaxErrorException();
				}
			}

			exprStack.addLast(newExpr);
		}

		if (exprStack.size() > 1)
			throw new SyntaxErrorException();
		if (exprStack.size() == 0)
			return new NumberExpression(new DoubleValue(0.0));
		return exprStack.removeLast();
	}

	public static MathExpression buildExpression(String expressionString) throws SyntaxErrorException
	{
		//We know that expression looks like this:
		// S = LITERAL
		//	LITERAL = NUMBER | VARIABLE
		// S = (S)
		// S = S OPERATOR S
		//   OPERATOR = + | - | * | /
		// S = \FUNCTION(PARAMS)
		//   PARAMS = S [, PARAMS]

		return buildExpression(buildShuntingYard(expressionString));
	}
}
