package com.example.Calculator;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 04.11.13
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
public class Parser
{
  public static Double Factorial ( Double x )
  {
    if ( x < 2 )
      return 1.0;
    else
      return x * Factorial(x - 1);
  }
  public static Double Calculator(String S)
  {
    int pos, pos1, i = 0, j;
    Double ans;
    String s = "";
    if ((pos = S.indexOf("(")) != -1)
    {
      pos1 = S.indexOf(")");
      for (i = pos; i < S.length() && i < pos1; i++)
        if (S.charAt(i) == '(')
          pos = i;
      s = S.substring(0, pos);
      s = S.substring(pos + 1, pos1);
      s = S.substring(pos1 + 1, S.length());
      return Calculator(S.substring(0, pos) + Calculator(S.substring(pos + 1, pos1)) + S.substring(pos1 + 1, S.length()));
    }
   /* if ((pos = S.indexOf("(")) != -1)
    {
      o = 1;
      c = 1;
      h = 0;
      w = 0;
      pos1 = S.indexOf(")");
      for (k = 0; k < S.length() - 1; k++)
      {
        if (S.charAt(k) == '(' && S.charAt(k + 1) == '(')
        {
          o++;
          w = k;
        }
        if (S.charAt(k) == ')' && S.charAt(k + 1) == ')')
        {
          c++;
          h = k;
        }
      }
      if (pos1 == h)
      {
       if (pos == w)
         ans = Calculator(S.substring(pos + o, pos1 + c - 1));
       else
         ans = Calculator(S.substring(pos + o, pos1 + c - 1));
      }
      else
        ans = Calculator(S.substring(pos + o, pos1));

      if (o > 1)
      {
        if ((S.charAt(pos + 1) == '(' || S.charAt(pos + 1) == '+' || S.charAt(pos + 1) == '-'
                || S.charAt(pos + 1) == '/' || S.charAt(pos + 1) == '*' || S.charAt(pos + 1) == '^'))
          o--;

        if (c > 1 && (S.charAt(pos1 + 1) == ')' || S.charAt(pos1 + 1) == '+' || S.charAt(pos1 + 1) == '-'
                   || S.charAt(pos1 + 1) == '/' || S.charAt(pos1 + 1) == '*' || S.charAt(pos1 + 1) == '^'))
          c--;
        return Calculator(S.substring(0, pos + o) + Double.toString(ans) + S.substring(pos1 + c, S.length()));
      }
      else if (pos1 == S.length() - 1)
        return Calculator(S.substring(0, pos) + Double.toString(ans));
      else if (pos == 0)
        return Calculator(Double.toString(ans) + S.substring(pos1 + 1, S.length())); //c

      else /*if ((S.lastIndexOf('(') == pos) && S.lastIndexOf(')') == pos1) * /
        return Calculator(Double.toString(ans));
    }
     */

    if ((pos = S.indexOf("+")) != -1)
      return (Calculator(S.substring(0, pos)) + (Calculator(S.substring(pos + 1, S.length()))));
    if ((pos = S.indexOf("/")) != -1)
    {
      if ((Calculator(S.substring(pos + 1, S.length()))) == 0)
         return Double.parseDouble("");
      return (Calculator(S.substring(0, pos)) / (Calculator(S.substring(pos + 1, S.length()))));
    }
    if ((pos = S.indexOf("*")) != -1)
      return (Calculator(S.substring(0, pos)) * (Calculator(S.substring(pos + 1, S.length()))));
    if ((pos = S.indexOf("^")) != -1)
      return (Math.pow(Calculator(S.substring(0, pos)),(Calculator(S.substring(pos + 1, S.length())))));
    if ((pos = S.indexOf("s")) != -1 && S.charAt(pos + 1) == 'i')
    {
       j = i = pos + 3;
       s = S.substring(0, pos);
       while (((S.charAt(j) >= '0' && S.charAt(j) <= '9') || S.charAt(j) == '.') && j < S.length() - 1)
        j++;
       s = S.substring(i, j);
       ans = Math.sin(Math.toRadians(Calculator(S.substring(i, j))));
       s = S.substring(j, S.length());
       return Calculator(S.substring(0, pos) + Math.sin(Math.toRadians(Calculator(S.substring(i, j)))) + S.substring(j, S.length()));
    }
    if ((pos = S.indexOf("s")) != -1 && S.charAt(pos + 1) == 'q')
    {
      j = i = pos + 4;
      s = S.substring(0, pos);
      while (((S.charAt(j) >= '0' && S.charAt(j) <= '9') || S.charAt(j) == '.') && j < S.length() - 1)
        j++;
      s = S.substring(i, j);
      ans = Math.sqrt((Calculator(S.substring(i, j))));
      s = S.substring(j + 1, S.length());
      return Calculator(S.substring(0, pos) + Math.sqrt((Calculator(S.substring(i, j)))) + S.substring(j + 1, S.length()));
    }
    if ((pos = S.indexOf("c")) != -1 && S.charAt(pos + 1) == 'o')
    {
      j = i = pos + 3;
      s = S.substring(0, pos);
      while (((S.charAt(j) >= '0' && S.charAt(j) <= '9') || S.charAt(j) == '.') && j < S.length() - 1)
        j++;
      s = S.substring(i, j);
      ans = Math.cos(Math.toRadians(Calculator(S.substring(i, j))));
      s = S.substring(j + 1, S.length());
      return Calculator(S.substring(0, pos) + Math.cos(Math.toRadians(Calculator(S.substring(i, j)))) + S.substring(j + 1, S.length()));
    }
    if ((pos = S.indexOf("c")) != -1 && S.charAt(pos + 1) == 't')
    {
      j = i = pos + 3;
      s = S.substring(0, pos);
      while (((S.charAt(j) >= '0' && S.charAt(j) <= '9') || S.charAt(j) == '.') && j < S.length() - 1)
        j++;
      s = S.substring(i, j);
      ans = 1.0 / Math.tan(Math.toRadians(Calculator(S.substring(i, j))));
      s = S.substring(j + 1, S.length());
      return Calculator(S.substring(0, pos) + (1.0 / Math.tan(Math.toRadians(Calculator(S.substring(i, j))))) + S.substring(j + 1, S.length()));
    }
    else if ((pos = S.indexOf("t")) != -1)
   {
    j = i = pos + 2;
    s = S.substring(0, pos);
    while (((S.charAt(j) >= '0' && S.charAt(j) <= '9') || S.charAt(j) == '.') && j < S.length() - 1)
      j++;
    s = S.substring(i, j);
    ans = Math.tan(Math.toRadians(Calculator(S.substring(i, j))));
    s = S.substring(j + 1, S.length());
    return Calculator(S.substring(0, pos) + Math.tan(Math.toRadians(Calculator(S.substring(i, j)))) + S.substring(j + 1, S.length()));
   }
    if ((pos = S.indexOf("!")) != -1)
    {
      i = pos - 1;
      while (((S.charAt(i) >= '0' && S.charAt(i) <= '9') || S.charAt(i) == '.') && i > 0)
        i--;
      ans = Factorial(Calculator(S.substring(i, pos)));
      s = S.substring(0, i);
      s = S.substring(pos + 1, S.length());
     return Calculator(S.substring(0, i) + Factorial(Calculator(S.substring(i, pos))) + S.substring(pos + 1, S.length()));
    }
    if ((pos = S.indexOf("-")) != -1 && pos != 0 && (S.charAt(pos - 1) >= '0' && S.charAt(pos - 1) <= '9'))
      return (Calculator(S.substring(0, pos)) - (Calculator(S.substring(pos + 1, S.length()))));
    else if ((pos = S.indexOf("-")) != -1)
    {
      i = pos + 1;
      while (((S.charAt(i) >= '0' && S.charAt(i) <= '9') || S.charAt(i) == '.') && i < S.length() - 1)
        i++;
      s = S.substring(pos + 1, pos + 2 + (i - (pos + 1)));
      return -Double.parseDouble(S.substring(pos + 1, pos + 2 + ( i - (pos + 1))));
    }



    return Double.parseDouble(S);
  }
}
