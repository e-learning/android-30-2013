package com.example.drugov_yaroslav_12;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 10.12.13
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class MyThread extends Thread {
    private Picture picture;
    private int t;

    public MyThread( Picture picture ) {
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
            if (x - r <= 0 || x + r >= picture.getScreenWidth())
                vx = -vx;
            if (x < r)
                x += 2 * (r - x);
            else if (x > picture.getScreenWidth() - r)
                x -= 2 * (x - (picture.getScreenWidth() - r));
            //x = Math.max(r, Math.min(picture.getScreenWidth() - r, x));

            y += vy;
            if (y - r <= 0 || y + r >= picture.getScreenHeight())
                vy = -vy;
            if (y < r)
                y += 2 * (r - y);
            else if (y > picture.getScreenHeight() - r)
                y -= 2 * (y - (picture.getScreenHeight() - r));
            //y = Math.max(r, Math.min(picture.getScreenHeight() - r, y));

            picture.SetCircle(x, y, r);
            picture.Redraw();

            picture.EndFrame();
        }
    }
}
