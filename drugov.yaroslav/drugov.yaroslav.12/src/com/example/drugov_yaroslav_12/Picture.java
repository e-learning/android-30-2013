package com.example.drugov_yaroslav_12;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 10.12.13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class Picture extends SurfaceView implements SurfaceHolder.Callback {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int x;
    private int y;
    private int r;
    private Canvas canvas;

    public Picture(Context context) {
        super(context);
        getHolder().addCallback(this);
        x = y = r = 0;

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
    }

    public void StartFrame() {
        canvas = getHolder().lockCanvas();
    }

    public void EndFrame() {
        getHolder().unlockCanvasAndPost(canvas);
    }

    public int getScreenWidth() {
        return canvas.getWidth();
    }

    public int getScreenHeight() {
        return canvas.getHeight();
    }

    public void SetCircle( int x, int y, int r ) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void Clear() {
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        paint.setColor(Color.GREEN);
    }

    public void Redraw() {
        canvas.drawCircle(x, y, r, paint);
        //onDraw(canvas);
    }

    private MyThread thread;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        (thread = new MyThread(this)).start();
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
        canvas.drawCircle(x, y, r, paint);
        //canvas.drawText("Privet", 10, 200, paint);
        //super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
