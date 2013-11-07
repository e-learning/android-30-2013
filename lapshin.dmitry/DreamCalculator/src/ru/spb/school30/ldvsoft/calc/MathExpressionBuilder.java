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
 * We know that expression looks like this:
 * 1. Simple - like Number, Variable etc
 *     S
 * 2. Operators - like Add, Subtract etc
 *     S %operator% S
 * 3. Functions - like Sin, Cos, Ln etc
 *     \%functionName%([S [, ...]])
 */
public class MathExpressionBuilder
{
	private static final String NUMBER_PARTS = "0123456789.";
	private static final String DIGITS = "0123456789";
	private static final TreeMap<String, FunctionHost> FUNCTIONS = new TreeMap<String, FunctionHost>();
	static
	{
		FUNCTIONS.put("+", new OperatorAddHost());
		FUNCTIONS.put("-", new OperatorSubstractHost());
		FUNCTIONS.put("*", new OperatorMultiplyHost());
		FUNCTIONS.put("/", new OperatorDivideHost());
		FUNCTIONS.put(",", new OperatorCommaHost());
		FUNCTIONS.put("\\sqrt", new SqrtHost());
	}

	private static boolean isNumberPart(char c)
	{
		return NUMBER_PARTS.indexOf(c) != -1;
	}

	private static boolean isDigit(char c)
	{
		return DIGITS.indexOf(c) != -1;
	}

	private static NumberValue buildNumber(double aNumber)
	{
		if (Math.abs(aNumber) <= IntegerValue.MAX_VALUE && Math.abs(Math.round(aNumber) - aNumber) < DoubleValue.EPS)
			return new IntegerValue((int)Math.round(aNumber));
		return new DoubleValue(aNumber);
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

				expressionOut.addLast(new NumberExpression(buildNumber(num)));
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
					throw new SyntaxErrorException(SyntaxErrorException.WRONG_FUNCTION);
				operatorsStack.addLast(function);
			}
			else if (FUNCTIONS.containsKey(expressionString.substring(i, i + 1)))
			{
				// Operator
				while (
					operatorsStack.size() > 0 && FUNCTIONS.containsKey(operatorsStack.getLast())
					&& FUNCTIONS.get(operatorsStack.getLast()).getPriority()
					>= FUNCTIONS.get(expressionString.substring(i, i + 1)).getPriority()
				)
					expressionOut.addLast(operatorsStack.removeLast());
				operatorsStack.addLast(expressionString.substring(i, i + 1));
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
					throw new SyntaxErrorException("Brace error");
				operatorsStack.removeLast(); //That was a (
				if (operatorsStack.size() > 1 && operatorsStack.getLast().charAt(0) == '\\')
				{
					expressionOut.addLast(operatorsStack.getLast());
					operatorsStack.removeLast();
				}
				++i;
			}
			else
				throw new SyntaxErrorException("WATAFAG????");
		}

		while (operatorsStack.size() > 0)
		{
			if ("()".indexOf(operatorsStack.getLast().charAt(0)) != -1)
				throw new SyntaxErrorException("Brace error");
			expressionOut.addLast(operatorsStack.getLast());
			operatorsStack.removeLast();
		}

		return expressionOut;
	}

	private static class ExprDeque extends ArrayDeque<MathExpression>
	{
		@Override
		public MathExpression removeLast()
		{
			if (size() > 0)
				return super.removeLast();
			return EmptyExpression.getInstance();
		}
	}

	private static MathExpression buildExpression(Deque<Object> expressionDeque) throws SyntaxErrorException
	{
		Deque<MathExpression> exprStack = new ExprDeque();

		while (expressionDeque.size() > 0)
		{
			Object o = expressionDeque.removeFirst();
			MathExpression newExpr;
			if (MathExpression.class.isInstance(o))
				newExpr = (MathExpression) o;
			else
			{
				ParamsExpression params;
				MathExpression op1;
				MathExpression op2;
				switch (((String) o).charAt(0))
				{
					case ',':
					case '+':
					case '-':
					case '*':
					case '/':
						op2 = exprStack.removeLast();
						op1 = exprStack.removeLast();
						params = new ParamsExpression()
							.add(op1)
							.add(op2);
						break;
					default:
						params = new ParamsExpression();
						if (exprStack.size() != 0)
							params.add(exprStack.removeLast());
				}
				newExpr = FUNCTIONS.get(o).buildFunction(params.getParams());
			}
			exprStack.addLast(newExpr);
		}

		if (exprStack.size() > 1)
			throw new SyntaxErrorException("Something left behind...");
		if (exprStack.size() == 0)
			return new NumberExpression(new DoubleValue(0.0));
		return exprStack.removeLast();
	}

	public static MathExpression buildExpression(String expressionString) throws SyntaxErrorException
	{
		return buildExpression(buildShuntingYard(expressionString));
	}
}
