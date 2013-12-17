package com.example.drugov_yaroslav_14;

import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 17.12.13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class Score {

    public int score;
    public float deltaTime;
    public float time;
    private Timer timer;

    public Score() {
        //timer = new Timer();
        deltaTime = time = 0;
    }

    public void Update() {
        deltaTime = 1;
        time += 1;
        //timer.s
    }
}
