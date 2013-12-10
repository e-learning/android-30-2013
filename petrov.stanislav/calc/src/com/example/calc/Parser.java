package com.example.calc;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI14
 * Date: 19.11.13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class Parser {

    public String Pars2(String S) {
        int i = 0;
        int j = 0;
        int m;
        char c = 0;
        i = S.length();
        for (m = j = S.indexOf('('); j < i; j++)
        {
            i = S.length();
            if ((c = S.charAt(j)) == '(')
                m = j;
            else if(c == ')')
            {
                S = S.substring(0, m) + Pars(S.substring(m + 1, j)) + S.substring(j + 1, i);
                m = j = S.indexOf('(');
            }
        }
        return S;
    }

    public String Pars4(String S)  throws Exception{
        /*  dermo s i i j!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
        int flag = 0;
        int i = 0;
        int j = 0;
        int m;
        char c = 0;
        j = S.length();
        for (i = 0; i < j; i++)
        {
            m = S.indexOf('s');
            if(m != -1 && S.charAt(m + 1) == 'i')
            {
                i = m + 4;
                int n = S.length();
                while(i < S.length()  && ((S.charAt(i)) !=  ')' || flag > 0))
                {
                   if(S.charAt(i) ==  '(')
                       flag++;
                   if(S.charAt(i) ==  ')')
                       flag--;
                   i++;
                }
                return Pars(S.substring(0, m) + (Double.toString(Math.sin(Math.toRadians(Double.parseDouble(Pars(S.substring(m + 4, i - 1))))))) + S.substring(i + 1, S.length()));
            }

            m = S.indexOf('c');
            if(m != -1 && S.charAt(m + 1) == 'o')
            {
                i = m + 4;
                int n = S.length();
                while(i < S.length()  && ((S.charAt(i)) !=  ')' || flag > 0))
                {
                    if(S.charAt(i) ==  '(')
                        flag++;
                    if(S.charAt(i) ==  ')')
                        flag--;
                    i++;
                }

                return Pars(S.substring(0, m) + (Double.toString(Math.cos(Math.toRadians(Double.parseDouble(Pars(S.substring(m + 4, i - 1)))))) + S.substring(i + 1, S.length())));
            }

            m = S.indexOf('t');
            if(m != -1 &&(m == 0 ||(m != 0 && S.charAt(m - 1) != 'c')))
            {
                i = m + 3;
                while(i < S.length()  && ((S.charAt(i)) !=  ')' || flag > 0))
                {
                    if(S.charAt(i) ==  '(')
                        flag++;
                    if(S.charAt(i) ==  ')')
                        flag--;
                    i++;
                }
                double tg = Double.parseDouble(Pars(S.substring(m + 3, i - 1)));
                if(tg == 90)
                    throw new Exception("\ntg doesn't exist");

                return Pars(S.substring(0, m) + (Double.toString(Math.tan(Math.toRadians(tg)))) + S.substring(i + 1, S.length()));
            }
            m = S.indexOf('c');
            if(m != -1 && S.charAt(m + 1) == 't')
            {
                i = m + 4;
                while(i < S.length()  && ((S.charAt(i)) !=  ')' || flag > 0))
                {
                    if(S.charAt(i) ==  '(')
                        flag++;
                    if(S.charAt(i) ==  ')')
                        flag--;
                    i++;
                }
                double ctg =  Double.parseDouble(Pars(S.substring(m + 4, i - 1)));
                if(ctg == 0)
                    throw new Exception("\nctg doesn't exist");

                return Pars(S.substring(0, m) + (Double.toString(1 / Math.tan(Math.toRadians(ctg)))) + S.substring(i + 1, S.length()-1));
            }
            S = S.substring(0, m) + Pars(S.substring(m + 1, j - 1)) + S.substring(j + 1, i);
        }
        return S;
    }

    public String Pars(String S) {
        String res = "";
        String res1 = "";
        int i = 0;
        int j = 0;
        char c = 0;
        double r = 0;
        i = S.length();

        if (S.indexOf("s") == -1 && S.indexOf("c") == -1 && S.indexOf("t") == -1 && S.indexOf("(") == -1 && S.indexOf(")") == -1 && S.indexOf("*") == -1 && S.indexOf("/") == -1 && S.indexOf("+") == -1 && (S.indexOf("-") == -1 || (S.indexOf("-") != -1 && S.charAt(S.indexOf("-") + 1) != ' ')))
            return S;

        if (S.indexOf("s") != -1 || S.indexOf("c") != -1 || S.indexOf("t") != -1)
            try
            {
                S = Pars4(S);
                if (S.indexOf("s") == -1 && S.indexOf("c") == -1 && S.indexOf("t") == -1 && S.indexOf("(") == -1 && S.indexOf("*") == -1 && S.indexOf("/") == -1 && S.indexOf(")") == -1 && S.indexOf("+") == -1 && (S.indexOf("-") == -1 || (S.indexOf("-") != -1 && S.charAt(S.indexOf("-") + 1) != ' ')))
                    return S;
            }
            catch (Exception E)
            {
                return E.toString();
            }

        if (S.indexOf("(") != -1 && S.indexOf(")") != -1)
            if(S.charAt(0) == ' ')
                S = Pars2(S);
            else
                S = Pars2(' ' + S);
        if(S.indexOf('*') != -1 || S.indexOf('/') != -1)
            if(S.charAt(0) == ' ')
                S = Pars3(S);
            else
                S =Pars3(' ' + S);
        if (S.indexOf("s") == -1 && S.indexOf("c") == -1 && S.indexOf("t") == -1 && S.indexOf("(") == -1 && S.indexOf("*") == -1 && S.indexOf("/") == -1 && S.indexOf(")") == -1 && S.indexOf("+") == -1 && (S.indexOf("-") == -1 || (S.indexOf("-") != -1 && S.charAt(S.indexOf("-") + 1) != ' ')))
            return S;
     /* if (s.charAt(0) == 'T' )
          res1 = "TI TUPOI CHTO LI???";
      else
      if (((s.charAt(i - 2) == '/') || (s.charAt(i - 2) == '*') || (s.charAt(i - 2) == '-') || (s.charAt(i - 2) == '+' )) && (S.charAt(j + 1) == ' ' && c == '-'))
          res1 = "TI DURAK";
      else*/
        i = S.length();
        while(i > j)
        {
            res = "";
            c = S.charAt(j);
            if((c != '+' && c != '-' && c != '/' && c != '*') || (S.charAt(j + 1) != ' ' && c == '-'))
            {
                res1 += c;
            }
            else if (c == '-'&& S.charAt(j + 1) == ' ')
            {
                j++;
                c = S.charAt(j);
                while(i > j && ((c != '+' && c != '-' && c != '/' && c != '*') || (S.charAt(j + 1) != ' ' && c == '-')))
                {
                    res += c;
                    j++;
                    if (i == j)
                        break;
                    c = S.charAt(j);
                }
                r = Double.parseDouble(res1) - Double.parseDouble(res);
                res1 = Double.toString(r);
                j--;
            }
            else if (c == '+')
            {
                j++;
                c = S.charAt(j);
                while(i > j && ((c != '+' && c != '-' && c != '/' && c != '*') || (S.charAt(j + 1) != ' ' && c == '-')))
                {
                    res += c;
                    j++;
                    if (i == j)
                        break;
                    c = S.charAt(j);
                }
                r = Double.parseDouble(res1) + Double.parseDouble(res);
                res1 = Double.toString(r);
                j--;
            }
            else if (c == '/')
            {
                j++;
                c = S.charAt(j);
                while(i > j && ((c != '+' && c != '-' && c != '/' && c != '*') || (S.charAt(j + 1) != ' ' && c == '-')))
                {
                    res += c;
                    j++;
                    if (i == j)
                        break;
                    c = S.charAt(j);
                }
                r = Double.parseDouble(res1) / Double.parseDouble(res);
                res1 = Double.toString(r);
                j--;
            }
            else if (c == '*')
            {
                j++;
                c = S.charAt(j);
                while(i > j && ((c != '+' && c != '-' && c != '/' && c != '*') || (S.charAt(j + 1) != ' ' && c == '-')))
                {
                    res += c;
                    j++;
                    if (i == j)
                        break;
                    c = S.charAt(j);

                }
                r = Double.parseDouble(res1) * Double.parseDouble(res);
                res1 = Double.toString(r);
                j--;
            }
            j++;
        }
        return res1;
    }

    String Pars3( String S )
    {
        int i, l, r;
        double res;
        String S2;
        char c;
        for (i = 0; i < S.length(); i++)
        {
            char c1 = S.charAt(i);
            S2 = " ";
            if(c1 == '/' || c1 == '*')
            {
                l = i - 2;
                c = S.charAt(l);
                while(c != ' ')
                {
                    l--;
                    if(l < 0)
                        break;
                    c = S.charAt(l);
                }
                int l1 = l;
                while(l1 != (i - 2))
                {
                    c = S.charAt(l1+1);
                    S2 += c;
                    l1++;
                }
                res = Double.parseDouble(S2);


                S2 = "";
                r = i + 2;
                c = S.charAt(r);
                while(c != ' ')
                {
                    S2 += c;
                    r++;
                    if(r >= S.length())
                        break;
                    c = S.charAt(r);
                }

                if (c1 == '*')
                   res *=  Double.parseDouble(S2);
                else
                    res /=  Double.parseDouble(S2);
                /*if (S2.charAt(0) == ' ')
                    S2 = S2.substring(1, S2.length());
                */
                S = S.substring(0, l) + " " + Double.toString(res) + S.substring(r, S.length());
            }

        }
        return S;
    }


}
