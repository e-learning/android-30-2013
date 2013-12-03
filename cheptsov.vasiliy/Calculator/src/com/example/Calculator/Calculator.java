package com.example.Calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Vector;

public class Calculator extends Activity {
    // Calculator text view handle
    static private TextView CalcTextView;
    // Calculator string
    static private Vector<String> CalcString = new Vector<String>();
    // Calculator was function flag
    static boolean CalcWasFunction = false;
    // Calculator processing class
    static private Processing Proc = new Processing();

    // Set main field function
    private void SetMainField() {
        // Initialisate layout
        setContentView(R.layout.main);

        // Initialisate buttons
        ((Button)findViewById(R.id.button0)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button1)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button2)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button3)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button4)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button5)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button6)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button7)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button8)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.button9)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonClear)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonDiv)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonMul)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonBcSpace)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonSub)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonAdd)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonNeg)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonPoint)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonABS)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonRes)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonSIN)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonCOS)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonTAN)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonCAT)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonFact)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonSQRT)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonOpenSc)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonCloseSc)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonFx)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonX)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonPaint)).setOnClickListener(ClickListener);

        // Initialisate text view field
        CalcTextView = (TextView)findViewById(R.id.textViewCalc);
        CalcTextView.setText("");
    }

    // Set graphic field function
    private void SetGraphicField() {
        // Initialisate layout
        setContentView(R.layout.bitmap);

        // Initialisate click button function
        findViewById(R.id.imageButtonPicture).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetMainField();
            }
        });
    }

    private int Clamp(int Value, int Max, int Min ) {
        if (Value > Max)
            return Max;
        else if (Value < Min)
            return Min - 1;
        else
            return Value;
    }

    private void Paint() {
        String Line = "";
        if (CalcWasFunction)
            Line += "f(x)=";
        for (int i = 0; i < CalcString.size(); i++)
            if (CalcString.get(i).equals("+/-"))
                Line += "-";
            else
                Line += CalcString.get(i);
        CalcTextView.setText(Line);
    }

    private final View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (((Button)view).getText().toString().equals("Clear")) {
                if (CalcString.size() > 0)
                    CalcString.removeAllElements();
                CalcWasFunction = false;
                Paint();
                return;
            } else if (((Button)view).getText().toString().equals("BcSp")) {
                if (CalcString.size() > 0) {
                    CalcString.removeElementAt(CalcString.size() - 1);
                    Paint();
                    return;
                }
                else {
                    CalcWasFunction = false;
                    Paint();
                    return;
                }
            } else if (((Button)view).getText().toString().equals("f(x)="))
                CalcWasFunction = true;
            else if (((Button)view).getText().toString().equals("=")) {
                if (!Proc.Scanner(CalcString)) {
                    CalcTextView.setText("Scanner error");
                    Proc.Reset();
                    CalcString.removeAllElements();
                    CalcWasFunction = false;
                    return;
                }
                if (!Proc.Parser()) {
                    CalcTextView.setText("Parser error");
                    Proc.Reset();
                    CalcString.removeAllElements();
                    CalcWasFunction = false;
                    return;
                }
                double res = 0;
                try {
                    res = Proc.Evaluator();
                    CalcTextView.setText("" + res);
                } catch (ArithmeticException AE) {
                    CalcTextView.setText("Arithmetical error");
                }
                CalcString.removeAllElements();
                Proc.Reset();
                CalcWasFunction = false;
                return;
            } if (((Button)view).getText().toString().equals("Paint")) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), GraphicActivity.class);
                intent.putExtra("NoofExtras", CalcString.size());
                for (int i = 0; i < CalcString.size(); i++)
                    intent.putExtra("Extra" + i, CalcString.elementAt(i));
                startActivity(intent);
                CalcTextView.setText("");
                CalcString.removeAllElements();
            } else
                CalcString.add(((Button)view).getText().toString());
         Paint();
        }
    };

    // Main project function
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetMainField();
    }
}
