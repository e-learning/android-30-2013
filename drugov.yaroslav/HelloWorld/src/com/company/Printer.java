package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: YD4
 * Date: 24.09.13
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */
public class Printer implements Observer {
    private String story;
    private int n;
    private int max;

    public Printer() {
        story = new String();
        max = 100;
        n = 0;
    }

    public Printer( int maxStorySize ) {
        story = new String();
        max = maxStorySize;
        n = 0;
    }

    public void HandleEvent( int newValue ) throws TooManyRecords {
        if (n++ >= max)
            throw new TooManyRecords(max);

        System.out.println("New value is " + newValue);
        story += " " + newValue;
    }

    public void Delete() {
        System.out.println("Changes story: " + story);
    }
}
