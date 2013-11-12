package com.example.Calculator;

import android.app.Activity;
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

    // Put Bresentham line function
    private void PutLine(Bitmap BM, int W, int H, int x, int OldY, int y) {
        if (OldY <= y && ((y >= 0 && y < H) || (OldY >= 0 && OldY < H))) {
            for (int i = OldY; i <= y; i++)
                if (x >= 0 && x < W && i >= 0 && i < H)
                    BM.setPixel(x, i, 0xffffffff);
        } else if (OldY >= y && ((y >= 0 && y < H) || (OldY >= 0 && OldY < H))) {
            for (int i = OldY; i >= y; i--)
                if (x >= 0 && x < H && i >= 0 && i < H)
                    BM.setPixel(x, i, 0xffffffff);
        }
    }

    private int Clamp(int Value, int Max, int Min ) {
        if (Value > Max)
            return Max;
        else if (Value < Min)
            return Min - 1;
        else
            return Value;
    }

    private final View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (((Button)view).getText().toString().equals("Clear")) {
                if (CalcString.size() > 0)
                    CalcString.removeAllElements();
                CalcWasFunction = false;
            } else if (((Button)view).getText().toString().equals("BcSp")) {
                if (CalcString.size() > 0)
                    CalcString.remove((int) CalcString.size() - 1);
                else
                    CalcWasFunction = false;
            } else if (((Button)view).getText().toString().equals("f(x)="))
                CalcWasFunction = true;
            else if (((Button)view).getText().toString().equals("=")) {
                if (!Proc.Scanner(CalcString)) {
                    CalcTextView.setText("Scanner error");
                    Proc.Reset();
                    CalcString.clear();
                    CalcWasFunction = false;
                    return;
                }
                if (!Proc.Parser()) {
                    CalcTextView.setText("Parser error");
                    Proc.Reset();
                    CalcString.clear();
                    CalcWasFunction = false;
                }
                CalcTextView.setText("" + Proc.Evaluator());
                CalcString.clear();
                CalcWasFunction = false;
                return;
                /*
                double res = Proc.Evaluator();
                String s = new String("" + res);
                Proc.Reset();
                CalcString.clear();
                for (int i = 0; i < s.length(); i++)
                    CalcString.add("" + s.toCharArray()[i]);
                */
            } else if (((Button)view).getText().toString().equals("Paint")) {
                if (!Proc.Scanner(CalcString)) {
                    CalcTextView.setText("Scanner error");
                    Proc.Reset();
                    CalcString.clear();
                    CalcWasFunction = false;
                    return;
                }
                if (!Proc.Parser()) {
                    CalcTextView.setText("Parser error");
                    Proc.Reset();
                    CalcString.clear();
                    CalcWasFunction = false;
                }
                DisplayMetrics DM = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(DM);
                Bitmap BM = Bitmap.createBitmap(DM.widthPixels, DM.heightPixels, Bitmap.Config.ARGB_8888);
                for (int i = 0; i < DM.heightPixels; i++)
                    BM.setPixel(DM.widthPixels / 2, i, 0xffff0000);
                for (int i = 0; i < DM.widthPixels; i++)
                    BM.setPixel(i, DM.heightPixels / 2, 0xff0000ff);
                boolean IsFirstPoint = true;
                int OldY = 0;
                for (int x = 0; x < DM.widthPixels; x++) {
                    Proc.SetAllVariables(x - DM.widthPixels / 2);
                    double res = Proc.Evaluator();
                    int y = -(int)res + DM.heightPixels / 2;
                    if (res == (1.0 / 0)) {
                        PutLine(BM, DM.widthPixels, DM.heightPixels, x-1, OldY, Clamp(-y, DM.heightPixels, 0));
                        IsFirstPoint = true;
                    }
                    y = Clamp(y, DM.heightPixels, 0);
                    if (IsFirstPoint) {
                        if (x >= 0 && x < DM.widthPixels && y >= 0 && y < DM.heightPixels)
                            BM.setPixel(x, y, 0xffffffff);
                        IsFirstPoint = false;
                    } else
                        PutLine(BM, DM.widthPixels, DM.heightPixels, x, OldY, y);
                    OldY = y;
                }
                Proc.Reset();
                CalcWasFunction = false;
                CalcString.clear();
                SetGraphicField();
                ImageButton IB = (ImageButton)findViewById(R.id.imageButtonPicture);
                IB.setImageBitmap(BM);
                return;
            } else
                CalcString.add(((Button)view).getText().toString());

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
    };

    // Main project function
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetMainField();
    }
}
