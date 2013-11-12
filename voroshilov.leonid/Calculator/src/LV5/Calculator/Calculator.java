package LV5.Calculator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Vector;


public class Calculator extends Activity
{
    Bitmap bm;
    String history = new String("");
    private class HistoryListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view)
        {
            final TextView field = (TextView) findViewById(R.id.textView);
            field.setText(history);
        }
    }
    private class GraphListener implements View.OnClickListener
    {
        public void BrezenhemeLine2(int x1, int y1, int x2, int y2)
        {
            int deltaX = Math.abs(x2 - x1);
            int deltaY = Math.abs(y2 - y1);
            int signX = x1 < x2 ? 1 : -1;
            int signY = y1 < y2 ? 1 : -1;
            int error = deltaX - deltaY;

            bm.setPixel(x2, y2, 0xFF00FF00);

            while(x1 != x2 || y1 != y2)
            {
                bm.setPixel(x1, y1, 0xFF00FF00);
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
        public void DrawBrezenhemeLine (int x1, int y1, int x2, int y2)
        {
            int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

            dx = x2 - x1;
            dy = y2 - y1;
            incx = (int)Math.signum(dx);
            incy = (int)Math.signum(dy);
            dx = Math.abs(dx);
            dy = Math.abs(dy);

            if(dx > dy)
            {
                pdx = incx;
                pdy = 0;
                es = dy;
                el = dy;
            }

            else
            {
                pdx = 0;
                pdy = incy;
                es = dx;
                el = dy;
            }

            x = x1;
            y = y1;

            err = el / 2;
            bm.setPixel(x, y, 0xFF00FF00);

            for(int i = 0; i < el; i++)
            {
                err = -es;
                if(err < 0)
                {
                    err += el;
                    x += incx;
                    y += incy;
                }
                else
                {
                    x += pdx;
                    y += pdy;
                }

                bm.setPixel(x, y, 0xFF00FF00);

            }
        }

        @Override
        public void onClick(View view)
        {
            ImageButton IB = (ImageButton)findViewById(R.id.imageButton);
            DisplayMetrics DM = new DisplayMetrics();
            int oldx = 0,oldy = 0;
            boolean IsInit = false;
            TextView field = (TextView) findViewById(R.id.textView);
            getWindowManager().getDefaultDisplay().getMetrics(DM);
            String str = new String(field.getText().toString());
            bm = bm.createBitmap(DM.widthPixels, DM.heightPixels, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < DM.widthPixels; x++)
                bm.setPixel(x,DM.heightPixels / 2, 0xFF0000FF);
            for (int y = 0; y < DM.heightPixels; y++)
                bm.setPixel(DM.widthPixels / 2, y, 0xFF0000FF);

            for(int x = -DM.widthPixels/ 2; x < DM.widthPixels / 2; x += 1)
            {
                    try
                    {
                        if(field.length() != 0)
                        {
                            double res = -1 * Parser.FunctionCalculation(str,x) + DM.heightPixels / 2;
                            bm.setPixel(x + DM.widthPixels/ 2, (int)Math.ceil(res), 0xFF00FF00);
                            if(IsInit == false)
                            {
                                oldx = x + DM.widthPixels/ 2;
                                oldy = (int)res;
                                IsInit = true;
                            }
                            if(str.indexOf('t') == -1 && str.indexOf('c') == -1)
                            BrezenhemeLine2(oldx ,oldy, x + DM.widthPixels / 2,(int) res);
                            oldx = x + (DM.widthPixels / 2);
                            oldy = (int)res;


                        }
                    }
                    catch (Exception Ex)
                    {
                        field.setText(Ex.toString());
                        field.setText(str);
                    }
            }
            IB.setImageBitmap(bm);
            IB.setVisibility(View.VISIBLE);
            IB.setOnClickListener(new View.OnClickListener(){
              public void onClick (View view){
                view.setVisibility(View.GONE);
            }});
        }
    }

            private class TrigonometryListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view)
        {
            final Button sender = (Button) view;
            final TextView field = (TextView) findViewById(R.id.textView);
            field.setText(field.getText().toString() + sender.getText().toString() + "(");
        }
    }

    private class CButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view)
            {
                final TextView field = (TextView) findViewById(R.id.textView);
                field.setText("");
        }
    }




    private class SpecialButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            final Button sender = (Button) view;
            final TextView field = (TextView) findViewById(R.id.textView);
            String str = new String(field.getText().toString());
            String tmp = new String("");
            if(str.indexOf('X') == -1 && str.indexOf('=') == -1)
            {
                try
                {
                    tmp = Double.toString(Parser.Calculation(str));
                }
                catch (Exception Ex)
                {
                    tmp = "Unfortunately there is a problem: " + Ex.toString();
                    tmp = "";

                }

                ((TextView) findViewById(R.id.textView)).setText(str + " = " + tmp);
                tmp = str + " = " + tmp;

                history = history +  tmp + "\n";
            }
        }
    }


    private class NumButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view){
            final Button sender = (Button) view;
            final TextView field = (TextView) findViewById(R.id.textView);
            field.setText(field.getText().toString() + sender.getText().toString());
        }
    }
    private NumButtonListener numButtonListener = new NumButtonListener();
    private SpecialButtonListener specButtonListener = new SpecialButtonListener();
    private CButtonListener cButtonListener = new CButtonListener();
    private TrigonometryListener tgListener = new TrigonometryListener();
    private GraphListener graphListener = new GraphListener();
    private HistoryListener hlistener = new HistoryListener();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.num7).setOnClickListener(numButtonListener);
        findViewById(R.id.num8).setOnClickListener(numButtonListener);
        findViewById(R.id.num9).setOnClickListener(numButtonListener);
        findViewById(R.id.num4).setOnClickListener(numButtonListener);
        findViewById(R.id.num5).setOnClickListener(numButtonListener);
        findViewById(R.id.num6).setOnClickListener(numButtonListener);
        findViewById(R.id.num1).setOnClickListener(numButtonListener);
        findViewById(R.id.num2).setOnClickListener(numButtonListener);
        findViewById(R.id.num3).setOnClickListener(numButtonListener);
        findViewById(R.id.num0).setOnClickListener(numButtonListener);

        findViewById(R.id.buttonplus).setOnClickListener(numButtonListener);
        findViewById(R.id.buttonminus).setOnClickListener(numButtonListener);
        findViewById(R.id.buttonmultyply).setOnClickListener(numButtonListener);
        findViewById(R.id.buttondivide).setOnClickListener(numButtonListener);
        findViewById(R.id.buttondot).setOnClickListener(numButtonListener);
        findViewById(R.id.buttonequal).setOnClickListener(specButtonListener);
        findViewById(R.id.buttonbracket1).setOnClickListener(numButtonListener);
        findViewById(R.id.buttonbracket2).setOnClickListener(numButtonListener);

        findViewById(R.id.buttonpow).setOnClickListener(numButtonListener);
        findViewById(R.id.buttonclear).setOnClickListener(cButtonListener);
        findViewById(R.id.buttonmod).setOnClickListener(numButtonListener);

        findViewById(R.id.buttonsin).setOnClickListener(tgListener);
        findViewById(R.id.buttoncos).setOnClickListener(tgListener);
        findViewById(R.id.buttonctg).setOnClickListener(tgListener);
        findViewById(R.id.buttontg).setOnClickListener(tgListener);
        findViewById(R.id.buttonabs).setOnClickListener(tgListener);

        findViewById(R.id.buttonX).setOnClickListener(numButtonListener);
        findViewById(R.id.buttongraph).setOnClickListener(graphListener);
        findViewById(R.id.buttonhistory).setOnClickListener(hlistener);



    }
}
