package ru.spb.school30.ldvsoft.calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 04.11.13
 * Time: 20:41
 * Builds expression with it's parts from string form.
 */
public class MathExpressionBuilder
{
	private static final TreeMap<Character, Integer> OPERATORS = new TreeMap<Character, Integer>();
	private static final String NUMBER_PARTS = "0123456789.";
	static
	{
		OPERATORS.put('+', 1);
		OPERATORS.put('-', 1);
		OPERATORS.put('*', 2);
		OPERATORS.put('/', 2);
		OPERATORS.put('(', 0);
		OPERATORS.put(')', 0);
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
		//To Polish
		Deque<Character> operatorsStack = new ArrayDeque<Character>();
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
					}
				}

				expressionOut.addLast(new NumberExpression(num));
			}
			else if (expressionString.charAt(i) == '\\')
			{
				//Read a function
				throw new SyntaxErrorException();
			}
			else if (expressionString.charAt(i) == ',')
			{
				// , between function arguments
				throw new SyntaxErrorException();
			}
			else if (OPERATORS.containsKey(expressionString.charAt(i)))
			{
				// Operator
				while (operatorsStack.size() > 0 && OPERATORS.containsKey(operatorsStack.getLast())
						&& OPERATORS.get(operatorsStack.getLast()) >= OPERATORS.get(expressionString.charAt(i)))
				{
					expressionOut.addLast(operatorsStack.getLast());
					operatorsStack.removeLast();
				}
				operatorsStack.addLast(expressionString.charAt(i));
				++i;
			}
			else if (expressionString.charAt(i) == '(')
			{
				operatorsStack.addLast(expressionString.charAt(i));
				++i;
			}
			else if (expressionString.charAt(i) == ')')
			{
				boolean wasOpeningBrace = false;
				while (operatorsStack.size() > 0)
				{
					if (operatorsStack.getLast() == '(')
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
				if (operatorsStack.size() > 1 && operatorsStack.getLast() == '\\')
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
			if ("()".indexOf(operatorsStack.getLast()) != -1)
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
			Object o = expressionDeque.getFirst();
			expressionDeque.removeFirst();
			MathExpression newExpr = null;
			if (MathExpression.class.isInstance(o))
				newExpr = (MathExpression) o;
			else
			{
				MathExpression op1;
				MathExpression op2;
				switch ((Character) o)
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
				}
			}

			exprStack.addLast(newExpr);
		}

		if (exprStack.size() > 1)
			throw new SyntaxErrorException();
		if (exprStack.size() == 0)
			return new NumberExpression(0.0);
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
