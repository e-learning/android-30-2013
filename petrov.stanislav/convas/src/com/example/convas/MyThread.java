package com.example.convas;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI14
 * Date: 10.12.13
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class MyThread extends Thread {
    private Pictures picture;
    private int t;

    public MyThread( Pictures picture ) {
        this.picture = picture;
        t = 0;
    }

    @Override
    public void run() {
        int x = 100;
        int y = 100;
        int vx = 10;
        int vy = 10;
        int r = 8;
        for (int t = 0;; t++)
        {
            picture.StartFrame();
            picture.Clear();

            x += vx;
            x = Math.max(r, Math.min(picture.getScreenWidth() - r, x));
            y += vy;
            y = Math.max(r, Math.min(picture.getScreenHeight() - r, y));

            if (x - r <= 0 || x + r >= picture.getScreenWidth())
                vx = -vx;
            if (y - r <= 0 || y + r >= picture.getScreenHeight())
                vy = -vy;

            picture.SetCircle(x, y, r);
            picture.Redraw();

            picture.EndFrame();
        }
    }
}