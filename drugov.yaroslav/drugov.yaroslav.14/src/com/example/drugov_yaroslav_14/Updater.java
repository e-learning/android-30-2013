package com.example.drugov_yaroslav_14;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 17.12.13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class Updater extends Thread {

    private GameField gameField;

    public Updater(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void run() {
        while (true)
            gameField.Update();
    }
}
