package com.example.calc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;

import java.util.Random;
import java.util.Vector;

public class MyActivity extends Activity {
    String s;
    String s1;
    String s2;
    Bitmap bm;

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

    public String Pars(String S) {
      String res = "";
      String res1 = "";
      int i = 0;
      int j = 0;
      char c = 0;
      double r = 0;
      i = S.length();
     /* if (s.charAt(0) == 'T' )
          res1 = "TI TUPOI CHTO LI???";
      else
      if (((s.charAt(i - 2) == '/') || (s.charAt(i - 2) == '*') || (s.charAt(i - 2) == '-') || (s.charAt(i - 2) == '+' )) && (S.charAt(j + 1) == ' ' && c == '-'))
          res1 = "TI DURAK";
      else*/
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
                S2 += " "  + c1 + " ";
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

                if (S2.charAt(0) == ' ')
                    S2 = S2.substring(1, S2.length());

                S = S.substring(0, l) + " " + Pars(S2) + S.substring(r, S.length());
            }

        }
        return S;
    }

    void BrezenhemeLine2(int x1, int y1, int x2, int y2)
    {
        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        int signX = x1 < x2 ? 1 : -1;
        int signY = y1 < y2 ? 1 : -1;
        int error = deltaX - deltaY;

        bm.setPixel(x2, y2, 0xff00ffff);

        while(x1 != x2 || y1 != y2)
        {
            bm.setPixel(x1, y1, 0xff00ffff);
            final int error2 = error * 2;

            if(error2 > -deltaY)
            {
                error -= deltaY;
                x1 += signX;
            }

            if(error2 < deltaX)
            {
                error += deltaX;
                y1 += signY;
            }
        }
    }


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        s = new String();
        s = " ";
        s2 = "";
        Button b = (Button)findViewById(R.id.button0);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+="0";
                t.setText(s);

                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bmin = (Button)findViewById(R.id.buttonmin);
        bmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" - ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bplus = (Button)findViewById(R.id.buttonplus);
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" + ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bum = (Button)findViewById(R.id.buttonum);
        bum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" * ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bpm = (Button)findViewById(R.id.button12);
        bpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" -";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bumso = (Button)findViewById(R.id.button);
        bumso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" ( ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bumsz = (Button)findViewById(R.id.button10);
        bumsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" ) ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bp = (Button)findViewById(R.id.button11);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=".";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bdel = (Button)findViewById(R.id.buttondel);
        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+=" / ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+="1";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+="2";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
            Button b3 = (Button)findViewById(R.id.button3);
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view4) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="3";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
            Button b4 = (Button)findViewById(R.id.button4);
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view5) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="4";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
            Button b5 = (Button)findViewById(R.id.button5);
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view6) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="5";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
            Button b6 = (Button)findViewById(R.id.button6);
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view7) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="6";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }

            });
            Button b7 = (Button)findViewById(R.id.button7);
               b7.setOnClickListener(new View.OnClickListener() {
                    @Override
                 public void onClick(View view8) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="7";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                 }
                });
            Button b8 = (Button)findViewById(R.id.button8);
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="8";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
            Button b9 = (Button)findViewById(R.id.button9);
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view9) {
                    TextView t = (TextView) findViewById(R.id.editText);
                    s+="9";
                    t.setText(s);
                    ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
        Button b10 = (Button)findViewById(R.id.buttonC);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view9) {
                TextView t = (TextView) findViewById(R.id.editText);
                s=" ";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button brand = (Button)findViewById(R.id.buttonrand);
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view9) {
                TextView t = (TextView) findViewById(R.id.editText);
                Random x = new Random();
                s += Double.toString(x.nextDouble() * x.nextInt(5000));
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        Button bd = (Button)findViewById(R.id.buttond);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view9) {
                TextView t = (TextView) findViewById(R.id.editText);
                if (s.length() > 0)
                  s = s.substring(0, s.length() - 1);
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bH = (Button)findViewById(R.id.buttonH);
        bH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view9) {
                TextView t = (TextView) findViewById(R.id.editText);
                t.setText(s2);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bx = (Button)findViewById(R.id.buttonX);
        bx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view9) {
                TextView t = (TextView) findViewById(R.id.editText);
                s+="X";
                t.setText(s);
                ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        Button bR = (Button)findViewById(R.id.buttonR);
        bR.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View view9) {
             TextView t = (TextView) findViewById(R.id.editText);
             s1 = s;

            // s = "( ( 1 + 1 ) * 2 ) + (1 + 1)";

             if (s.indexOf("(") != -1 && s.indexOf(")") != -1)
                 s = ' ' + Pars2(s);
             if(s.indexOf('*') != -1 || s.indexOf('/') != -1)
                  s = ' ' + Pars3(s);
            // if (s.indexOf("-") != -1 || s.indexOf("+") != -1)
                 s = ' ' + Pars(s);
             s2 += s1 + " = " + s + "\n";
             t.setText(s1 + " = " + s);
        }
        ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
        });
        Button bg = (Button)findViewById(R.id.buttongraph);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view9) {
                double j;
                ImageButton t = (ImageButton) findViewById(R.id.imageButton);
                DisplayMetrics Disp = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(Disp);

                bm = bm.createBitmap(Disp.widthPixels, Disp.heightPixels, Bitmap.Config.ARGB_8888);

                for(j = 0; j < Disp.heightPixels; j++)
                    bm.setPixel(Disp.widthPixels / 2, (int)j, 0xff0000ff);
                for(j = 0; j < Disp.widthPixels; j++)
                    bm.setPixel((int)j, Disp.heightPixels / 2, 0xff00ff00);
                bm.setPixel(0, 0, 0xff0000ff);
                int flag = 0;
                int oldy = 0;
                for(j = -1 * Disp.widthPixels / 2; j < Disp.widthPixels / 2; j += 1)
                {
                    s1 = s.substring(0, s.indexOf('X')) + Double.toString(j) + s.substring(s.indexOf('X') + 1, s.length());
                    while(s1.indexOf('X') != -1)
                      s1 = s1.substring(0, s1.indexOf('X')) + Double.toString(j) + s1.substring(s1.indexOf('X') + 1, s1.length());
                    if (flag == 1)
                      oldy = (int)Double.parseDouble(s2);
                    if (s.indexOf("(") != -1 && s.indexOf(")") != -1)
                        s1 = Pars2(s1);
                    if (s.indexOf("/") != -1 || s.indexOf("*") != -1)
                        s1 = Pars3(s1);
                   // if (s.indexOf("-") != -1 || s.indexOf("+") != -1)
                        s1 = Pars(s1);
                    flag = 1;
                    try
                    {
                        if (flag == 1)
                           BrezenhemeLine2((int)((j - 1) * 10 + Disp.widthPixels / 2) ,(int)(oldy * -1 + Disp.heightPixels / 2), (int)(j * 10 + Disp.widthPixels / 2), (int)(Double.parseDouble(s1) * -1 + Disp.heightPixels / 2));
                        //bm.setPixel(((int)j * 10 + Disp.widthPixels / 2 ), (int)(Double.parseDouble(s2)) * -1 + Disp.heightPixels / 2 , 0xff00ffff);
                    }
                    catch (Exception Ex)
                    {
                        //s1 = Ex.toString();
                    }
                }

                t.setImageBitmap(bm);
                t.setVisibility(View.VISIBLE);
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view9) {
                        view9.setVisibility(View.GONE);
                    }
                });

            }
            ///Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
        });
    }
}
