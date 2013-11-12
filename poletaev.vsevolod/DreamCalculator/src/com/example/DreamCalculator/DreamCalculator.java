package com.example.DreamCalculator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DreamCalculator extends Activity {
    private class ClickList implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mEval.addSymbol(((Button) view).getText().toString());
            ((TextView) findViewById(R.id.etExpr)).setText(mEval.toString());
        }
    }

    ;

    private void MainInit() {
        View.OnClickListener mClickList = new ClickList();

        findViewById(R.id.btDig0).setOnClickListener(mClickList);
        findViewById(R.id.btDig1).setOnClickListener(mClickList);
        findViewById(R.id.btDig2).setOnClickListener(mClickList);
        findViewById(R.id.btDig3).setOnClickListener(mClickList);
        findViewById(R.id.btDig4).setOnClickListener(mClickList);
        findViewById(R.id.btDig5).setOnClickListener(mClickList);
        findViewById(R.id.btDig6).setOnClickListener(mClickList);
        findViewById(R.id.btDig7).setOnClickListener(mClickList);
        findViewById(R.id.btDig8).setOnClickListener(mClickList);
        findViewById(R.id.btDig9).setOnClickListener(mClickList);
        findViewById(R.id.btOpAdd).setOnClickListener(mClickList);
        findViewById(R.id.btOpSub).setOnClickListener(mClickList);
        findViewById(R.id.btOpDev).setOnClickListener(mClickList);
        findViewById(R.id.btOpProd).setOnClickListener(mClickList);
        findViewById(R.id.btOpDot).setOnClickListener(mClickList);
        findViewById(R.id.btOpOpBr).setOnClickListener(mClickList);
        findViewById(R.id.btOpClBr).setOnClickListener(mClickList);
        findViewById(R.id.btFnAbs).setOnClickListener(mClickList);
        findViewById(R.id.btFnAcos).setOnClickListener(mClickList);
        findViewById(R.id.btFnAsin).setOnClickListener(mClickList);
        findViewById(R.id.btFnAtan).setOnClickListener(mClickList);
        findViewById(R.id.btFnFloor).setOnClickListener(mClickList);
        findViewById(R.id.btFnDorbnaya).setOnClickListener(mClickList);
        findViewById(R.id.btFnFactor).setOnClickListener(mClickList);
        findViewById(R.id.btFnLn).setOnClickListener(mClickList);
        findViewById(R.id.btFnLog).setOnClickListener(mClickList);
        findViewById(R.id.btFnRnd).setOnClickListener(mClickList);
        findViewById(R.id.btFnSin).setOnClickListener(mClickList);
        findViewById(R.id.btFnSqrt).setOnClickListener(mClickList);
        findViewById(R.id.btFnCos).setOnClickListener(mClickList);
        findViewById(R.id.btFnTan).setOnClickListener(mClickList);
        findViewById(R.id.btFnToRad).setOnClickListener(mClickList);
        findViewById(R.id.btOpPow).setOnClickListener(mClickList);
        findViewById(R.id.btConstE).setOnClickListener(mClickList);
        findViewById(R.id.btConstPi).setOnClickListener(mClickList);


        findViewById(R.id.btFnRnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEval.addSymbol("" + Math.random());
            }
        });

        findViewById(R.id.btOpEval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ((TextView) findViewById(R.id.etExpr)).setText("" + mEval.doEvaluation());
                } catch (ExprEvaluator.SyntaxErrorException e) {
                    ((TextView) findViewById(R.id.etExpr)).setText(e.toString());
                } catch (Operator.InvalidArguementException e) {
                    ((TextView) findViewById(R.id.etExpr)).setText(e.toString());
                } finally {
                    mEval.reset();
                }
            }
        });

        findViewById(R.id.btCtrlDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEval.deleteLast();
                ((TextView) findViewById(R.id.etExpr)).setText(mEval.toString());
            }
        });

        findViewById(R.id.btCtrlGrahicsTools).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.graph);
                ImageButton IB = (ImageButton) findViewById(R.id.imageButton);
                Point Size = new Point();
                Bitmap BM;

                getWindowManager().getDefaultDisplay().getSize(Size);
                BM = Bitmap.createBitmap(Size.x, Size.y, Bitmap.Config.ARGB_8888);
                IB.setImageBitmap(BM);


                for (int i = 0; i < Math.max(Size.x, Size.y); i++) {
                    if (i < Size.y)
                        BM.setPixel(Size.x / 2, i, 0xFFFF0000);
                    if (i < Size.x)
                        BM.setPixel(i, Size.y / 2, 0xFFFF0000);
                }

                for (int i = 0; i < Size.x; i++) {
                    //BM.setPixel();
                }

                IB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.main);
                        MainInit();
                    }
                });
            }
        });

        findViewById(R.id.btCtrlIntegralMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private static ExprEvaluator mEval = new ExprEvaluator();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MainInit();
    }
}