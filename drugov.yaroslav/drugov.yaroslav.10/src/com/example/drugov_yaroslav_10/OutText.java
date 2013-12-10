package com.example.drugov_yaroslav_10;

import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18.11.13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class OutText {
    private TextView textView;
    private String text;
    private ArrayList<String> stack;
    private ArrayList<String> funks;

    public OutText( Activity activity, int id ) {
        textView = (TextView)activity.findViewById(id);
        stack = new ArrayList();
        funks = new ArrayList<String>();

        funks.add("sin");
        funks.add("cos");
        funks.add("tg");
        funks.add("ctg");
    }

    private int CheckFunks( String string ) {
        for (int i = 0; i < funks.size(); i++)
            if (funks.get(i).equals(string))
                return i;
        return -1;
    }

    private String CountFunk( int n, String arg ) {
        switch (n)
        {
            case 0: return "" + Math.sin(Double.parseDouble(arg));
            case 1: return "" + Math.cos(Double.parseDouble(arg));
            case 2: return "" + Math.tan(Double.parseDouble(arg));
            case 3: return "" + 1 / Math.tan(Double.parseDouble(arg));
        }
        return null;
    }

    private String Parse( String string ) {
        String tmp;
        int j, k;

        // parse brackets
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '(')
            {
                j = i;
                while (string.charAt(j) != ')')
                    if (++j >= string.length())
                        return null;
                if ((tmp = Parse(string.substring(i + 1, j))) == null)
                    return null;
                string = string.substring(0, i) + tmp + string.substring(j + 1, string.length());
            }

        char c;
        // parse functions
        for (int i = 0; i < string.length(); i++)
        {
            j = i;
            while ((c = string.charAt(j)) >= 'a' && c <= 'z')
                if (++j >= string.length())
                    return null;

            if (j > i)
            {
                k = j;
                while ((c = string.charAt(k)) >= '0' && c <= '9' || c == '.' || c == ',' || c == '-')
                    if ((k > j && c == '-') || ++k >= string.length())
                        break;
                if (k == j + 1 || CheckFunks(string.substring(i, j)) < 0)
                    return null;
                if ((tmp = CountFunk(CheckFunks(string.substring(i, j)), string.substring(j, k))) == null)
                    return null;
                string = string.substring(0, i) + tmp + string.substring(k, string.length());
            }
        }

        // parse operators * and /
        for (int i = 0; i < string.length(); i++)
        {
            if ((c = string.charAt(i)) == '*' || c == '/')
            {
                j = k = i;
                if (string.length() > j + 2 && string.charAt(j + 1) == '-')
                    j++;

                while (i == j || (c = string.charAt(j)) >= '0' && c <= '9' || c == '.' || c == ',')
                {
                    if (!(c == '.' && string.length() > j + 1 && string.charAt(j + 1) >= '0' && string.charAt(j + 1) <= '9' && string.charAt(j - 1) >= '0' && string.charAt(j - 1) <= '9'))
                        return null;
                    if ((j > i + 1 && c == '-') || ++j >= string.length())
                        break;
                }
                while (i == k || (c = string.charAt(k)) >= '0' && c <= '9' || c == '.' || c == ',')
                {
                    if (!(c == '.' && string.length() > j + 1 && string.charAt(j + 1) >= '0' && string.charAt(j + 1) <= '9' && string.charAt(j - 1) >= '0' && string.charAt(j - 1) <= '9'))
                        return null;
                    if (--k < 0)
                        break;
                }
                if (k >= i - 1 || j <= i + 1)
                    return null;
                k++;
                if (k == 1 && string.charAt(k - 1) == '-')
                    k--;
                if (string.charAt(i) == '*')
                    tmp = "" + Double.parseDouble(string.substring(k, i)) * Double.parseDouble(string.substring(i + 1, j));
                else
                    tmp = "" + Double.parseDouble(string.substring(k, i)) / Double.parseDouble(string.substring(i + 1, j));
                string = string.substring(0, k) + tmp + string.substring(j, string.length());
            }
        }

        // parse operators + and -
        for (int i = 0; i < string.length(); i++)
        {
            if ((c = string.charAt(i)) == '+' || c == '-')
            {
                j = k = i;
                if (string.length() > j + 2 && string.charAt(j + 1) == '-')
                    j++;

                while (j == i || (c = string.charAt(j)) >= '0' && c <= '9' || c == '.' || c == ',')
                {
                    if (!(c == '.' && string.length() > j + 1 && string.charAt(j + 1) >= '0' && string.charAt(j + 1) <= '9' && string.charAt(j - 1) >= '0' && string.charAt(j - 1) <= '9'))
                        return null;
                    if (++j >= string.length())
                        break;
                }
                while (k == i || (c = string.charAt(k)) >= '0' && c <= '9' || c == '.' || c == ',')
                {
                    if (!(c == '.' && string.length() > j + 1 && string.charAt(j + 1) >= '0' && string.charAt(j + 1) <= '9' && string.charAt(j - 1) >= '0' && string.charAt(j - 1) <= '9'))
                        return null;
                    if (--k < 0)
                        break;
                }
                if (k >= i - 1 || j <= i + 1)
                    continue;
                if (k > 0 && string.charAt(k) == '-')
                    k--;
                k++;
                if (string.charAt(i) == '+')
                    tmp = "" + ((k < i ? Double.parseDouble(string.substring(k, i)) : 0) + Double.parseDouble(string.substring(i, j)));
                else
                    tmp = "" + ((k < i ? Double.parseDouble(string.substring(k, i)) : 0) - Double.parseDouble(string.substring(i, j)));
                string = string.substring(0, k) + tmp + string.substring(j, string.length());
            }
        }

        return string;
    }

    public boolean Update( String string ) {
        if ((text = Parse(string)) == null)
        {
            textView.setText("Incorrect expresion");
            return false;
        }
        textView.setText(text);

        return true;
    }

    public boolean ParseOld( String string ) {
        char c;
        int j;

        text = "";

        for (int i = 0; i < string.length(); i++)
        {
            c = string.charAt(i);
            if (c >= '0' && c <= '9' || c == '.')
            {
                j = i + 1;
                while (j < string.length())
                {
                    c = string.charAt(j);
                    if ((c <= '0' || c >= '9') && c != '.')
                        break;
                    else
                        j++;
                }
                text += "n" + string.substring(i, j) + " ";
                i = j - 1;
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/')
            {
                while ((j = stack.size() - 1) >= 0)
                {
                    //if (!((stack.get(j).equals("+") && c != '*' && c != '/') ||
                    //      (stack.get(j).equals("-") && c != '*' && c != '/') ||
                    //       stack.get(j).equals("*") || stack.get(j).equals("/")))
                    //    break;
                    if (!((stack.get(j).equals("+") && c != '*' && c != '/') ||
                          (stack.get(j).equals("-") && c != '*' && c != '/') ||
                           stack.get(j).equals("*") || stack.get(j).equals("/")))
                        break;
                    text += stack.get(j) + " ";
                    stack.remove(j);
                }
                stack.add("o" + c);
            }
            else if (c == '(')
                stack.add("" + c);
            else if (c >= 'a' && c <= 'z')
            {
                c = string.charAt(j = i + 1);
                while (j < string.length())
                {
                    c = string.charAt(j);
                    if (!( c >= 'a' && c <= 'z'))
                        break;
                    else
                        j++;
                }
                if (CheckFunks(string.substring(i, j)) < 0)
                    return false;
                stack.add("f" + string.substring(i, j));
                i = j - 1;
            }
            else if (c == ')')
            {
                while ((j = stack.size() - 1) >= 0 && !stack.get(j).equals("("))
                {
                    text += stack.get(j) + " ";
                    stack.remove(j);
                }
                if (j < 0)
                    return false;
                else
                {
                    stack.remove(j);
                    if ((j = stack.size() - 1) >= 0 && CheckFunks(stack.get(j)) >= 0)
                    {
                        text += stack.get(j) + " ";
                        stack.remove(j);
                    }
                }
            }
        }

        while ((j = stack.size() - 1) >= 0)
        {
            text += stack.get(j) + " ";
            stack.remove(j);
        }

        double res = 0;
        String[] elements = text.split(" ");

        for (int i = 0; i < elements.length; i++)
        {
            switch (elements[i].charAt(0))
            {
                case 'n':
                    stack.add(elements[i].substring(1, elements[i].length()));
                    break;
                case 'f':
                    if (stack.size() < 1)
                        return false;
                    stack.remove(stack.size() - 1);
                    stack.add(CountFunk(CheckFunks(elements[i].substring(1, elements[i].length())), stack.get(stack.size() - 1)));

                    break;
                case 'o':
                    if (stack.size() < 2)
                        return false;
                    switch (elements[i].charAt(1))
                    {
                        case '+':
                            res = Double.parseDouble(stack.get(stack.size() - 2)) + Double.parseDouble(stack.get(stack.size() - 1));
                            break;
                        case '-':
                            res = Double.parseDouble(stack.get(stack.size() - 2)) - Double.parseDouble(stack.get(stack.size() - 1));
                            break;
                        case '*':
                            res = Double.parseDouble(stack.get(stack.size() - 2)) * Double.parseDouble(stack.get(stack.size() - 1));
                            break;
                        case '/':
                            res = Double.parseDouble(stack.get(stack.size() - 2)) / Double.parseDouble(stack.get(stack.size() - 1));
                            break;
                    }
                    stack.remove(stack.size() - 1);
                    stack.remove(stack.size() - 1);
                    stack.add("" + res);
                    break;
            }

        }

        if (stack.size() != 1)
            return false;
        textView.setText(stack.get(0));
        return true;
    }
}
