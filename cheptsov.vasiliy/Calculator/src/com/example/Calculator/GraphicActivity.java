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

// Graphic render activity
public class GraphicActivity extends Activity {
    private static Processing proc = new Processing();
    private static Vector<String> CalcString = new Vector<String>();
    private boolean IsProcCanRun;
    private int Zoom;
    private int WShift;
    private int HShift;

    private final View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (((Button)view).getText().toString().equals("+"))
                Zoom += 10;
            else if (((Button)view).getText().toString().equals("-"))
                Zoom -= 10;
            else if (((Button)view).getText().toString().equals("L"))
                WShift -= 100;
            else if (((Button)view).getText().toString().equals("R"))
                WShift += 100;
            else if (((Button)view).getText().toString().equals("U"))
                HShift -= 100;
            else
                HShift += 100;
            Paint();
        }
    };

    public void Paint() {
        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);
        Bitmap BM = Bitmap.createBitmap(DM.widthPixels, DM.heightPixels, Bitmap.Config.ARGB_8888);
        int now;
        double RealZoom;

        if (Zoom > 0)
            RealZoom = Zoom;
        else if (Zoom < 0)
            RealZoom = 1.0 / -Zoom;
        else
            RealZoom = 1;

        for (int i = 0; i < DM.heightPixels; i++) {
            now = DM.widthPixels / 2 - WShift;
            if (now >= 0 && now < DM.widthPixels)
                BM.setPixel(now, i, 0xffff0000);
        }
        for (int i = 0; i < DM.widthPixels; i++) {
            now = DM.heightPixels / 2 - HShift;
            if (now >= 0 && now < DM.heightPixels)
                BM.setPixel(i, now, 0xff0000ff);
        }
        for (int x = 0; x < DM.widthPixels; x++) {
            proc.SetAllVariables((x - DM.widthPixels / 2) + WShift);
            try {
                int y = (int)((-proc.Evaluator() * RealZoom + DM.heightPixels / 2 - HShift));
                if (x >= 0 && x < DM.widthPixels && y >= 0 && y < DM.heightPixels)
                    BM.setPixel(x, y, 0xffffffff);
            } catch (ArithmeticException AE) {
            }
        }
        ((ImageButton)findViewById(R.id.imageButtonPicture)).setImageBitmap(BM);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int NoofExtras = getIntent().getIntExtra("NoofExtras", 0);
        for (int i = 0; i < NoofExtras; i++)
            CalcString.add(getIntent().getStringExtra("Extra" + i));

        if (!proc.Scanner(CalcString)) {
            CalcString.clear();
            proc.Reset();
            setContentView(R.layout.graphic_error);
            ((TextView)findViewById(R.id.graphic_error_text)).setText("Scanner error");
            IsProcCanRun = false;
            return;
        }
        if (!proc.Parser()) {
            CalcString.clear();
            proc.Reset();
            setContentView(R.layout.graphic_error);
            ((TextView)findViewById(R.id.graphic_error_text)).setText("Parser error");
            IsProcCanRun = false;
            return;
        }
        setContentView(R.layout.bitmap);
        ((Button)findViewById(R.id.buttonZoomAdd)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonZoomSub)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonLeft)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonRight)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonUp)).setOnClickListener(ClickListener);
        ((Button)findViewById(R.id.buttonDown)).setOnClickListener(ClickListener);
        IsProcCanRun = true;
        WShift = HShift = Zoom = 0;
        Paint();
    }
}
