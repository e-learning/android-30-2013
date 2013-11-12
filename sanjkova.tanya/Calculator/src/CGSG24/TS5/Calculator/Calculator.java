package CGSG24.TS5.Calculator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.lang.Math.pow;
import static java.lang.Math.tan;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

public class Calculator extends Activity {


    private class NumButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            final Button sender = (Button) view;
            final TextView field = (TextView) findViewById(R.id.textView);
            field.setText(field.getText().toString() + sender.getText().toString());
        }
    }

    static private double Parser(String S) throws Exception
    {
        int p, p1;

        p = S.indexOf('x');
        if (p != -1)
            throw new Exception("It's impossible");


        p = S.indexOf('(');
        p1 = S.indexOf(')');
        if (p != -1)
            if (p1 == -1)
                throw new Exception("Error");
            else
            {
                double b = 0, n = 0;
                if (p == 0 || (S.charAt(p - 1) != 'n' && S.charAt(p - 1) != 's'))
                {
                    if (p != 0)
                        b = Parser(S.substring(0, p - 1));
                    if (p1 != S.length() - 1)
                        n = Parser(S.substring(p1 + 2, S.length()));
                    if (b != 0 && n != 0)
                        return Parser(Double.toString(b) + S.substring(p - 1, p) + Parser(S.substring(p + 1, p1)) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));
                    else if (b != 0 && n == 0)
                        return Parser(Double.toString(b) + S.substring(p - 1, p) + Parser(S.substring(p + 1, p1)));
                    else if (b == 0 && n != 0)
                        return Parser(Parser(S.substring(p + 1, p1)) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));
                    else
                        return Parser(S.substring(p + 1, p1));
                }
                else
                {
                    if (p1 == S.length() - 1 && (p - 4) != -1)
                    {
                        int y;
                        b = Parser(S.substring(0, p - 4));

                        y = S.indexOf('i');
                        if (y != -1)
                            return Parser(Double.toString(b) + S.substring(p - 4, p - 3) + sin(Parser(S.substring(p + 1, p1))));

                        y = S.indexOf('o');
                        if (y != -1)
                            return Parser(Double.toString(b) + S.substring(p - 4, p - 3) + cos(Parser(S.substring(p + 1, p1))));

                        y = S.indexOf('t');
                        if (y != -1)
                            return Parser(Double.toString(b) + S.substring(p - 4, p - 3) + tan(Parser(S.substring(p + 1, p1))));

                    }
                    else if (p1 == S.length() - 1 && (p - 4) == -1)
                    {
                        p = S.indexOf('i');
                        if (p != -1)
                            return sin(Parser(S.substring(p + 3, p1)));

                        p = S.indexOf('o');
                        if (p != -1)
                            return cos(Parser(S.substring(p + 3, p1)));

                        p = S.indexOf('a');
                        if (p != -1)
                            return tan(Parser(S.substring(p + 3, p1)));

                    }
                    else if (p - 4 == -1 && p1 != S.length() - 1)
                    {
                        n = Parser(S.substring(p1 + 2, S.length()));

                        p = S.indexOf('i');
                        if (p != -1)
                            return Parser(sin(Parser(S.substring(p + 3, p1))) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));

                        p = S.indexOf('o');
                        if (p != -1)
                            return Parser(cos(Parser(S.substring(p + 3, p1))) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));

                        p = S.indexOf('a');
                        if (p != -1)
                            return Parser(tan(Parser(S.substring(p + 3, p1))) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));
                    }
                    else
                    {
                        int y;

                        b = Parser(S.substring(0, p - 4));
                        n = Parser(S.substring(p1 + 2, S.length()));

                        y = S.indexOf('i');
                        if (y != -1)
                            return Parser(Double.toString(b) + S.substring(p - 4, p - 3) + sin(Parser(S.substring(p + 1, p1))) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));

                        y = S.indexOf('o');
                        if (y != -1)
                            return Parser(Double.toString(b) + S.substring(p - 4, p - 3) + cos(Parser(S.substring(p + 1, p1))) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));

                        y = S.indexOf('a');
                        if (y != -1)
                            return Parser(Double.toString(b) + S.substring(p - 4, p - 3) + tan(Parser(S.substring(p + 1, p1))) + S.substring(p1 + 1, p1 + 2) + Double.toString(n));

                    }
                }
            }
        p = S.indexOf('^');
        if (p != -1)
            return pow(Parser(S.substring(0, p)), Parser(S.substring(p + 1, S.length())));

        p = S.indexOf('*');
        if (p != -1)
            return Parser(S.substring(0, p)) * Parser(S.substring(p + 1, S.length()));

        p = S.indexOf('/');
        if (p != -1)
            return Parser(S.substring(0, p)) / Parser(S.substring(p + 1, S.length()));

        p = S.indexOf('+');
        if (p != -1)
            return Parser(S.substring(0, p)) + Parser(S.substring(p + 1, S.length()));


        p = S.lastIndexOf('-');
        if (p != -1)
            if (p != 0 && S.charAt(p - 1) != 'E')
                return Parser(S.substring(0, p)) - Parser(S.substring(p + 1, S.length()));

        return Double.parseDouble(S);
    }

    static private int ParserPicture(String S, int x) throws Exception
    {
        int p;

        p = S.indexOf('x');
        if (p != -1)
        {
            for (int i = 0; i < S.length(); i++)
                if (S.charAt(i)  == 'x')
                    S = S.substring(0, i) + Integer.toString(x) + S.substring(i + 1, S.length());

            return (int)Parser(S);
        }
        else
            throw new Exception("");

    }
    private NumButtonListener numButtonListener = new NumButtonListener();

    private void Init()
    {
        findViewById(R.id.button).setOnClickListener(numButtonListener);
        findViewById(R.id.button3).setOnClickListener(numButtonListener);
        findViewById(R.id.button5).setOnClickListener(numButtonListener);
        findViewById(R.id.button1).setOnClickListener(numButtonListener);
        findViewById(R.id.button4).setOnClickListener(numButtonListener);
        findViewById(R.id.button8).setOnClickListener(numButtonListener);
        findViewById(R.id.button2).setOnClickListener(numButtonListener);
        findViewById(R.id.button6).setOnClickListener(numButtonListener);
        findViewById(R.id.button7).setOnClickListener(numButtonListener);
        findViewById(R.id.button9).setOnClickListener(numButtonListener);
        findViewById(R.id.button14).setOnClickListener(numButtonListener);
        findViewById(R.id.button12).setOnClickListener(numButtonListener);
        findViewById(R.id.button13).setOnClickListener(numButtonListener);
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ((TextView) findViewById(R.id.textView)).setText("");
            }
        });
        findViewById(R.id.button10).setOnClickListener(numButtonListener);
        findViewById(R.id.button19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expr = ((TextView) findViewById(R.id.textView)).getText().toString();
                try
                {
                    expr = Double.toString(Parser(expr));
                }
                catch (Exception E)
                {
                    expr = E.toString();
                }

                ((TextView) findViewById(R.id.textView)).setText(expr);
            }
        });
        findViewById(R.id.button15).setOnClickListener(numButtonListener);
        findViewById(R.id.button16).setOnClickListener(numButtonListener);
        findViewById(R.id.button23).setOnClickListener(numButtonListener);
        findViewById(R.id.button17).setOnClickListener(numButtonListener);
        findViewById(R.id.button18).setOnClickListener(numButtonListener);
        findViewById(R.id.button20).setOnClickListener(numButtonListener);
        findViewById(R.id.button21).setOnClickListener(numButtonListener);
        findViewById(R.id.button22).setOnClickListener(numButtonListener);
    }
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button num1 = (Button)findViewById(R.id.button2);
        final TextView TV_res =(TextView)findViewById(R.id.textView);

        Init();
        findViewById(R.id.button24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expr = ((TextView) findViewById(R.id.textView)).getText().toString();
                setContentView(R.layout.picture);

                ImageButton ImB = (ImageButton)findViewById(R.id.imageButton);

                Bitmap bmp = Bitmap.createBitmap(2000, 2000, Bitmap.Config.RGB_565);

                for (int i = 0; i < bmp.getWidth(); i++)
                {
                    if (i % 10 == 0)
                    {
                        bmp.setPixel(i, bmp.getHeight() / 2 + 1, 0xFF00FF);
                        bmp.setPixel(i, bmp.getHeight() / 2 - 1, 0xFF00FF);
                    }
                    bmp.setPixel(i, bmp.getHeight() / 2, 0xFF00FF);
                }

                for (int i = 0; i < bmp.getHeight(); i++)
                {
                    if (i % 10 == 0)
                    {
                        bmp.setPixel(bmp.getWidth() / 2 + 1, i, 0x00FF00);
                        bmp.setPixel(bmp.getWidth() / 2 - 1, i, 0x00FF00);
                    }

                    bmp.setPixel(bmp.getWidth() / 2, i, 0x00FF00);
                }

                int x;
                try
                {
                    int h;
                    for (x = -1000; x < 1000; x++)
                        if (ParserPicture(expr, x) * -1 < bmp.getHeight() / 2 && ParserPicture(expr, x) * -1 > -bmp.getHeight() / 2 && x > -bmp.getWidth() / 2 && x < bmp.getWidth() / 2)
                        {
                            h = ParserPicture(expr, x);
                             bmp.setPixel(x + bmp.getWidth() / 2, h * -1 + bmp.getHeight() / 2, 0xFFFF00);
                        }
                }
                catch (Exception E)
                {
                    expr = E.toString();
                }

                ImB.setImageBitmap(bmp);

                //        ((TextView) findViewById(R.id.textView1)).setText(expr);
                findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        onCreate(null);
                    }
                });
            }
        });

    }
}
