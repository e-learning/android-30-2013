package LV5.Calculator;
import java.lang.Math.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 04.11.13
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class Parser
{
    public static double Calculation(String src) throws Exception
    {
        int pos, pos2,i;
        char sign1, sign2;
        pos = src.indexOf('(');
        pos2 = src.indexOf(')');


        if(pos != - 1|| pos2 != -1)
        {
            if(pos2 == -1)
                throw new Exception("miss some ')");
             if(pos == -1 && pos2 != -1)
                throw new Exception("Syntax error( meet ')' before '('");
            else if(pos2 < pos)
                throw new Exception("Syntax error( meet ')' before '('");
            else
            {
                for(i = src.indexOf('('); i < src.length(); i++)
                {
                    if(src.charAt(i) == '(')
                        pos = i;
                    if(src.charAt(i) == ')')
                    {
                        pos2 = i;
                        src = src.substring(0, pos) + Double.toString(Calculation(src.substring(pos + 1, pos2)))  +  src.substring(pos2 + 1, src.length());
                        return Calculation(src);
                    }
                }

            }
         }



        pos = src.indexOf('s');
        if(pos != -1 && src.charAt(pos + 1) == 'i')
        {
            i = pos + 3;
            int n = src.length();
            while(i < src.length()  && ((src.charAt(i) >= '0' && src.charAt(i) <= '9' )|| src.charAt(i) == '.' || (src.charAt(i) == '-' && (src.charAt(i - 1) < '0' || src.charAt(i - 1) > '9'))))
                i++;
          return Calculation(src.substring(0, pos) + (Double.toString(Math.sin(Math.toRadians(Calculation(src.substring(pos + 3, i)))))) + src.substring(i, src.length()));
        }

        pos = src.indexOf('c');
        if(pos != -1 && src.charAt(pos + 1) == 'o')
        {
            i = pos + 3;
            int n = src.length();
            while(i < src.length()  && ((src.charAt(i) >= '0' && src.charAt(i) <= '9' )|| src.charAt(i) == '.' || (src.charAt(i) == '-' && (src.charAt(i - 1) < '0' || src.charAt(i - 1) > '9'))))
                i++;

            return Calculation(src.substring(0, pos) + (Double.toString(Math.cos(Math.toRadians(Calculation(src.substring(pos + 3, i)))))) + src.substring(i, src.length()));
        }

        pos = src.indexOf('t');
        if(pos != -1 &&(pos == 0 ||(pos != 0 && src.charAt(pos - 1) != 'c')))
        {
            i = pos + 2;
            while(i < src.length()  && ((src.charAt(i) >= '0' && src.charAt(i) <= '9' )|| src.charAt(i) == '.' || (src.charAt(i) == '-' && (src.charAt(i - 1) < '0' || src.charAt(i - 1) > '9'))))
              i++;
            double tg = Calculation(src.substring(pos + 2, i));
            if(tg == 90)
              throw new Exception("\ntg doesn't exist");

            return Calculation(src.substring(0, pos) + (Double.toString(Math.tan(Math.toRadians(tg)))) + src.substring(i, src.length()));
        }
        pos = src.indexOf('c');
        if(pos != -1 && src.charAt(pos + 1) == 't')
        {
            i = pos + 3;
            while(i < src.length()  && ((src.charAt(i) >= '0' && src.charAt(i) <= '9' )|| src.charAt(i) == '.' ||  (src.charAt(i) == '-' && (src.charAt(i - 1) < '0' || src.charAt(i - 1) > '9'))))
                i++;
            double ctg = Calculation(src.substring(pos + 3, i));
            if(ctg == 0)
                throw new Exception("\nctg doesn't exist");

            return Calculation(src.substring(0, pos) + (Double.toString(1 / Math.tan(Math.toRadians(ctg)))) + src.substring(i, src.length()));
        }

        pos = src.indexOf('+');
        if (pos != -1)
            return Calculation(src.substring(0, pos)) + Calculation(src.substring(pos + 1, src.length()));

        pos = src.lastIndexOf('-');
        if (pos > 0 )
            if((src.charAt(pos - 1)>= '0' && src.charAt(pos - 1) <= '9') || (src.charAt(pos - 1) == ')'))
              return Calculation(src.substring(0, pos)) - Calculation(src.substring(pos + 1, src.length()));

        pos = src.indexOf('m');
        if (pos != -1)
            return Calculation(src.substring(0, pos)) % Calculation(src.substring(pos + 3, src.length()));

        pos = src.indexOf('a');
        if(pos != -1 && src.charAt(pos + 2) == 's')
        {
            i = pos + 3;
            int n = src.length();
            while(i < src.length()  && ((src.charAt(i) >= '0' && src.charAt(i) <= '9' )|| src.charAt(i) == '.' || (src.charAt(i) == '-' && (src.charAt(i - 1) < '0' || src.charAt(i - 1) > '9'))))
                i++;

            return Calculation(src.substring(0, pos) + (Double.toString(Math.abs(Calculation(src.substring(pos + 3, i)))) + src.substring(i, src.length())));
        }


        pos = src.indexOf('*');
        if (pos != -1)
            return Calculation(src.substring(0, pos)) * Calculation(src.substring(pos + 1, src.length()));

        pos = src.indexOf('/');
        if (pos != -1)
            return Calculation(src.substring(0, pos)) / Calculation(src.substring(pos + 1, src.length()));

        pos = src.indexOf('^');
        if (pos != -1)
            return Math.pow(Calculation(src.substring(0, pos)), Calculation(src.substring(pos + 1, src.length())));

        return Double.parseDouble(src);
    }

    public static double FunctionCalculation (String src,double x)
    {
        int i = 0;


        while(i < src.length())
        {
            if(src.charAt(i) == 'X')
                src = src.substring(0,i) + Double.toString(x) + src.substring(i + 1, src.length());
            i++;
        }

        try
        {
            src = Double.toString(Parser.Calculation(src));
        }
        catch (Exception Ex)
        {
            src = "Unfortunately there is a problem: " + Ex.toString();

        }

        return Double.parseDouble(src);


    }
}
