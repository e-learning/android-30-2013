package com.example.drugov_yaroslav_14;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 17.12.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class GameField extends SurfaceView implements SurfaceHolder.Callback {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int field[];
    private int n;
    private Orientation orientation;
    private Score score;

    private float r;
    private float x;
    private float y;
    private float vx;
    private float vy;

    public GameField(Context context) {
        super(context);
        getHolder().addCallback(this);

        score = new Score();
        orientation = new Orientation(context);
        r = 10;
        x = y = 100;
        vx = vy = 0;
        paint.setStyle(Paint.Style.FILL);
    }

    private Updater updater;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        (updater = new Updater(this)).start();
        StartNewLevel(10);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.

        int bx, by;
        for (int i = 0; i < n * n; i++) {
            bx = i % n;
            by = i / n;
            switch (field[i]) {
                case 1:
                    paint.setColor(Color.WHITE);
                    break;
                default:
                    paint.setColor(Color.BLACK);
                    break;
            }
            canvas.drawRect(canvas.getWidth() * bx / n, canvas.getHeight() * by / n, canvas.getWidth() * (bx + 1) / n, canvas.getHeight() * (by + 1) / n, paint);
        }

        paint.setColor(Color.GREEN);
        canvas.drawCircle(x, y, r, paint);
    }

    private void StartNewLevel(int n) {
        this.n = n;
        field = new int[n * n];
        java.util.Arrays.fill(field, 0);
        for (int i = 0; i < n; i++) {
            field[n * i] = 1;
            field[n * i + n - 1] = 1;
            field[i] = 1;
            field[(n - 1) * n + i] = 1;
        }

    }

    private void BallUpdate(Canvas canvas) {
        float oldx = x;
        float oldy = y;

        x += vx * score.deltaTime + orientation.ax * score.deltaTime * score.deltaTime / 2;
        y += vy * score.deltaTime + orientation.ay * score.deltaTime * score.deltaTime / 2;
        vx += orientation.ax * score.deltaTime;
        vy += orientation.ay * score.deltaTime;

        int bx, by;
        float top, bottom, left, right, pen, c = 0.5f;
        boolean isYchange = false;
        boolean isXchange = false;
        for (int i = 0; i < n * n; i++) {
            bx = i % n;
            by = i / n;
            top    = canvas.getHeight() * (by + 0) / n;
            bottom = canvas.getHeight() * (by + 1) / n;
            left   = canvas.getWidth()  * (bx + 0) / n;
            right  = canvas.getWidth()  * (bx + 1) / n;

            switch (field[i]) {
                case 1:
                    if (x > left - r && x < right + r && y > top - r && y < bottom + r) {
                        if ((oldx < left - r || oldx > right + r)) {
                            if (oldx < left - r) {
                                pen = x - (left - r);
                                x = left - r;
                            }
                            else {
                                pen = (right + r) - x;
                                x = right + r;
                            }
                            if (!isXchange) {
                                vx -= c * pen * score.deltaTime;
                                vx *= -0.9f;
                                isXchange = true;
                            }
                        }
                        else {
                            if (oldy < top - r) {
                                pen = y - (top - r);
                                y = top - r;
                            }
                            else {
                                pen = (bottom + r) - x;
                                y = bottom + r;
                            }
                            if (!isYchange) {
                                vy -= c * pen * score.deltaTime;
                                vy *= -0.9f;
                                isYchange = true;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        //x = Math.max(Math.min(x, canvas.getWidth()), 0);
        //y = Math.max(Math.min(y, canvas.getHeight()), 0);
    }

    public void Update() {
        score.Update();

        Canvas canvas = getHolder().lockCanvas();
        BallUpdate(canvas);
        onDraw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }
}
