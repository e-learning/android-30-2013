package com.sp4.calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI14
 * Date: 17.12.13
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
public class Integral extends Activity {
    String Str,s1;
    double Ot = 0, Do = 0, N = 1, Dx = 0;

    void Count()
    {
       Parser p= new Parser();
       double s = 0, x;
       Dx = (Do - Ot) / N;
       for (int i = 0; i <= N; i++)
       {
         x = Ot + i * Dx;
           s1 = Str.substring(0, Str.indexOf('X')) + Double.toString(x) + Str.substring(Str.indexOf('X') + 1, Str.length());
         while(s1.indexOf('X') != -1)
             s1 = s1.substring(0, s1.indexOf('X')) + Double.toString(x) + s1.substring(s1.indexOf('X') + 1, s1.length());
         s1 = p.Pars(' ' + s1);
         s += Double.parseDouble(s1) * Dx;
       }
        TextView t = (TextView) findViewById(R.id.res);
        t.setText(Double.toString(s));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integral);

        Intent i = getIntent();

        Str = i.getStringExtra("s");
        TextView t = (TextView) findViewById(R.id.textViewf);
        t.setText("You function is:" + Str);

        EditText ot = (EditText)findViewById(R.id.editText);
        ot.selectAll();
        ot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Ot = Double.parseDouble(editable.toString());
                Count();
                editable.clear();
            }
        });

        EditText doo = (EditText)findViewById(R.id.editText1);
        doo.selectAll();
        doo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Do = Double.parseDouble(editable.toString());
                Count();
                editable.clear();
            }
        });

        EditText dx = (EditText)findViewById(R.id.editText1);
        dx.selectAll();
        dx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                N = Double.parseDouble(editable.toString());
                Count();
                editable.clear();
            }
        });


    }
}
